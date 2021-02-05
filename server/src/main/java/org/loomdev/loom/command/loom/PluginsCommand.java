package org.loomdev.loom.command.loom;

import com.google.common.collect.ImmutableList;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandContext;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.plugin.PluginState;
import org.loomdev.api.plugin.exception.PluginNotFoundException;
import org.loomdev.api.util.ChatColor;
import org.loomdev.loom.plugin.InternalPluginManager;
import org.loomdev.loom.plugin.PluginContainer;
import org.loomdev.api.plugin.exception.IllegalPluginStateChangeException;

import java.util.List;
import java.util.stream.Collectors;

public class PluginsCommand extends Command {

    private final InternalPluginManager pluginManager;

    public PluginsCommand(InternalPluginManager pluginManager) {
        super("plugins", "pl");
        this.pluginManager = pluginManager;

        setDescription("Shows a list of plugins installed on this server.");
        setUsage("/plugins");
        setPermission("loom.command.plugins");
    }

    @Override
    public void execute(@NotNull CommandContext context) {
        var source = context.getSource();
        var args = context.getArguments();

        var isAdmin = source.hasPermission("loom.command.plugins.admin");
        if (!isAdmin || args.length == 0) {
            sendList(source);
            return;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
            sendHelpText(source);
            return;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            reloadPlugins(source);
            return;
        }

        if (args.length == 2) {
            var pluginId = args[1];

            if (args[0].equalsIgnoreCase("disable")) {
                disablePlugin(source, pluginId);
                return;
            } else if (args[0].equalsIgnoreCase("enable")) {
                enablePlugin(source, pluginId);
                return;
            } else if (args[0].equalsIgnoreCase("reload")) {
                reloadPlugin(source, pluginId);
                return;
            }
        }

        var usageMessage = Component.text()
                .append(Component.text("Invalid usage! Use").color(NamedTextColor.RED))
                .append(Component.text(" /plugins help ").color(NamedTextColor.GOLD))
                .append(Component.text("for usage details.").color(NamedTextColor.RED))
                .build();
        source.sendMessage(usageMessage);
    }

    @Override
    @NotNull
    public List<String> suggest(@NotNull CommandContext context) {
        var source = context.getSource();
        var isAdmin = source.hasPermission("loom.command.plugins.admin");

        if (!isAdmin) {
            return ImmutableList.of();
        }

        var args = context.getArguments();
        if (args.length == 0) {
            return ImmutableList.of("help", "reload", "enable", "disable");
        }

        if (args.length == 1) {
            return ImmutableList.copyOf(pluginManager.getPlugins().getAllPluginIds());
        }

        return ImmutableList.of();
    }

    // region Plugin list

    private void sendList(CommandSource source) {
        boolean isAdmin = source.hasPermission("loom.command.plugins.admin");

        var plugins = pluginManager.getPlugins()
                .all()
                .filter(container -> isAdmin || container.getState() == PluginState.ENABLED)
                .map(container -> getPlugin(container, isAdmin))
                .collect(Collectors.toList());

        var message = Component.join(
                Component.newline(),
                Component.text("Plugins (" + plugins.size() + "): ").color(ChatColor.WHITE),
                Component.join(Component.text(", "), plugins)
        );

        source.sendMessage(message);
    }

    private Component getPlugin(PluginContainer container, boolean isAdmin) {
        var metadata = container.getMetadata();

        var hoverTextBuilder = Component.text();

        if (isAdmin) {
            hoverTextBuilder.append(getHoverField("Id: ", metadata.getId()));
            hoverTextBuilder.append(Component.newline());
        }

        hoverTextBuilder.append(getHoverField("Name: ", metadata.getName()));

        metadata.getDescription().ifPresent(description -> {
            hoverTextBuilder.append(Component.newline());
            hoverTextBuilder.append(getHoverField("Description: ", description));
        });

        hoverTextBuilder.append(Component.newline());
        hoverTextBuilder.append(getHoverField("Version: ", metadata.getVersion()));

        if (metadata.getAuthors().length > 0) {
            hoverTextBuilder.append(Component.newline());
            hoverTextBuilder.append(getHoverField("Authors: ", String.join(", ", metadata.getAuthors())));
        }

        var state = container.getState();
        var stateColor = isAdmin ? state.getColor() : NamedTextColor.GREEN;

        if (isAdmin) {
            hoverTextBuilder.append(Component.newline());
            hoverTextBuilder.append(getHoverField("State: ", state.name().toLowerCase(), stateColor));
        }

        var hoverEvent = HoverEvent.showText(hoverTextBuilder.asComponent());
        var pluginName = Component.text(metadata.getName())
                .color(stateColor);

        if (isAdmin) {
            String commandSuggestion = null;
            if (state == PluginState.DISABLED || state == PluginState.INVALID) {
                commandSuggestion = "/pl enable " + metadata.getId();
            } else if (state == PluginState.ENABLED) {
                commandSuggestion = "/pl disable " + metadata.getId();
            }

            if (commandSuggestion != null) {
                var clickEvent = ClickEvent.suggestCommand(commandSuggestion);
                pluginName = pluginName.clickEvent(clickEvent);
            }
        }

        return pluginName.hoverEvent(hoverEvent);
    }

    private Component getHoverField(String label, String value) {
        return getHoverField(label, value, NamedTextColor.GREEN);
    }

    private Component getHoverField(String label, String value, TextColor color) {
        var labelComponent = Component.text(label).color(ChatColor.WHITE);
        var valueComponent = Component.text(value).color(color);

        return labelComponent
                .append(valueComponent);
    }

    // endregion Plugin list

    public void reloadPlugins(CommandSource source) {
        try {
            pluginManager.reloadAll();
            var message = Component.text("Reloaded all plugins").color(NamedTextColor.GREEN);
            source.sendMessage(message);
        } catch (Exception e) {
            source.sendMessage(getExceptionMessage(e));
            e.printStackTrace();
        }
    }

    public void disablePlugin(CommandSource source, String pluginId) {
        try {
            pluginManager.disable(pluginId);
            source.sendMessage(Component.text("Plugin '" + pluginId + "' disabled.").color(NamedTextColor.GREEN));
        } catch (PluginNotFoundException e) {
            source.sendMessage(getNotFoundText(e.getPluginId()));
        } catch (IllegalPluginStateChangeException exception) {
            var message = Component.text("Plugin already disabled. (state: " + exception.getFrom() + ")")
                    .color(NamedTextColor.RED);
            source.sendMessage(message);
        } catch (Exception e) {
            source.sendMessage(getExceptionMessage(e));
            e.printStackTrace();
        }
    }

    public void enablePlugin(CommandSource source, String pluginId) {
        try {
            pluginManager.enable(pluginId);
            source.sendMessage(Component.text("Plugin '" + pluginId + "' enabled.").color(NamedTextColor.GREEN));
        } catch (PluginNotFoundException e) {
            source.sendMessage(getNotFoundText(e.getPluginId()));
        } catch (IllegalPluginStateChangeException exception) {
            var message = Component.text("Plugin already enabled. (state: " + exception.getFrom() + ")")
                    .color(NamedTextColor.RED);
            source.sendMessage(message);
        } catch (Exception e) {
            source.sendMessage(getExceptionMessage(e));
            e.printStackTrace();
        }
    }

    public void reloadPlugin(CommandSource source, String pluginId) {
        try {
            var failedToDisable = pluginManager.reload(pluginId);
            if (failedToDisable) {
                source.sendMessage(Component.text("An error occurred when reloading '" + pluginId + "'. Please check the console for more information.").color(NamedTextColor.RED));
                return;
            }

            source.sendMessage(Component.text("Plugin '" + pluginId + "' reloaded.").color(NamedTextColor.GREEN));
        } catch (PluginNotFoundException e) {
            source.sendMessage(getNotFoundText(e.getPluginId()));
        } catch (Exception e) {
            source.sendMessage(getExceptionMessage(e));
            e.printStackTrace();
        }
    }

    // region Help Text

    private void sendHelpText(CommandSource source) {
        var helpText = Component.join(
                Component.newline(),
                getHelpTextLine("/pl", "Shows a list of all plugins."),
                getHelpTextLine("/pl reload", "Reload all plugins."),
                getHelpTextLine("/pl enable <plugin-id>", "Enable a disabled plugin."),
                getHelpTextLine("/pl disable <plugin-id>", "Disable a enabled plugin."),
                getHelpTextLine("/pl reload <plugin-id>", "Reload an enabled plugin.")
        );

        source.sendMessage(helpText);
    }

    private Component getHelpTextLine(String command, String description) {
        return Component.text(command).color(ChatColor.GOLD)
                .append(Component.text(" - ").color(ChatColor.GRAY))
                .append(Component.text(description).color(ChatColor.GRAY));
    }

    // endregion Help Text

    private Component getNotFoundText(String pluginId) {
        return Component.text("Plugin '" + pluginId + "' could not be found.")
                .color(NamedTextColor.RED);
    }

    private Component getExceptionMessage(Exception e) {
        return Component.text("Something went wrong! Please check the console for more information. (" + e.getClass().getSimpleName() +")")
                .color(NamedTextColor.RED);
    }
}

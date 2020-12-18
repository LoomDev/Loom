package org.loomdev.loom.command.loom;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandContext;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.plugin.PluginMetadata;
import org.loomdev.api.util.ChatColor;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class PluginsCommand extends Command {

    public PluginsCommand() {
        super("plugins", "pl");
        setDescription("Shows a list of plugins installed on this server.");
        setUsage("/plugins");
        setPermission("loom.command.plugins");
    }

    @Override
    public void execute(@NotNull CommandContext context) {
        var source = context.getSource();
        var args = context.getArguments();

        if (args.length == 0) {
            sendList(source);
            return;
        }

        if (source.hasPermission("loom.command.plugins.admin")) {
            source.sendMessage(Component.text("Insufficient permissions!").color(ChatColor.RED));
            return;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                source.sendMessage(Component.text("Reloading all plugins..."));
                Loom.getPluginManager().reloadAll();
                source.sendMessage(Component.text("Successfully reloaded all plugins!").color(ChatColor.GREEN));
            } else if(args[0].equalsIgnoreCase("scan")) {
                source.sendMessage(Component.text("Scanning for new plugins..."));
                List<PluginMetadata> newPlugins = Loom.getPluginManager().scanPluginDirectory();
                source.sendMessage(Component.text("Found " + newPlugins.size() + " new plugins!").color(ChatColor.GREEN));
            }
        } else if (args.length == 2) {
            String pluginId = args[1];
            Optional<PluginMetadata> metadataOpt = Loom.getPluginManager().getPluginMetadata(pluginId);

            if (!metadataOpt.isPresent()) {
                source.sendMessage(Component.text(String.format("No plugin found with id: %s.", args[1])).color(ChatColor.RED));
                return;
            }

            PluginMetadata metadata = metadataOpt.get();

            if (args[0].equalsIgnoreCase("reload")) {
                source.sendMessage(Component.text("Reloading " + metadata.getNameOrId() + "...").color(ChatColor.YELLOW));
                Loom.getPluginManager().reloadPlugin(metadata.getId());
                source.sendMessage(Component.text("Successfully reloaded " + metadata.getNameOrId() + "!").color(ChatColor.GREEN));
            } else if (args[0].equalsIgnoreCase("enable")) {
                source.sendMessage(Component.text("Enabling " + metadata.getNameOrId() + "...").color(ChatColor.YELLOW));
                Loom.getPluginManager().enablePlugin(metadata.getId());
                source.sendMessage(Component.text("Successfully enabled " + metadata.getNameOrId() + "!").color(ChatColor.GREEN));
            } else if (args[0].equalsIgnoreCase("disable")) {
                source.sendMessage(Component.text("Disabling " + metadata.getNameOrId() + "...").color(ChatColor.YELLOW));
                Loom.getPluginManager().disablePlugin(metadata.getId());
                source.sendMessage(Component.text("Successfully disabled " + metadata.getNameOrId() + "!").color(ChatColor.GREEN));
            } else {
                source.sendMessage(Component.text("Unknown usage!").color(ChatColor.RED)
                        .append(Component.newline())
                        .append(getHelpText()));
            }
        } else {
            source.sendMessage(Component.text("Unknown usage!").color(ChatColor.RED)
                    .append(Component.newline())
                    .append(getHelpText()));
        }
    }

    private void sendList(CommandSource source) {
        boolean full = source.hasPermission("loom.command.plugins.admin");

        TreeMap<String, PluginMetadata> plugins = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        Loom.getServer().getPluginManager().getAllPlugins()
                .stream()
                .filter(plugin -> full || Loom.getPluginManager().isEnabled(plugin.getId()))
                .forEach(plugin -> plugins.put(plugin.getNameOrId(), plugin));

        var builder = Component.empty().toBuilder()
                .append(Component.text("Plugins (" + plugins.size() + "): ").color(ChatColor.WHITE));

        int index = 0;
        for (Map.Entry<String, PluginMetadata> entry : plugins.entrySet()) {
            if (index++ > 0) {
                builder.append(Component.text(", ").color(ChatColor.WHITE));
            }

            TextComponent.Builder pluginBuilder = Component.empty().toBuilder();
            PluginMetadata metadata = entry.getValue();
            TextComponent.Builder hoverBuilder = Component.empty().toBuilder();

            if (full) {
                hoverBuilder.append(Component.text("Id: ").color(ChatColor.WHITE)
                        .append(Component.text(metadata.getId()).color(ChatColor.GREEN))).append(Component.newline());
            }

            metadata.getDescription().ifPresent(description ->
                    hoverBuilder.append(Component.text("Description: ").color(ChatColor.WHITE)
                            .append(Component.text(description).color(ChatColor.GREEN))));

            metadata.getVersion().ifPresent(version -> hoverBuilder.append(Component.newline())
                    .append(Component.text("Version: ").color(ChatColor.WHITE)
                            .append(Component.text(version).color(ChatColor.GREEN))));

            if (metadata.getAuthors().size() > 0) {
                hoverBuilder.append(Component.newline()).append(Component.text("Authors: ").color(ChatColor.WHITE))
                        .append(Component.text(String.join(", ", metadata.getAuthors())).color(ChatColor.GREEN));
            }

            PluginMetadata.State state = metadata.getState();
            TextColor stateColor = ChatColor.GREEN;
            if (state == PluginMetadata.State.ERROR) {
                stateColor = ChatColor.RED;
            } else if (state == PluginMetadata.State.DISABLED) {
                stateColor = ChatColor.GRAY;
            }

            if (full) {
                hoverBuilder.append(Component.newline())
                        .append(Component.text("State: ", ChatColor.WHITE))
                        .append(Component.text(state.name().toLowerCase(), stateColor));
            }

            pluginBuilder.append(Component.text(entry.getKey()).color(stateColor));
            pluginBuilder.hoverEvent(HoverEvent.showText(hoverBuilder.build()));

            if (full && state != PluginMetadata.State.ERROR) {
                if (state == PluginMetadata.State.DISABLED) {
                    pluginBuilder.clickEvent(ClickEvent.suggestCommand("/pl enable " + metadata.getId()));
                } else {
                    pluginBuilder.clickEvent(ClickEvent.suggestCommand("/pl disable " +metadata.getId()));
                }
            }

            builder.append(pluginBuilder);
        }

        source.sendMessage(builder.build());
    }

    private TextComponent getHelpText() {
        return Component.empty()
                .append(getHelpTextLine("/pl", "Shows a list of all plugins.")).append(Component.newline())
                .append(getHelpTextLine("/pl reload", "Reload all plugins.")).append(Component.newline())
                .append(getHelpTextLine("/pl enable <plugin-id>", "Enable a disabled plugin.")).append(Component.newline())
                .append(getHelpTextLine("/pl disable <plugin-id>", "Disable a enabled plugin."));
    }

    private TextComponent getHelpTextLine(String command, String description) {
        return Component.text(command).color(ChatColor.GOLD)
                .append(Component.text(" - ").color(ChatColor.GRAY))
                .append(Component.text(description).color(ChatColor.GRAY));
    }
}

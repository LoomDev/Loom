package org.loomdev.loom.command.loom;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.Loom;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.plugin.PluginMetadata;
import org.loomdev.api.util.ChatColor;

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
    public void execute(@NonNull CommandSource source, String[] args) {
        if (args.length == 0) {
            sendList(source);
            return;
        }

        // TODO permissions
        if (source instanceof Player && !((Player) source).isOp()) {
            source.sendMessage(TextComponent.of("Insufficient permissions!").color(ChatColor.RED));
            return;
        }

        if (args.length == 1) {
            if(args[0].equalsIgnoreCase("reload")) {
                source.sendMessage(TextComponent.of("Reloading all plugins..."));
                Loom.getPluginManager().reloadAll();
                source.sendMessage(TextComponent.of("Successfully reloaded all plugins!").color(ChatColor.GREEN));
            }
        } else if (args.length == 2) {
            String pluginId = args[1];
            Optional<PluginMetadata> metadataOpt = Loom.getPluginManager().getPluginMetadata(pluginId);

            if (!metadataOpt.isPresent()) {
                source.sendMessage(TextComponent.of(String.format("No plugin found with id: %s.", args[1])).color(ChatColor.RED));
                return;
            }

            PluginMetadata metadata = metadataOpt.get();

            if (args[0].equalsIgnoreCase("reload")) {
                source.sendMessage(TextComponent.of("Reloading " + metadata.getNameOrId() + "...").color(ChatColor.YELLOW));
                Loom.getPluginManager().reloadPlugin(metadata.getId());
                source.sendMessage(TextComponent.of("Successfully reloaded " + metadata.getNameOrId() + "!").color(ChatColor.GREEN));
            } else if (args[0].equalsIgnoreCase("enable")) {
                source.sendMessage(TextComponent.of("Enabling " + metadata.getNameOrId() + "...").color(ChatColor.YELLOW));
                Loom.getPluginManager().enablePlugin(metadata.getId());
                source.sendMessage(TextComponent.of("Successfully enabled " + metadata.getNameOrId() + "!").color(ChatColor.GREEN));
            } else if (args[0].equalsIgnoreCase("disable")) {
                source.sendMessage(TextComponent.of("Disabling " + metadata.getNameOrId() + "...").color(ChatColor.YELLOW));
                Loom.getPluginManager().disablePlugin(metadata.getId());
                source.sendMessage(TextComponent.of("Successfully disabled " + metadata.getNameOrId() + "!").color(ChatColor.GREEN));
            } else {
                source.sendMessage(TextComponent.of("Unknown usage!").color(ChatColor.RED)
                        .append(TextComponent.newline())
                        .append(getHelpText()));
            }
        } else {
            source.sendMessage(TextComponent.of("Unknown usage!").color(ChatColor.RED)
                    .append(TextComponent.newline())
                    .append(getHelpText()));
        }
    }

    private void sendList(CommandSource source) {
        boolean full = !(source instanceof Player) || ((Player) source).isOp(); // TODO permissions

        TreeMap<String, PluginMetadata> plugins = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        Loom.getServer().getPluginManager().getAllPlugins()
                .stream()
                .filter(plugin -> full || Loom.getPluginManager().isEnabled(plugin.getId()).orElse(false))
                .forEach(plugin -> plugins.put(plugin.getNameOrId(), plugin));

        TextComponent.Builder builder = TextComponent.builder()
                .append(TextComponent.of("Plugins (" + plugins.size() + "): ").color(ChatColor.WHITE));

        int index = 0;
        for (Map.Entry<String, PluginMetadata> entry : plugins.entrySet()) {
            if (index++ > 0) {
                builder.append(TextComponent.of(", ").color(ChatColor.WHITE));
            }

            TextComponent.Builder pluginBuilder = TextComponent.builder();
            PluginMetadata metadata = entry.getValue();
            TextComponent.Builder hoverBuilder = TextComponent.builder();

            if (full) {
                hoverBuilder.append(TextComponent.of("Id: ").color(ChatColor.WHITE)
                        .append(TextComponent.of(metadata.getId()).color(ChatColor.GREEN))).append(TextComponent.newline());
            }

            metadata.getDescription().ifPresent(description ->
                    hoverBuilder.append(TextComponent.of("Description: ").color(ChatColor.WHITE)
                            .append(TextComponent.of(description).color(ChatColor.GREEN))));

            metadata.getVersion().ifPresent(version -> hoverBuilder.append(TextComponent.newline())
                    .append(TextComponent.of("Version: ").color(ChatColor.WHITE)
                            .append(TextComponent.of(version).color(ChatColor.GREEN))));

            if (metadata.getAuthors().size() > 0) {
                hoverBuilder.append(TextComponent.newline()).append(TextComponent.of("Authors: ").color(ChatColor.WHITE))
                        .append(TextComponent.of(String.join(", ", metadata.getAuthors())).color(ChatColor.GREEN));
            }

            PluginMetadata.State state = metadata.getState();
            TextColor stateColor = ChatColor.GREEN;
            if (state == PluginMetadata.State.ERROR) {
                stateColor = ChatColor.RED;
            } else if (state == PluginMetadata.State.DISABLED) {
                stateColor = ChatColor.GRAY;
            }

            if (full) {
                hoverBuilder.append(TextComponent.newline())
                        .append(TextComponent.of("State: ", ChatColor.WHITE))
                        .append(TextComponent.of(state.name().toLowerCase(), stateColor));
            }

            pluginBuilder.append(TextComponent.of(entry.getKey()).color(stateColor));
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
        return TextComponent.builder()
                .append(getHelpTextLine("/pl", "Shows a list of all plugins.")).append(TextComponent.newline())
                .append(getHelpTextLine("/pl reload", "Reload all plugins.")).append(TextComponent.newline())
                .append(getHelpTextLine("/pl enable <plugin-id>", "Enable a disabled plugin.")).append(TextComponent.newline())
                .append(getHelpTextLine("/pl disable <plugin-id>", "Disable a enabled plugin."))
                .build();
    }

    private TextComponent getHelpTextLine(String command, String description) {
        return TextComponent.of(command).color(ChatColor.GOLD)
                .append(TextComponent.of(" - ").color(ChatColor.GRAY))
                .append(TextComponent.of(description).color(ChatColor.GRAY));
    }
}

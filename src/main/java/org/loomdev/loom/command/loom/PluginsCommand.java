package org.loomdev.loom.command.loom;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.Loom;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.plugin.Plugin;
import org.loomdev.api.plugin.PluginContainer;
import org.loomdev.api.plugin.PluginMetadata;

import java.util.Collection;
import java.util.Map;
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
        if (args.length < 2) {
            source.sendMessage(getList());
            return;
        }

        if (args[1].equalsIgnoreCase("enable")) { // TODO HACKY af
            Loom.getServer().getPluginManager().enablePlugin(args[2]);
        } else if (args[1].equalsIgnoreCase("disable")) {
            Loom.getServer().getPluginManager().disablePlugin(args[2]);
        }
    }

    @NonNull
    private TextComponent getList() {
        TextColor WHITE = TextColor.fromHexString("#ffffff"); // TODO replace once an enum exists for this
        TextColor GREEN = TextColor.fromHexString("#55FF55");
        TextColor RED = TextColor.fromHexString("#FF5555");


        TreeMap<String, PluginContainer> plugins = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        Loom.getServer().getPluginManager().getPlugins().forEach(plugin -> plugins.put(plugin.getMetadata().getName()
                .orElse(plugin.getMetadata().getId()), plugin));

        TextComponent.Builder builder = TextComponent.builder()
                .append(TextComponent.of(String.format("Plugins (%s): ", plugins.size())).color(WHITE));

        int index = 0;
        for (Map.Entry<String, PluginContainer> entry : plugins.entrySet()) {
            if (index++ > 0) {
                builder.append(TextComponent.of(" ,").color(WHITE));
            }

            TextComponent.Builder pluginBuilder = TextComponent.builder();
            PluginMetadata metadata = entry.getValue().getMetadata();
            TextComponent.Builder hoverBuilder = TextComponent.builder();

            metadata.getDescription().ifPresent(description -> hoverBuilder.append(TextComponent.of("Description: ").color(WHITE)
                    .append(TextComponent.of(description).color(GREEN))));

            metadata.getVersion().ifPresent(version -> hoverBuilder.append(TextComponent.newline())
                    .append(TextComponent.of("Version: ").color(WHITE)
                    .append(TextComponent.of(version).color(GREEN))));

            if (metadata.getAuthors().size() > 0) {
                hoverBuilder.append(TextComponent.newline()).append(TextComponent.of("Authors: ").color(WHITE))
                        .append(TextComponent.of(String.join(", ", metadata.getAuthors())).color(GREEN));
            }

            // TODO authors

            pluginBuilder.append(TextComponent.of(entry.getKey()).color(entry.getValue().isEnabled() ? GREEN : RED));
            pluginBuilder.hoverEvent(HoverEvent.showText(hoverBuilder.build()));
            builder.append(pluginBuilder);
        }

        return builder.build();
    }
}

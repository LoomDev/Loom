package org.loomdev.loom.command.loom;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import org.loomdev.api.command.ArgumentCommandNode;
import org.loomdev.api.command.CommandContext;
import org.loomdev.api.command.LiteralCommandNode;
import org.loomdev.api.command.Suggestion;
import org.loomdev.api.plugin.exception.IllegalPluginStateChangeException;
import org.loomdev.api.plugin.exception.PluginNotFoundException;
import org.loomdev.loom.plugin.InternalPluginManager;

import java.util.Collection;
import java.util.stream.Collectors;

public class PluginsCommand {

    public static void register(InternalPluginManager pluginManager) {
        LiteralCommandNode.builder("plugins")
                .requiresPermission("loom.command.plugins")
                .alias("pl")

                .then(LiteralCommandNode.builder("help")
                        .executesVoid(PluginsCommand::sendHelpText))

                .then(LiteralCommandNode.builder("reload")
                        .requiresPermission("loom.command.plugins.reload")

                        .then(ArgumentCommandNode.builder("plugin")
                                .ofString()
                                .suggester((ctx) -> suggestNames(pluginManager))
                        .executesBoolean((ctx) -> {
                            if(ctx.hasValue("plugin")) {
                                return reloadPlugin(pluginManager, ctx, ctx.getValue("plugin"));
                            }
                            else {
                                return reloadPlugins(pluginManager, ctx);
                            }
                        }))

                .then(LiteralCommandNode.builder("enable")
                        .requiresPermission("loom.command.plugins.enable")
                        .then(ArgumentCommandNode.builder("plugin")
                                .required()
                                .executesBoolean((ctx) -> enablePlugin(pluginManager, ctx, ctx.getValue("plugin"))))

                        )
                
                .then(LiteralCommandNode.builder("disable")
                        .requiresPermission("loom.command.plugins.disable")
                        .then(ArgumentCommandNode.builder("plugin")
                                .required()
                                .executesBoolean((ctx) -> disablePlugin(pluginManager, ctx, ctx.getValue("plugin"))))

                        ));
        
    }

    private static Collection<Suggestion> suggestNames(InternalPluginManager pluginManager) {
        return pluginManager.getPlugins().getAllMetadata().map((plugin) -> {
            return new Suggestion(plugin.getId(), plugin.toString());
        }).collect(Collectors.toList());
    }

    private static boolean reloadPlugins(InternalPluginManager pluginManager, CommandContext ctx) {
        try {
            pluginManager.reloadAll();
            var message = Component.text("Reloaded all plugins").color(NamedTextColor.GREEN);
            ctx.getSource().sendMessage(message);
            return true;
        } catch (Exception e) {
            ctx.getSource().sendMessage(getExceptionMessage(e));
            e.printStackTrace();
        }
        return false;
    }

    // region Help Text

    private static void sendHelpText(CommandContext ctx) {
        var helpText = Component.join(
                Component.newline(),
                getHelpTextLine("/pl", "Shows a list of all plugins."),
                getHelpTextLine("/pl reload", "Reload all plugins."),
                getHelpTextLine("/pl enable <plugin-id>", "Enable a disabled plugin."),
                getHelpTextLine("/pl disable <plugin-id>", "Disable a enabled plugin."),
                getHelpTextLine("/pl reload <plugin-id>", "Reload an enabled plugin.")
        );

        ctx.getSource().sendMessage(helpText);
    }

    private static boolean reloadPlugin(InternalPluginManager pluginManager, CommandContext ctx, String pluginId) {
        try {
            var failedToDisable = pluginManager.reload(pluginId);
            if (failedToDisable) {
                ctx.getSource().sendMessage(Component.text("An error occurred when reloading '" + pluginId + "'. Please check the console for more information.").color(NamedTextColor.RED));
                return false;
            }

            ctx.getSource().sendMessage(Component.text("Plugin '" + pluginId + "' reloaded.").color(NamedTextColor.GREEN));
            return true;
        } catch (PluginNotFoundException e) {
            ctx.getSource().sendMessage(getNotFoundText(e.getPluginId()));
        } catch (Exception e) {
            ctx.getSource().sendMessage(getExceptionMessage(e));
            e.printStackTrace();
        }
        return false;
    }

    private static Component getHelpTextLine(String command, String description) {
        return Component.text(command).color(NamedTextColor.GOLD)
                .append(Component.text(" - ").color(NamedTextColor.GRAY))
                .append(Component.text(description).color(NamedTextColor.GRAY));
    }

    // endregion Help Text

    private static Component getNotFoundText(String pluginId) {
        return Component.text("Plugin '" + pluginId + "' could not be found.")
                .color(NamedTextColor.RED);
    }

    private static Component getExceptionMessage(Exception e) {
        return Component.text("Something went wrong! Please check the console for more information. (" + e.getClass().getSimpleName() +")")
                .color(NamedTextColor.RED);
    }
    
    public static boolean disablePlugin(InternalPluginManager pluginManager, CommandContext ctx, String pluginId) {
        try {
            pluginManager.disable(pluginId);
            ctx.getSource().sendMessage(Component.text("Plugin '" + pluginId + "' disabled.").color(NamedTextColor.GREEN));
            return true;
        } catch (PluginNotFoundException e) {
            ctx.getSource().sendMessage(getNotFoundText(e.getPluginId()));
        } catch (IllegalPluginStateChangeException exception) {
            var message = Component.text("Plugin already disabled. (state: " + exception.getFrom() + ")")
                    .color(NamedTextColor.RED);
            ctx.getSource().sendMessage(message);
        } catch (Exception e) {
            ctx.getSource().sendMessage(getExceptionMessage(e));
            e.printStackTrace();
        }
        return false;
    }

    private static boolean enablePlugin(InternalPluginManager pluginManager, CommandContext ctx, String pluginId) {
        try {
            pluginManager.enable(pluginId);
            ctx.getSource().sendMessage(Component.text("Plugin '" + pluginId + "' enabled.").color(NamedTextColor.GREEN));
            return true;
        } catch (PluginNotFoundException e) {
            ctx.getSource().sendMessage(getNotFoundText(e.getPluginId()));
        } catch (IllegalPluginStateChangeException exception) {
            var message = Component.text("Plugin already enabled. (state: " + exception.getFrom() + ")")
                    .color(NamedTextColor.RED);
            ctx.getSource().sendMessage(message);
        } catch (Exception e) {
            ctx.getSource().sendMessage(getExceptionMessage(e));
            e.printStackTrace();
        }
        return false;
    }
    
}

package org.loomdev.loom.command;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.CommandManager;
import org.loomdev.api.command.tree.CommandNode;
import org.loomdev.api.command.tree.LiteralCommandNode;
import org.loomdev.api.command.tree.argument.ArgumentCommandNode;
import org.loomdev.api.plugin.exception.IllegalPluginStateException;
import org.loomdev.loom.server.ServerImpl;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;

import net.minecraft.commands.CommandSourceStack;

public class CommandManagerImpl implements CommandManager {

    private ServerImpl server;

    public CommandManagerImpl(ServerImpl server) {
        this.server = server;
    }
    
    @Override
    public void registerCommand(@NotNull CommandNode node) throws IllegalPluginStateException {
        server.getMinecraftServer().getCommands().getDispatcher().register(convert(node));
    }

    private LiteralArgumentBuilder<CommandSourceStack> convert(CommandNode node) {
        if (node instanceof LiteralCommandNode) {
            LiteralArgumentBuilder<CommandSourceStack> builder = LiteralArgumentBuilder.literal(((LiteralCommandNode) node).getLiteral());
            for(CommandNode childNode : node.getChildren()) {
                builder.then(convert(childNode));
            }
            return builder;
        } else if (node instanceof ArgumentCommandNode) {
//            RequiredArgumentBuilder.argument(((ArgumentCommandNode) node).getName(), );
        }
    }
    
    @Override
    public void unregisterAll() {
        
    }
    
}

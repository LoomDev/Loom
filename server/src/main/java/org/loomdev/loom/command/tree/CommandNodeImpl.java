package org.loomdev.loom.command.tree;

import java.util.Collection;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.CommandExecutor;
import org.loomdev.api.command.tree.CommandNode;
import org.loomdev.loom.command.CommandSourceImpl;
import org.loomdev.loom.server.ServerImpl;

import com.mojang.brigadier.context.CommandContextBuilder;

import net.minecraft.commands.CommandSourceStack;

public class CommandNodeImpl implements CommandNode {

    private ServerImpl server;
    private com.mojang.brigadier.tree.CommandNode<CommandSourceStack> node;
    
    public CommandNodeImpl(ServerImpl server, com.mojang.brigadier.tree.CommandNode<CommandSourceStack> node) {
        this.server = server;
        this.node = node;
    }
    
    @Override
    @NotNull
    public CommandExecutor getExecutor() {
    }

    @Override
    @NotNull
    public Collection<String> getAliases() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @NotNull
    public Collection<CommandNode> getChildren() {
        // TODO Auto-generated method stub
        return null;
    }

}

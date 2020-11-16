package org.loomdev.loom.command;

import net.kyori.adventure.text.Component;
import net.minecraft.commands.CommandSourceStack;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.CommandSource;
import org.loomdev.loom.util.transformer.TextTransformer;

public class CommandSourceImpl implements CommandSource {

    private final CommandSourceStack stack;

    public CommandSourceImpl(CommandSourceStack stack) {
        this.stack = stack;
    }

    @Override
    public void sendMessage(@NotNull String message) {
        sendMessage(Component.text(message));
    }

    @Override
    public void sendMessage(@NotNull Component message) {
        stack.sendSuccess(TextTransformer.toMinecraft(message), false);
    }
}

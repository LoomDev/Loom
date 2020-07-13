package org.loomdev.loom.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Util;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.command.CommandSource;
import org.loomdev.loom.util.transformer.TextTransformer;

public class ConsoleSource implements CommandSource {

    private final MinecraftServer minecraftServer;

    public ConsoleSource(MinecraftServer minecraftServer) {
        this.minecraftServer = minecraftServer;
    }

    @Override
    public void sendMessage(@NonNull String message) {
        minecraftServer.sendSystemMessage(TextTransformer.toMinecraft(TextComponent.of(message)), Util.NIL_UUID);
    }

    @Override
    public void sendMessage(@NonNull Component message) {
        minecraftServer.sendSystemMessage(TextTransformer.toMinecraft(message), Util.NIL_UUID);
    }
}

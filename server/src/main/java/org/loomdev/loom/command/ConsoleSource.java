package org.loomdev.loom.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.minecraft.Util;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.CommandSource;
import org.loomdev.loom.util.transformer.TextTransformer;

public class ConsoleSource implements CommandSource {

    private final MinecraftServer minecraftServer;

    public ConsoleSource(MinecraftServer minecraftServer) {
        this.minecraftServer = minecraftServer;
    }

    @Override
    public void sendMessage(@NotNull String message) {
        minecraftServer.sendMessage(TextTransformer.toMinecraft(TextComponent.of(message)), Util.NIL_UUID);
    }

    @Override
    public void sendMessage(@NotNull Component message) {
        minecraftServer.sendMessage(TextTransformer.toMinecraft(message), Util.NIL_UUID);
    }
}

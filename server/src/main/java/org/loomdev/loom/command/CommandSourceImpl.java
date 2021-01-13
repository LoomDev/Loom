package org.loomdev.loom.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.minecraft.Util;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.util.ChatColor;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.command.CommandSourceConsumable;
import org.loomdev.api.command.ConsoleCommandSource;
import org.loomdev.api.entity.player.Player;
import org.loomdev.loom.util.transformer.TextTransformer;

import java.util.function.Consumer;
import java.util.UUID;

public class CommandSourceImpl implements CommandSource {

    private final net.minecraft.commands.CommandSource source;
    private boolean playerConsumed;
    private boolean consoleConsumed;

    public CommandSourceImpl(net.minecraft.commands.CommandSource source) {
        this.source = source;
    }

    @Override
    public boolean isPlayer() {
        return this instanceof Player;
    }

    @Override
    public boolean isConsole() {
        return this instanceof ConsoleCommandSource;
    }

    @Override
    public CommandSourceConsumable ifPlayer(Consumer<Player> consumer) {
        if (isPlayer()) {
            consumer.accept((Player) this);
        }

        playerConsumed = true;
        return this;
    }

    @Override
    public CommandSourceConsumable ifConsole(Consumer<ConsoleCommandSource> consumer) {
        if (isConsole()) {
            consumer.accept((ConsoleCommandSource) this);
        }

        consoleConsumed = true;
        return this;
    }

    @Override
    public void orElse(Consumer<CommandSource> consumer) {
        if (isPlayer() && playerConsumed) {
            return;
        }

        if (isConsole() && consoleConsumed) {
            return;
        }

        playerConsumed = consoleConsumed = false;
        consumer.accept(this);
    }

    @Override
    public void sendMessage(@NotNull String message) {
        sendMessage(Component.text(message));
    }

    @Override
    public void sendMessage(@NotNull String message, @NotNull UUID uuid) {
        sendMessage(Component.text(message), uuid);
    }

    @Override
    public void sendMessage(@NotNull Component message) {
        source.sendMessage(TextTransformer.toMinecraft(message), Util.NIL_UUID);
    }

    @Override
    public void sendMessage(@NotNull Component message, @NotNull UUID uuid) {
        source.sendMessage(TextTransformer.toMinecraft(message), uuid);
    }

    @Override
    public void sendError(@NotNull String message) {
        sendError(Component.text(message));
    }

    @Override
    public void sendError(@NotNull String message, @NotNull UUID uuid) {
        sendError(Component.text(message), uuid);
    }

    @Override
    public void sendError(@NotNull Component message) {
        sendMessage(toError(message));
    }

    @Override
    @Deprecated
    public void sendError(@NotNull Component message, @NotNull UUID uuid) {
        sendMessage(toError(message), uuid);
    }

    private Component toError(Component component) {
        return Component.text("").append(component).color(ChatColor.RED);
    }

    @Override
    public boolean isOperator() {
        return isPlayer() && Loom.getPlayerManager().isOperator((Player) this);
    }
}

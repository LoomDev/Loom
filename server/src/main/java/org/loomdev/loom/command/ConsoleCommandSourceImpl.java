package org.loomdev.loom.command;

import net.kyori.adventure.text.Component;
import net.minecraft.commands.CommandSource;
import org.loomdev.api.command.ConsoleCommandSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.loomdev.loom.util.ChatToLegacyConverter;
import org.loomdev.loom.util.transformer.TextTransformer;

import java.util.UUID;

public class ConsoleCommandSourceImpl extends CommandSourceImpl implements ConsoleCommandSource {

    private static final Logger LOGGER = LogManager.getLogger();

    public ConsoleCommandSourceImpl(CommandSource source) {
        super(source);
    }

    @Override
    public void sendMessage(String message, UUID sender) {
        LOGGER.info(message);
    }

    @Override
    public void sendMessage(Component message, UUID sender) {
        LOGGER.info(ChatToLegacyConverter.toLegacy(message));
    }

    @Override
    public void sendError(String message) {
        LOGGER.error(message);
    }

    @Override
    public void sendError(String message, UUID sender) {
        sendError(message);
    }

    @Override
    public void sendError(Component message) {
        LOGGER.error(ChatToLegacyConverter.toLegacy(message));
    }

    @Override
    public void sendError(Component message, UUID sender) {
        sendError(message);
    }

    @Override
    public boolean isOperator() {
        return true;
    }
}

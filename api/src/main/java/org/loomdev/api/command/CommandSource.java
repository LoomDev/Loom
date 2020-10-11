package org.loomdev.api.command;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public interface CommandSource {

    void sendMessage(@NotNull String message);

    void sendMessage(@NotNull Component message);
}

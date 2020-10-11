package org.loomdev.api.block.entity;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.PersistentState;

import java.util.List;
import java.util.Optional;

public interface Sign extends PersistentState {

    @NotNull List<String> getLines();

    @NotNull Optional<String> getLine(int index);

    void setLine(int index, @NotNull String text);
}

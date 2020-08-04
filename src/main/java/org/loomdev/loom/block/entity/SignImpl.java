package org.loomdev.loom.block.entity;

import net.minecraft.block.entity.SignBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.block.Material;
import org.loomdev.api.block.entity.Sign;

import java.util.List;
import java.util.Optional;

public class SignImpl implements Sign {

    private final SignBlockEntity mcState;

    public SignImpl(@NotNull SignBlockEntity mcState) {
        this.mcState = mcState;
    }

    @Override
    public @NotNull List<String> getLines() {
        return null;
    }

    @Override
    public @NotNull Optional<String> getLine(int i) {
        return Optional.empty();
    }

    @Override
    public void setLine(int i, @NotNull String s) {

    }

    @Override
    public @NotNull Block getBlock() {
        return null;
    }

    @Override
    public @NotNull Material getType() {
        return null;
    }
}

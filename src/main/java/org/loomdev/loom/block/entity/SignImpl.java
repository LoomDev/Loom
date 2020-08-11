package org.loomdev.loom.block.entity;

import net.kyori.adventure.text.TextComponent;
import net.minecraft.block.entity.SignBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.block.entity.Sign;

import java.util.List;
import java.util.Optional;

public class SignImpl implements Sign {

    private final SignBlockEntity mcState;

    public SignImpl(@NotNull SignBlockEntity mcState) {
        this.mcState = mcState;

    }


    @Override
    public @NotNull List<TextComponent> getLines() {
        return null;
    }

    @Override
    public @NotNull Optional<TextComponent> getLine(int index) {
        return Optional.empty();
    }

    @Override
    public void setLine(int i, @NotNull String s) {

    }

    @Override
    public void setLine(int i, @NotNull TextComponent textComponent) {

    }

    @Override
    public @NotNull Block getBlock() {
        return null;
    }

    @Override
    public @NotNull BlockType getType() {
        return null;
    }
}

package org.loomdev.loom.item.property.data;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.item.property.data.NameData;

public class NameDataImpl implements NameData {

    private final Component itemName;
    private Component customName;
    private final Component hoverText;

    public NameDataImpl(@NotNull Component itemName, @Nullable Component customName, @NotNull Component hoverText) {
        this.itemName = itemName;
        this.customName = customName;
        this.hoverText = hoverText;
    }

    @Override
    public @NotNull Component getName() {
        return this.customName == null ? this.itemName : this.customName;
    }

    @Override
    public void setName(@NotNull Component component) {
        this.customName = component;
    }

    @Override
    public void removeCustomName() {
        this.customName = null;
    }

    @Override
    public boolean hasCustomName() {
        return this.customName != null;
    }

    @Override
    public @NotNull Component getHoverText() {
        return this.hoverText;
    }
}

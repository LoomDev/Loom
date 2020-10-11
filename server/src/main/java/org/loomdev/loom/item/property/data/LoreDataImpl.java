package org.loomdev.loom.item.property.data;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.item.property.data.LoreData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoreDataImpl implements LoreData {

    private List<Component> lore;

    public LoreDataImpl() {
        this.lore = new ArrayList<>();
    }

    public LoreDataImpl(@NotNull List<Component> lore) {
        this.lore = lore;
    }

    @Override
    public @NotNull List<Component> getLore() {
        return this.lore;
    }

    @Override
    public void setLore(@NotNull List<Component> lore) {
        this.lore = lore;
    }

    @Override
    public void appendLore(Component... components) {
        this.lore.addAll(Arrays.asList(components));
    }

    @Override
    public void removeLoreLine(int index) {
        if (index >= 0 && index < lore.size()) {
            lore.remove(index);
        }
    }
}

package org.loomdev.api.item.property.data;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * Lore data of {@link org.loomdev.api.item.ItemStack}s.
 */
public interface LoreData extends ItemPropertyData<LoreData> {

    /**
     * Get the lore of the {@link org.loomdev.api.item.ItemStack}.
     *
     * @return The lore of the {@link org.loomdev.api.item.ItemStack}.
     */
    @NotNull List<Component> getLore();

    /**
     * Set the lore of the {@link org.loomdev.api.item.ItemStack}.
     *
     * @param components The new lore of the {@link org.loomdev.api.item.ItemStack}.
     */
    void setLore(@NotNull List<Component> components);

    /**
     * Append extra lore to the current lore.
     *
     * @param lore The lore to add.
     */
    default void appendLore(String... lore) {
        Arrays.stream(lore).forEach(l -> appendLore(Component.text(l)));
    }

    /**
     * Append extra lore to the current lore.
     *
     * @param lore The lore to add.
     */
    void appendLore(Component... lore);

    /**
     * Remove a specific line from the lore.
     *
     * @param lineIndex The index of the line th remove.
     */
    void removeLoreLine(int lineIndex);
}

package org.loomdev.api.item.property.data;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;

/**
 * Naming data of {@link org.loomdev.api.item.ItemStack}s.
 */
public interface NameData extends ItemPropertyData<NameData> {

    /**
     * Get the current name of the {@link org.loomdev.api.item.ItemStack}.
     *
     * @return The current name.
     */
    @NotNull Component getName();

    /**
     * Change the name of the {@link org.loomdev.api.item.ItemStack}.
     *
     * @param name The new name.
     */
    default void setName(@NotNull String name) {
        setName(TextComponent.of(name));
    }

    /**
     * Change the name of the {@link org.loomdev.api.item.ItemStack}.
     *
     * @param name The new name.
     */
    void setName(@NotNull Component name);

    /**
     * Reset the name of the {@link org.loomdev.api.item.ItemStack} to the name of the item.
     */
    void removeCustomName();

    /**
     * Check whether the {@link org.loomdev.api.item.ItemStack} has a custom name.
     *
     * @return True if the names doesn't equal the default minecraft name.
     */
    boolean hasCustomName();

    /**
     * Get the chat hover text for the {@link org.loomdev.api.item.ItemStack}.
     *
     * @return The hover text.
     */
    @NotNull Component getHoverText();
}

package org.loomdev.api.item.property.data;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;

/**
 * Naming data of {@link ItemStack}s.
 */
public interface NameData extends ItemPropertyData<NameData> {

    /**
     * Gets the current name of the {@link ItemStack}.
     *
     * @return The current name.
     */
    @NotNull Component getName();

    /**
     * Changes the name of the {@link ItemStack}.
     *
     * @param name The new name.
     */
    default void setName(@NotNull String name) {
        setName(Component.text(name));
    }

    /**
     * Changes the name of the {@link ItemStack}.
     *
     * @param name The new name.
     */
    void setName(@NotNull Component name);

    /**
     * Resets the name of the {@link ItemStack} to the name of the {@link ItemStack}.
     */
    void removeCustomName();

    /**
     * Checks whether the {@link ItemStack} has a custom name.
     *
     * @return {@code true} if the name isn't equal to the default minecraft name.
     */
    boolean hasCustomName();

    /**
     * Gets the chat hover text for the {@link ItemStack}.
     *
     * @return The hover text.
     */
    @NotNull Component getHoverText();
}

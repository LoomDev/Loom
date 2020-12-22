package org.loomdev.api.item.property.data;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;

/**
 * Naming data of item stacks.
 */
public interface NameData extends ItemPropertyData<NameData> {

    /**
     * Gets the current name of the item stack.
     *
     * @return The current name.
     */
    @NotNull Component getName();

    /**
     * Changes the name of the item stack.
     *
     * @param name The new name.
     */
    default void setName(@NotNull String name) {
        setName(Component.text(name));
    }

    /**
     * Changes the name of the item stack.
     *
     * @param name The new name.
     */
    void setName(@NotNull Component name);

    /**
     * Resets the name of the item stack to the name of the item.
     */
    void removeCustomName();

    /**
     * Checks whether the item stack has a custom name.
     *
     * @return True if the names doesn't equal the default minecraft name.
     */
    boolean hasCustomName();

    /**
     * Gets the chat hover text for the item stack.
     *
     * @return The hover text.
     */
    @NotNull Component getHoverText();
}

package org.loomdev.api.entity.projectile;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.item.ItemStack;

public interface Fireball extends AbstractFireball {

    @NotNull
    ItemStack getItem();

    void setItem(@NotNull ItemStack item);
}

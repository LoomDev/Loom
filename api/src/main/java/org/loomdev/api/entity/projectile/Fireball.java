package org.loomdev.api.entity.projectile;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.item.ItemStack;

public interface Fireball extends ExplosiveProjectile {

    @NotNull ItemStack getItemStack();

    void setItemStack(@NotNull ItemStack itemStack);
}

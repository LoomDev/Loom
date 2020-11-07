package org.loomdev.api.entity.item;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.item.ItemStack;

import java.util.Optional;
import java.util.UUID;

public interface ItemEntity extends Entity {

    @Nullable
    UUID getOwner();

    void setOwner(@Nullable UUID uuid);

    @Nullable
    UUID getThrower();

    void setThrower(@Nullable UUID uuid);

    @NotNull
    ItemStack getItemStack();

    void setItemStack(@NotNull ItemStack itemstack);

    int getPickupDelay();

    void setPickupDelay(int ticks);
}

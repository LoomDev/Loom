package org.loomdev.api.entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.item.ItemStack;

import java.util.Optional;
import java.util.UUID;

public interface Item extends Entity {

    @NotNull Optional<UUID> getOwner();

    void setOwner(@Nullable UUID uuid);

    @NotNull Optional<UUID> getThrower();

    void setThrower(@Nullable UUID uuid);

    @NotNull ItemStack getItemStack();

    void setItemStack(@NotNull ItemStack itemstack);

    int getPickupDelay();

    void setPickupDelay(int ticks);
}

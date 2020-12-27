package org.loomdev.loom.entity.item;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.item.ItemEntity;
import org.loomdev.api.item.ItemStack;
import org.loomdev.loom.entity.EntityImpl;
import org.loomdev.loom.item.ItemStackImpl;

import java.util.Optional;
import java.util.UUID;

public class ItemEntityImpl extends EntityImpl implements ItemEntity {

    public ItemEntityImpl(net.minecraft.world.entity.item.ItemEntity entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<ItemEntity> getType() {
        return EntityType.ITEM;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.item.ItemEntity getMinecraftEntity() {
        return (net.minecraft.world.entity.item.ItemEntity) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public Optional<UUID> getOwner() {
        return Optional.ofNullable(getMinecraftEntity().getOwner());
    }

    @Override
    public void setOwner(@Nullable UUID uuid) {
        getMinecraftEntity().setOwner(uuid);
    }

    @Override
    @NotNull
    public Optional<UUID> getThrower() {
        return Optional.ofNullable(getMinecraftEntity().getThrower());
    }

    @Override
    public void setThrower(@Nullable UUID uuid) {
        getMinecraftEntity().setThrower(uuid);
    }

    @Override
    @NotNull
    public ItemStack getItemStack() {
        return ItemStackImpl.of(getMinecraftEntity().getItem());
    }

    @Override
    public void setItemStack(@NotNull ItemStack item) {
        getMinecraftEntity().setItem(((ItemStackImpl) item).getMinecraftItemStack());
    }

    @Override
    public int getPickupDelay() {
        return getMinecraftEntity().pickupDelay;
    }

    @Override
    public void setPickupDelay(int ticks) {
        getMinecraftEntity().setPickUpDelay(ticks);
    }
}

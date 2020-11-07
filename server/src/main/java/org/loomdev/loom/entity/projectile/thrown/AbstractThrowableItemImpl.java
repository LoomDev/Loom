package org.loomdev.loom.entity.projectile.thrown;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.projectile.thrown.ThrowableItem;
import org.loomdev.api.item.ItemStack;
import org.loomdev.loom.entity.projectile.AbstractProjectileImpl;
import org.loomdev.loom.item.ItemStackImpl;

public abstract class AbstractThrowableItemImpl extends AbstractProjectileImpl implements ThrowableItem {

    public AbstractThrowableItemImpl(ThrowableItemProjectile entity) {
        super(entity);
    }

    @Override
    @NotNull
    public ThrowableItemProjectile getMinecraftEntity() {
        return (ThrowableItemProjectile) super.getMinecraftEntity();
    }

    @Override
    public ItemStack getItem() {
        return ItemStackImpl.of(getMinecraftEntity().entityData.get(ItemEntity.DATA_ITEM));
    }

    @Override
    public void setItem(ItemStack itemStack) {
        getMinecraftEntity().setItem(((ItemStackImpl) itemStack).getMinecraftItemStack());
    }
}

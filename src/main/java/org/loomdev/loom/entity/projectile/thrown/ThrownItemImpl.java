package org.loomdev.loom.entity.projectile.thrown;

import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.projectile.thrown.ThrownItem;
import org.loomdev.api.item.ItemStack;
import org.loomdev.loom.entity.projectile.ProjectileImpl;
import org.loomdev.loom.item.ItemStackImpl;

public abstract class ThrownItemImpl extends ProjectileImpl implements ThrownItem {

    public ThrownItemImpl(ThrownItemEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull ThrownItemEntity getMinecraftEntity() {
        return (ThrownItemEntity) super.getMinecraftEntity();
    }

    @Override
    public ItemStack getItem() {
        return ItemStackImpl.ofMcStack(getMinecraftEntity().dataTracker.get(ThrownItemEntity.ITEM));
    }

    @Override
    public void setItem(ItemStack itemStack) {
        getMinecraftEntity().setItem(((ItemStackImpl) itemStack).getMinecraftItemStack());
    }
}

package org.loomdev.loom.entity.projectile;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.projectile.Fireball;
import org.loomdev.api.item.ItemStack;
import org.loomdev.loom.item.ItemStackImpl;

public abstract class AbstractFireballImpl extends AbstractProjectileImpl implements Fireball {

    public AbstractFireballImpl(net.minecraft.world.entity.projectile.Fireball entity) {
        super(entity);
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.projectile.Fireball getMinecraftEntity() {
        return (net.minecraft.world.entity.projectile.Fireball) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        return ItemStackImpl.of(getMinecraftEntity().entityData.get(net.minecraft.world.entity.projectile.Fireball.DATA_ITEM_STACK));
    }

    @Override
    public void setItem(@NotNull ItemStack itemStack) {
        getMinecraftEntity().setItem(((ItemStackImpl) itemStack).getMinecraftItemStack());
    }
}

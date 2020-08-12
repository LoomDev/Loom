package org.loomdev.loom.entity.projectile;

import net.minecraft.entity.projectile.AbstractFireballEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.projectile.SizedFireball;
import org.loomdev.api.item.ItemStack;
import org.loomdev.loom.item.ItemStackImpl;

public class SizedFireballImpl extends ProjectileImpl implements SizedFireball {

    public SizedFireballImpl(AbstractFireballEntity entity) {
        super(entity);
    }

    @Override
    public AbstractFireballEntity getMinecraftEntity() {
        return (AbstractFireballEntity) super.getMinecraftEntity();
    }

    @Override
    public @NotNull ItemStack getItemStack() {
        return ItemStackImpl.ofMcStack(getMinecraftEntity().dataTracker.get(AbstractFireballEntity.ITEM));
    }

    @Override
    public void setItemStack(@NotNull ItemStack itemStack) {
        getMinecraftEntity().setItem(((ItemStackImpl) itemStack).getMinecraftItemStack());
    }
}

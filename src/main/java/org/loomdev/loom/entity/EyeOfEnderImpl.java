package org.loomdev.loom.entity;

import net.minecraft.entity.EyeOfEnderEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.EyeOfEnder;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.math.Vector3d;
import org.loomdev.loom.item.ItemStackImpl;

public class EyeOfEnderImpl extends EntityImpl implements EyeOfEnder {

    public EyeOfEnderImpl(EyeOfEnderEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType<EyeOfEnder> getType() {
        return EntityType.EYE_OF_ENDER;
    }

    @NotNull
    @Override
    public EyeOfEnderEntity getMinecraftEntity() {
        return (EyeOfEnderEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean getDropsItem() {
        return getMinecraftEntity().dropsItem;
    }

    @Override
    public void setDropsItem(boolean dropsItem) {
        getMinecraftEntity().dropsItem = dropsItem;
    }

    @Override
    public @NotNull Vector3d getTarget() {
        return new Vector3d(getMinecraftEntity().targetX, getMinecraftEntity().targetY, getMinecraftEntity().targetZ);
    }

    @Override
    public void setTarget(@NotNull Vector3d vector3d) {
        getMinecraftEntity().targetX = vector3d.getX();
        getMinecraftEntity().targetY = vector3d.getY();
        getMinecraftEntity().targetZ = vector3d.getZ();
    }

    @Override
    public @NotNull ItemStack getItem() {
        return ItemStackImpl.ofMcStack(getMinecraftEntity().getTrackedItem());
    }

    @Override
    public void setItem(@NotNull ItemStack itemStack) {
        getMinecraftEntity().setItem(((ItemStackImpl) itemStack).getMinecraftItemStack());
    }
}

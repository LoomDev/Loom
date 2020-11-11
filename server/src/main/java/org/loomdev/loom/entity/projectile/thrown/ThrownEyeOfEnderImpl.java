package org.loomdev.loom.entity.projectile.thrown;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.thrown.ThrownEyeOfEnder;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.math.vector.Vector3d;
import org.loomdev.loom.entity.EntityImpl;
import org.loomdev.loom.item.ItemStackImpl;

public class ThrownEyeOfEnderImpl extends EntityImpl implements ThrownEyeOfEnder {

    public ThrownEyeOfEnderImpl(net.minecraft.world.entity.projectile.EyeOfEnder entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<ThrownEyeOfEnder> getType() {
        return EntityType.EYE_OF_ENDER;
    }

    @NotNull
    @Override
    public net.minecraft.world.entity.projectile.EyeOfEnder getMinecraftEntity() {
        return (net.minecraft.world.entity.projectile.EyeOfEnder) super.getMinecraftEntity();
    }

    @Override
    public boolean getDropsItem() {
        return getMinecraftEntity().surviveAfterDeath;
    }

    @Override
    public void setDropsItem(boolean dropsItem) {
        getMinecraftEntity().surviveAfterDeath = dropsItem;
    }

    @Override
    @NotNull
    public Vector3d getTarget() {
        return new Vector3d(getMinecraftEntity().tx, getMinecraftEntity().ty, getMinecraftEntity().tz);
    }

    @Override
    public void setTarget(@NotNull Vector3d vector3d) {
        getMinecraftEntity().tx = vector3d.getX();
        getMinecraftEntity().ty = vector3d.getY();
        getMinecraftEntity().tz = vector3d.getZ();
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        return ItemStackImpl.of(getMinecraftEntity().getItemRaw());
    }

    @Override
    public void setItem(@NotNull ItemStack itemStack) {
        getMinecraftEntity().setItem(((ItemStackImpl) itemStack).getMinecraftItemStack());
    }
}

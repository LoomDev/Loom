package org.loomdev.loom.entity.projectile;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.projectile.AbstractArrow;
import org.loomdev.api.item.ItemStack;
import org.loomdev.loom.item.ItemStackImpl;

public abstract class AbstractArrowImpl extends AbstractProjectileImpl implements AbstractArrow {

    public AbstractArrowImpl(net.minecraft.world.entity.projectile.AbstractArrow entity) {
        super(entity);
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.projectile.AbstractArrow getMinecraftEntity() {
        return (net.minecraft.world.entity.projectile.AbstractArrow) super.getMinecraftEntity();
    }

    @Override
    public boolean isInBlock() {
        return getMinecraftEntity().inGround;
    }

    @Override
    public double getDamage() {
        return getMinecraftEntity().getBaseDamage();
    }

    @Override
    public void setDamage(double damage) {
        getMinecraftEntity().setBaseDamage(damage);
    }

    @Override
    public float getWaterIntertia() {
        return getMinecraftEntity().waterInertia;
    }

    @Override
    public void setWaterIntertia(float intertia) {
        getMinecraftEntity().waterInertia = intertia;
    }

    @Override
    @NotNull
    public PickupPermission getPickupPermission() {
        return PickupPermission.fromOrdinal(getMinecraftEntity().pickup.ordinal());
    }

    @Override
    public void setPickupPermission(@NotNull PickupPermission pickupPermission) {
        getMinecraftEntity().pickup = net.minecraft.world.entity.projectile.AbstractArrow.Pickup.byOrdinal(pickupPermission.ordinal());
    }

    @Override
    public boolean isShotFromCrossbow() {
        return getMinecraftEntity().shotFromCrossbow();
    }

    @Override
    public void setShotFromCrossbow(boolean flag) {
        getMinecraftEntity().setShotFromCrossbow(flag);
    }

    @Override
    public boolean isNoClip() {
        return getMinecraftEntity().isNoPhysics();
    }

    @Override
    public void setNoClip(boolean flag) {
        getMinecraftEntity().setNoPhysics(flag);
    }

    @Override
    public byte getPierceLevel() {
        return getMinecraftEntity().getPierceLevel();
    }

    @Override
    public void setPierceLevel(byte level) {
        getMinecraftEntity().setPierceLevel(level);
    }

    @Override
    public boolean isCritical() {
        return getMinecraftEntity().isCritArrow();
    }

    @Override
    public void setCritical(boolean flag) {
        getMinecraftEntity().setCritArrow(flag);
    }

    @Override
    public int getKnockback() {
        return getMinecraftEntity().knockback;
    }

    @Override
    public void setKnockback(int knockback) {
        getMinecraftEntity().knockback = knockback;
    }

    @Override
    public ItemStack asItemStack() {
        return ItemStackImpl.of(getMinecraftEntity().getPickupItem());
    }
}

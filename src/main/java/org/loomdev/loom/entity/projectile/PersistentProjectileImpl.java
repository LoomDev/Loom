package org.loomdev.loom.entity.projectile;

import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.projectile.PersistentProjectile;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.sound.Sound;

public class PersistentProjectileImpl extends ProjectileImpl implements PersistentProjectile {

    public PersistentProjectileImpl(PersistentProjectileEntity entity) {
        super(entity);
    }

    @Override
    public PersistentProjectileEntity getMinecraftEntity() {
        return (PersistentProjectileEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean isInBlock() {
        return getMinecraftEntity().inGround;
    }

    @Override
    public double getDamage() {
        return getMinecraftEntity().getDamage();
    }

    @Override
    public void setDamage(double damage) {
        getMinecraftEntity().setDamage(damage);
    }

    @Override
    public float getDragInWater() {
        return getMinecraftEntity().dragInWater;
    }

    @Override
    public void setDragInWater(float drag) {
        getMinecraftEntity().dragInWater = drag;
    }

    @Override
    public @NotNull PickupPermission getPickupPermission() {
        return PickupPermission.fromOrdinal(getMinecraftEntity().pickupType.ordinal());
    }

    @Override
    public void setPickupPermission(@NotNull PickupPermission pickupPermission) {
        getMinecraftEntity().pickupType = PersistentProjectileEntity.PickupPermission.fromOrdinal(pickupPermission.ordinal());
    }

    @Override
    public boolean isShotFromCrossbow() {
        return getMinecraftEntity().isShotFromCrossbow();
    }

    @Override
    public void setShotFromCrossbow(boolean flag) {
        getMinecraftEntity().setShotFromCrossbow(flag);
    }

    @Override
    public boolean isNoClip() {
        return getMinecraftEntity().isNoClip();
    }

    @Override
    public void setNoClip(boolean flag) {
        getMinecraftEntity().setNoClip(flag);
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
        return getMinecraftEntity().isCritical();
    }

    @Override
    public void setCritical(boolean flag) {
        getMinecraftEntity().setCritical(flag);
    }

    @Override
    public int getPunch() {
        return getMinecraftEntity().punch;
    }

    @Override
    public void setPunch(int punch) {
        getMinecraftEntity().setPunch(punch);
    }

    @Override
    public ItemStack asItemStack() {
        return null; // TODO
    }

    @Override
    public Sound.Type getSound() {
        return Sound.Type.getByRawId(Registry.SOUND_EVENT.getRawId(getMinecraftEntity().sound)).orElse(null);
    }

    @Override
    public void setSound(Sound.Type sound) {
        getMinecraftEntity().sound = Registry.SOUND_EVENT.get(sound.rawId());
    }
}

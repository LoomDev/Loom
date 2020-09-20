package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Hoglin;
import org.loomdev.loom.entity.passive.AnimalEntityImpl;

public abstract class HoglinImpl extends AnimalEntityImpl implements Hoglin {

    public HoglinImpl(HoglinEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.HOGLIN;
    }

    @Override
    public @NotNull HoglinEntity getMinecraftEntity() {
        return (HoglinEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean isImmuneToZombification() {
        return getMinecraftEntity().isImmuneToZombification();
    }

    @Override
    public void setImmuneToZombification(boolean flag) {
        getMinecraftEntity().setImmuneToZombification(flag);
    }

    @Override
    public boolean isHuntable() {
        return getMinecraftEntity().canBeHunted();
    }

    @Override
    public void setHuntable(boolean flag) {
        getMinecraftEntity().setCannotBeHunted(!flag);
    }

    @Override
    public int getConversionTime() {
        return HoglinEntity.CONVERSION_TIME - getMinecraftEntity().timeInOverworld;
    }

    @Override
    public void setConversionTime(int ticks) {
        getMinecraftEntity().timeInOverworld = HoglinEntity.CONVERSION_TIME - ticks;
    }

    @Override
    public boolean isConverting() {
        return getMinecraftEntity().timeInOverworld > 0;
    }

    @Override
    public void convert() {
        getMinecraftEntity().zombify((ServerWorld) getMinecraftEntity().world);
    }
}

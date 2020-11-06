package org.loomdev.loom.entity.monster.piglin;

import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.mob.AbstractPiglin;
import org.loomdev.loom.entity.monster.MonsterImpl;

public abstract class AbstractPiglinImpl extends MonsterImpl implements AbstractPiglin {

    public AbstractPiglinImpl(net.minecraft.world.entity.monster.piglin.AbstractPiglin entity) {
        super(entity);
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.piglin.AbstractPiglin getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.piglin.AbstractPiglin) super.getMinecraftEntity();
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
    public int getConversionTime() {
        return net.minecraft.world.entity.monster.piglin.AbstractPiglin.CONVERSION_TIME - getMinecraftEntity().timeInOverworld;
    }

    @Override
    public void setConversionTime(int ticks) {
        getMinecraftEntity().timeInOverworld = net.minecraft.world.entity.monster.piglin.AbstractPiglin.CONVERSION_TIME - ticks;
    }

    @Override
    public boolean isConverting() {
        return getConversionTime() > 0; // TODO check
    }

    @Override
    public void convert() {
        getMinecraftEntity().finishConversion((ServerLevel) getMinecraftEntity().level);
    }
}

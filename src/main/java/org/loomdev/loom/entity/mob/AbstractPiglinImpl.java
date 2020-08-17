package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.AbstractPiglinEntity;
import org.loomdev.api.entity.mob.AbstractPiglin;

public class AbstractPiglinImpl extends HostileEntityImpl implements AbstractPiglin {

    public AbstractPiglinImpl(AbstractPiglinEntity entity) {
        super(entity);
    }

    @Override
    public AbstractPiglinEntity getMinecraftEntity() {
        return (AbstractPiglinEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean isImmuneToZombification() {
        return getMinecraftEntity().dataTracker.get(AbstractPiglinEntity.IMMUNE_TO_ZOMBIFICATION);
    }

    @Override
    public void setImmuneToZombification(boolean flag) {
        getMinecraftEntity().setImmuneToZombification(flag);
    }

    @Override
    public int getConversionTime() {
        return AbstractPiglinEntity.CONVERSION_TIME - getMinecraftEntity().timeInOverworld;
    }

    @Override
    public void setConversionTime(int ticks) {
        getMinecraftEntity().timeInOverworld = AbstractPiglinEntity.CONVERSION_TIME - ticks;
    }

    @Override
    public boolean isConverting() {
        return getConversionTime() > 0; // TODO check
    }

    @Override
    public void convert(boolean flag) {
        getMinecraftEntity().loomZombify();
    }
}

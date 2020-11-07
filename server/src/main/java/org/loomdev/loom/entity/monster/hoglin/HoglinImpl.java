package org.loomdev.loom.entity.monster.hoglin;

import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.monster.hoglin.Hoglin;
import org.loomdev.loom.entity.animal.AnimalImpl;

public abstract class HoglinImpl extends AnimalImpl implements Hoglin {

    public HoglinImpl(net.minecraft.world.entity.monster.hoglin.Hoglin entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Hoglin> getType() {
        return EntityType.HOGLIN;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.hoglin.Hoglin getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.hoglin.Hoglin) super.getMinecraftEntity();
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
        return net.minecraft.world.entity.monster.hoglin.Hoglin.CONVERSION_TIME - getMinecraftEntity().timeInOverworld;
    }

    @Override
    public void setConversionTime(int ticks) {
        getMinecraftEntity().timeInOverworld = net.minecraft.world.entity.monster.hoglin.Hoglin.CONVERSION_TIME - ticks;
    }

    @Override
    public boolean isConverting() {
        return getMinecraftEntity().timeInOverworld > 0;
    }

    @Override
    public void convert() {
        getMinecraftEntity().finishConversion((ServerLevel) getMinecraftEntity().level);
    }
}

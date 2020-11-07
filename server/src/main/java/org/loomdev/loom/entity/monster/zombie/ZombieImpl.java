package org.loomdev.loom.entity.monster.zombie;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.monster.zombie.Zombie;
import org.loomdev.loom.entity.monster.MonsterImpl;

public class ZombieImpl extends MonsterImpl implements Zombie {

    public ZombieImpl(net.minecraft.world.entity.monster.Zombie entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<? extends Zombie> getType() {
        return EntityType.ZOMBIE;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.Zombie getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Zombie) super.getMinecraftEntity();
    }

    @Override
    public boolean canBreakDoors() {
        return getMinecraftEntity().canBreakDoors();
    }

    @Override
    public void setCanBreakDoors(boolean b) {
        getMinecraftEntity().setCanBreakDoors(b);
    }

    @Override
    public boolean isBaby() {
        return getMinecraftEntity().isBaby();
    }

    @Override
    public void setBaby(boolean b) {
        getMinecraftEntity().setBaby(b);
    }

    @Override
    public int getConversionTime() {
        return getMinecraftEntity().conversionTime;
    }

    @Override
    public void setConversionTime(int i) {
        getMinecraftEntity().conversionTime = i;
    }

    @Override
    public boolean isConverting() {
        return getMinecraftEntity().conversionTime > 0;
    }

    @Override
    public void convert() {
        getMinecraftEntity().doUnderWaterConversion();
    }
}

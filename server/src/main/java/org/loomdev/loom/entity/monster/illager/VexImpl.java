package org.loomdev.loom.entity.monster.illager;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.monster.illager.Vex;
import org.loomdev.loom.entity.monster.MonsterImpl;

public class VexImpl extends MonsterImpl implements Vex {

    public VexImpl(net.minecraft.world.entity.monster.Vex entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Vex> getType() {
        return EntityType.VEX;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.Vex getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Vex) super.getMinecraftEntity();
    }

    @Override
    public boolean isCharging() {
        return getMinecraftEntity().isCharging();
    }

    @Override
    public void setCharging(boolean charging) {
        getMinecraftEntity().setIsCharging(charging);
    }
}

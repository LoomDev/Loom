package org.loomdev.loom.entity.monster;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.MagmaCube;

public class MagmaCubeImpl extends SlimeImpl implements MagmaCube {

    public MagmaCubeImpl(net.minecraft.world.entity.monster.MagmaCube entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<MagmaCube> getType() {
        return EntityType.MAGMA_CUBE;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.MagmaCube getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.MagmaCube) super.getMinecraftEntity();
    }
}

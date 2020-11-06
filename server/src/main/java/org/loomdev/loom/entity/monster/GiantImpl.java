package org.loomdev.loom.entity.monster;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Giant;

public class GiantImpl extends MonsterImpl implements Giant {

    public GiantImpl(net.minecraft.world.entity.monster.Giant entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Giant> getType() {
        return EntityType.GIANT;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.Giant getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Giant) super.getMinecraftEntity();
    }
}

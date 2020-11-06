package org.loomdev.loom.entity.monster;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.mob.Monster;
import org.loomdev.loom.entity.PathfinderMob;

public abstract class MonsterImpl extends PathfinderMob implements Monster {

    public MonsterImpl(net.minecraft.world.entity.monster.Monster entity) {
        super(entity);
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.Monster getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Monster) super.getMinecraftEntity();
    }
}

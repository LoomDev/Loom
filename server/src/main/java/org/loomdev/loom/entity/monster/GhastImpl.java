package org.loomdev.loom.entity.monster;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Enemy;
import org.loomdev.api.entity.mob.Ghast;

public class GhastImpl extends MobEntityImpl implements Ghast, Enemy {

    public GhastImpl(net.minecraft.world.entity.monster.Ghast entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Ghast> getType() {
        return EntityType.GHAST;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.Ghast getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Ghast) super.getMinecraftEntity();
    }
}

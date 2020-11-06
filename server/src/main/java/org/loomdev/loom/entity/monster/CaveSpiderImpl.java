package org.loomdev.loom.entity.monster;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.CaveSpider;
import org.loomdev.loom.entity.monster.SpiderImpl;

public class CaveSpiderImpl extends SpiderImpl implements CaveSpider {

    public CaveSpiderImpl(net.minecraft.world.entity.monster.CaveSpider entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<CaveSpider> getType() {
        return EntityType.CAVE_SPIDER;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.CaveSpider getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.CaveSpider) super.getMinecraftEntity();
    }
}

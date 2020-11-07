package org.loomdev.loom.entity.monster;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.monster.Spider;

public class SpiderImpl extends MonsterImpl implements Spider {

    public SpiderImpl(net.minecraft.world.entity.monster.Spider entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<? extends Spider> getType() {
        return EntityType.SPIDER;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.Spider getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Spider) super.getMinecraftEntity();
    }

    @Override
    public boolean isClimbing() {
        return getMinecraftEntity().isClimbing();
    }

    @Override
    public void setClimbing(boolean climbing) {
        getMinecraftEntity().setClimbing(climbing);
    }
}

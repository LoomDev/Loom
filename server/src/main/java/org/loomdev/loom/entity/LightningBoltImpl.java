package org.loomdev.loom.entity;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.misc.LightningBolt;

public class LightningBoltImpl extends EntityImpl implements LightningBolt {

    public LightningBoltImpl(net.minecraft.world.entity.LightningBolt entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType<LightningBolt> getType() {
        return EntityType.LIGHTNING_BOLT;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.LightningBolt getMinecraftEntity() {
        return (net.minecraft.world.entity.LightningBolt) super.getMinecraftEntity();
    }

    @Override
    public boolean isVisualOnly() {
        return getMinecraftEntity().visualOnly;
    }
}

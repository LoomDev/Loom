package org.loomdev.loom.entity.passive;

import net.minecraft.entity.mob.WaterCreatureEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.mob.WaterCreature;
import org.loomdev.loom.entity.mob.PathAwareEntityImpl;

public class WaterCreatureImpl extends PathAwareEntityImpl implements WaterCreature {

    public WaterCreatureImpl(WaterCreatureEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull WaterCreatureEntity getMinecraftEntity() {
        return (WaterCreatureEntity) super.getMinecraftEntity();
    }
}

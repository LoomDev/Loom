package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.MagmaCubeEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.MagmaCube;

public class MagmaCubeImpl extends SlimeImpl implements MagmaCube {

    public MagmaCubeImpl(MagmaCubeEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.MAGMA_CUBE;
    }

    @Override
    public MagmaCubeEntity getMinecraftEntity() {
        return (MagmaCubeEntity) super.getMinecraftEntity();
    }
}

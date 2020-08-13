package org.loomdev.loom.entity.decoration;

import net.minecraft.entity.decoration.LeashKnotEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.decoration.LeashKnot;

public class LeashKnotImpl extends DecorationEntityImpl implements LeashKnot {

    public LeashKnotImpl(LeashKnotEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.LEASH_KNOT;
    }

    @Override
    public LeashKnotEntity getMinecraftEntity() {
        return (LeashKnotEntity) super.getMinecraftEntity();
    }
}

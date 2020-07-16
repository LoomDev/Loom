package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.EvokerEntity;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Evoker;

public class EvokerImpl extends SpellcastingIllagerImpl implements Evoker {

    public EvokerImpl(EvokerEntity entity) {
        super(entity);
    }

    @Override
    public @NonNull EntityType getType() {
        return EntityType.EVOKER;
    }

    @Override
    public EvokerEntity getMinecraftEntity() {
        return (EvokerEntity) super.getMinecraftEntity();
    }
}

package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.EvokerEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Evoker;

public class EvokerImpl extends SpellcastingIllagerImpl implements Evoker {

    public EvokerImpl(EvokerEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.EVOKER;
    }

    @Override
    public EvokerEntity getMinecraftEntity() {
        return (EvokerEntity) super.getMinecraftEntity();
    }
}

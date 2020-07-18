package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.IllusionerEntity;
import net.minecraft.entity.mob.SpellcastingIllagerEntity;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Illusioner;

public class IllusionerImpl extends SpellcastingIllagerImpl implements Illusioner {
    public IllusionerImpl(IllusionerEntity entity) {
        super(entity);
    }

    @Override
    public @NonNull EntityType getType() {
        return EntityType.ILLUSIONER;
    }

    @Override
    public SpellcastingIllagerEntity getMinecraftEntity() {
        return (IllusionerEntity) super.getMinecraftEntity();
    }
}

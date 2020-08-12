package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.IllusionerEntity;
import net.minecraft.entity.mob.SpellcastingIllagerEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Illusioner;

public class IllusionerImpl extends SpellcastingIllagerImpl implements Illusioner {
    public IllusionerImpl(IllusionerEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ILLUSIONER;
    }

    @Override
    public SpellcastingIllagerEntity getMinecraftEntity() {
        return (IllusionerEntity) super.getMinecraftEntity();
    }
}

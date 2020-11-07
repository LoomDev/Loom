package org.loomdev.loom.entity.ambient;

import net.minecraft.world.entity.ambient.AmbientCreature;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.ambient.AmbientEntity;
import org.loomdev.loom.entity.monster.MobEntityImpl;

public abstract class AmbientEntityImpl extends MobEntityImpl implements AmbientEntity {

    public AmbientEntityImpl(AmbientCreature entity) {
        super(entity);
    }

    @Override
    @NotNull
    public AmbientCreature getMinecraftEntity() {
        return (AmbientCreature) super.getMinecraftEntity();
    }
}

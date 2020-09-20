package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.IllagerEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.mob.Illager;
import org.loomdev.loom.entity.raid.RaiderImpl;

public abstract class IllagerImpl extends RaiderImpl implements Illager {

    public IllagerImpl(IllagerEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull IllagerEntity getMinecraftEntity() {
        return (IllagerEntity) super.getMinecraftEntity();
    }
}

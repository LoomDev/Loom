package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.WitchEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Witch;
import org.loomdev.loom.entity.raid.RaiderImpl;

public class WitchImpl extends RaiderImpl implements Witch {

    public WitchImpl(WitchEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.WITCH;
    }

    @Override
    public @NotNull WitchEntity getMinecraftEntity() {
        return (WitchEntity) super.getMinecraftEntity();
    }
}

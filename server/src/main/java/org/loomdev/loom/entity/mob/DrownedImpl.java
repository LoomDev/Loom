package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.DrownedEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Drowned;

public abstract class DrownedImpl extends ZombieImpl implements Drowned {

    public DrownedImpl(DrownedEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.DROWNED;
    }

    @Override
    public @NotNull DrownedEntity getMinecraftEntity() {
        return (DrownedEntity) super.getMinecraftEntity();
    }
}

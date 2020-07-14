package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.DrownedEntity;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Drowned;

public class DrownedImpl extends ZombieImpl implements Drowned {

    public DrownedImpl(DrownedEntity entity) {
        super(entity);
    }

    @Override
    public @NonNull EntityType getType() {
        return EntityType.DROWNED;
    }

    @Override
    public DrownedEntity getMinecraftEntity() {
        return (DrownedEntity) super.getMinecraftEntity();
    }
}

package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.ZombieEntity;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Zombie;

public class ZombieImpl extends HostileEntityImpl implements Zombie {
    public ZombieImpl(ZombieEntity entity) {
        super(entity);
    }

    @Override
    public @NonNull EntityType getType() {
        return EntityType.ZOMBIE;
    }

    @Override
    public ZombieEntity getMinecraftEntity() {
        return (ZombieEntity) super.getMinecraftEntity();
    }
}

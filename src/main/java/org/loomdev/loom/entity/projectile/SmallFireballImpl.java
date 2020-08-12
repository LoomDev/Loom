package org.loomdev.loom.entity.projectile;

import net.minecraft.entity.projectile.SmallFireballEntity;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.SmallFireball;

public class SmallFireballImpl extends SizedFireballImpl implements SmallFireball {
    public SmallFireballImpl(SmallFireballEntity entity) {
        super(entity);
    }

    @Override
    public @NonNull EntityType getType() {
        return EntityType.SMALL_FIREBALL;
    }

    @Override
    public SmallFireballEntity getMinecraftEntity() {
        return (SmallFireballEntity) super.getMinecraftEntity();
    }
}

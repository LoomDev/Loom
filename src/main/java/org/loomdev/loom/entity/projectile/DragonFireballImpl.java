package org.loomdev.loom.entity.projectile;

import net.minecraft.entity.projectile.DragonFireballEntity;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.DragonFireball;

public class DragonFireballImpl extends ProjectileImpl implements DragonFireball {

    public DragonFireballImpl(DragonFireballEntity entity) {
        super(entity);
    }

    @Override
    public @NonNull EntityType getType() {
        return EntityType.DRAGON_FIREBALL;
    }

    @Override
    public DragonFireballEntity getMinecraftEntity() {
        return (DragonFireballEntity) super.getMinecraftEntity();
    }
}

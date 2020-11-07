package org.loomdev.loom.entity.projectile;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.DragonFireball;

public class DragonFireballImpl extends AbstractProjectileImpl implements DragonFireball {

    public DragonFireballImpl(net.minecraft.world.entity.projectile.DragonFireball entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<DragonFireball> getType() {
        return EntityType.DRAGON_FIREBALL;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.projectile.DragonFireball getMinecraftEntity() {
        return (net.minecraft.world.entity.projectile.DragonFireball) super.getMinecraftEntity();
    }
}

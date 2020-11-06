package org.loomdev.loom.entity.projectile;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.SmallFireball;

public class SmallFireballImpl extends FireballImpl implements SmallFireball {

    public SmallFireballImpl(net.minecraft.world.entity.projectile.SmallFireball entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<SmallFireball> getType() {
        return EntityType.SMALL_FIREBALL;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.projectile.SmallFireball getMinecraftEntity() {
        return (net.minecraft.world.entity.projectile.SmallFireball) super.getMinecraftEntity();
    }
}

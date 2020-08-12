package org.loomdev.loom.entity.projectile;

import net.minecraft.entity.projectile.FireballEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.LargeFireball;

public class LargeFireballImpl extends SizedFireballImpl implements LargeFireball {

    public LargeFireballImpl(FireballEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.FIREBALL;
    }

    @Override
    public FireballEntity getMinecraftEntity() {
        return (FireballEntity) super.getMinecraftEntity();
    }

    @Override
    public int getExplosionPower() {
        return getMinecraftEntity().explosionPower;
    }

    @Override
    public void setExplosionPower(int power) {
        getMinecraftEntity().explosionPower = power;
    }

}

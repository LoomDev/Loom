package org.loomdev.loom.entity.projectile;

import com.google.common.base.Preconditions;
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
    public float getExplosionPower() {
        if (getMinecraftEntity().loomExplosionPower == -1F) {
            return getMinecraftEntity().explosionPower;
        }
        return getMinecraftEntity().loomExplosionPower;
    }

    @Override
    public void setExplosionPower(float power) {
        Preconditions.checkArgument(power >= 0, "Explosion power can't be less than zero.");
        getMinecraftEntity().loomExplosionPower = power;
    }

}

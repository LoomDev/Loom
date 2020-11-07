package org.loomdev.loom.entity.projectile;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.Fireball;
import org.loomdev.api.entity.projectile.LargeFireball;

public class LargeFireballImpl extends AbstractFireballImpl implements LargeFireball {

    public LargeFireballImpl(net.minecraft.world.entity.projectile.LargeFireball entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Fireball> getType() {
        return EntityType.FIREBALL;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.projectile.LargeFireball getMinecraftEntity() {
        return (net.minecraft.world.entity.projectile.LargeFireball) super.getMinecraftEntity();
    }

    @Override
    public float getExplosionPower() {
        if (getMinecraftEntity().explosionPower == -1F) {
            return getMinecraftEntity().explosionPower;
        }
        return getMinecraftEntity().explosionPower;
    }

    @Override
    public void setExplosionPower(int power) {
        Preconditions.checkArgument(power >= 0, "Explosion power can't be less than zero.");
        getMinecraftEntity().explosionPower = power;
    }
}

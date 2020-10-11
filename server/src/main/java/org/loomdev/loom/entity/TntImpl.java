package org.loomdev.loom.entity;

import com.google.common.base.Preconditions;
import net.minecraft.entity.TntEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.Tnt;

import java.util.Optional;

public class TntImpl extends EntityImpl implements Tnt {

    public TntImpl(TntEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.TNT;
    }

    @Override
    public @NotNull TntEntity getMinecraftEntity() {
        return (TntEntity) super.getMinecraftEntity();
    }

    @Override
    public int getFuseTicks() {
        return getMinecraftEntity().getFuse();
    }

    @Override
    public void setFuseTicks(int ticks) {
        getMinecraftEntity().setFuse(ticks);
    }

    @Override
    public @Nullable LivingEntity getSource() {
        return Optional.ofNullable(getMinecraftEntity().getCausingEntity())
                .map(e -> (LivingEntityImpl) e.getLoomEntity())
                .orElse(null);
    }

    @Override
    public float getExplosionPower() {
        return getMinecraftEntity().explosionPower;
    }

    @Override
    public void setExplosionPower(float power) {
        Preconditions.checkArgument(power >= 0, "Explosion power can't be less than zero.");
        getMinecraftEntity().explosionPower = power;
    }
}

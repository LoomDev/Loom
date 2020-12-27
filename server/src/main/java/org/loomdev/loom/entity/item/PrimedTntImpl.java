package org.loomdev.loom.entity.item;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.item.PrimedTnt;
import org.loomdev.loom.entity.EntityImpl;
import org.loomdev.loom.entity.LivingEntityImpl;

import java.util.Optional;

public class PrimedTntImpl extends EntityImpl implements PrimedTnt {

    public PrimedTntImpl(net.minecraft.world.entity.item.PrimedTnt entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<PrimedTnt> getType() {
        return EntityType.TNT;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.item.PrimedTnt getMinecraftEntity() {
        return (net.minecraft.world.entity.item.PrimedTnt) super.getMinecraftEntity();
    }

    @Override
    public int getFuse() {
        return getMinecraftEntity().getFuse();
    }

    @Override
    public void setFuse(int ticks) {
        getMinecraftEntity().setFuse(ticks);
    }

    @Override
    @NotNull
    public Optional<LivingEntity> getOwner() {
        return Optional.ofNullable(getMinecraftEntity().getOwner())
                .map(e -> (LivingEntityImpl) e.getLoomEntity());
    }

    @Override
    public float getExplosionPower() {
        return getMinecraftEntity().explosionPower;
    }

    @Override
    public void setExplosionPower(int power) {
        Preconditions.checkArgument(power >= 0, "Explosion power can't be less than zero.");
        getMinecraftEntity().explosionPower = power;
    }
}

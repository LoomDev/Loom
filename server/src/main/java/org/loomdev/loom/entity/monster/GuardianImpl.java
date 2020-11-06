package org.loomdev.loom.entity.monster;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.mob.Guardian;
import org.loomdev.loom.entity.LivingEntityImpl;

public class GuardianImpl extends MonsterImpl implements Guardian {

    public GuardianImpl(net.minecraft.world.entity.monster.Guardian entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<? extends Guardian> getType() {
        return EntityType.GUARDIAN;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.Guardian getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Guardian) super.getMinecraftEntity();
    }

    @Override
    public boolean isSpikesRetracted() {
        return getMinecraftEntity().isMoving();
    }

    @Override
    public void setSpikesRetracted(boolean b) {
        getMinecraftEntity().setMoving(b);
    }

    @Override
    @Nullable
    public LivingEntity getBeamTarget() {
        var target = getMinecraftEntity().getActiveAttackTarget();
        if (target == null) return null;
        return (LivingEntityImpl) target.getLoomEntity();
    }

    @Override
    public void setBeamTarget(@Nullable LivingEntity livingEntity) {
        getMinecraftEntity().setActiveAttackTarget(livingEntity == null ? 0 : livingEntity.getEntityId());
    }

    @Override
    public boolean hasBeamTarget() {
        return getMinecraftEntity().hasActiveAttackTarget();
    }
}

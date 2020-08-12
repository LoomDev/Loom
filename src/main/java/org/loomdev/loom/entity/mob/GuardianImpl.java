package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.GuardianEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.mob.Guardian;
import org.loomdev.loom.entity.LivingEntityImpl;

import java.util.Optional;

public class GuardianImpl extends HostileEntityImpl implements Guardian {

    public GuardianImpl(GuardianEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.GUARDIAN;
    }

    @Override
    public GuardianEntity getMinecraftEntity() {
        return (GuardianEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean areSpikesRetracted() {
        return getMinecraftEntity().areSpikesRetracted();
    }

    @Override
    public void setSpikesRetracted(boolean b) {
        getMinecraftEntity().setSpikesRetracted(b);
    }

    @Override
    public @NotNull Optional<LivingEntity> getBeamTarget() {
        return Optional.ofNullable(getMinecraftEntity().getBeamTarget()).map(entity -> (LivingEntityImpl) entity.getLoomEntity());
    }

    @Override
    public void setBeamTarget(@Nullable LivingEntity livingEntity) {
        getMinecraftEntity().setBeamTarget(livingEntity == null ? 0 : livingEntity.getEntityId());
    }

    @Override
    public boolean hasBeamTarget() {
        return getMinecraftEntity().hasBeamTarget();
    }
}

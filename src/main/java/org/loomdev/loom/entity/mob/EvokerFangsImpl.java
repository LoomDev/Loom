package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.EvokerFangsEntity;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.mob.EvokerFangs;
import org.loomdev.loom.entity.EntityImpl;
import org.loomdev.loom.entity.LivingEntityImpl;

import java.util.Optional;

public class EvokerFangsImpl extends EntityImpl implements EvokerFangs {

    public EvokerFangsImpl(EvokerFangsEntity entity) {
        super(entity);
    }

    @Override
    public @NonNull EntityType getType() {
        return EntityType.EVOKER_FANGS;
    }

    @Override
    public EvokerFangsEntity getMinecraftEntity() {
        return (EvokerFangsEntity) super.getMinecraftEntity();
    }

    @Override
    public @NonNull Optional<LivingEntity> getOwner() {
        return Optional.ofNullable(getMinecraftEntity().getOwner())
                .map(mc -> (LivingEntity) mc.getLoomEntity());
    }

    @Override
    public void setOwner(@Nullable LivingEntity livingEntity) {
        getMinecraftEntity().setOwner(livingEntity == null ? null : ((LivingEntityImpl) livingEntity).getMinecraftEntity());
    }
}

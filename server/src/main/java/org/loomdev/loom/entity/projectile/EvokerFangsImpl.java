package org.loomdev.loom.entity.projectile;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.mob.EvokerFangs;
import org.loomdev.loom.entity.EntityImpl;
import org.loomdev.loom.entity.LivingEntityImpl;

public class EvokerFangsImpl extends EntityImpl implements EvokerFangs {

    public EvokerFangsImpl(net.minecraft.world.entity.projectile.EvokerFangs entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<EvokerFangs> getType() {
        return EntityType.EVOKER_FANGS;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.projectile.EvokerFangs getMinecraftEntity() {
        return (net.minecraft.world.entity.projectile.EvokerFangs) super.getMinecraftEntity();
    }

    @Override
    @Nullable
    public LivingEntity getOwner() {
        var owner = getMinecraftEntity().getOwner();
        if (owner == null) return null;
        return (LivingEntity) owner.getLoomEntity();
    }

    @Override
    public void setOwner(@Nullable LivingEntity livingEntity) {
        getMinecraftEntity().setOwner(livingEntity == null ? null : ((LivingEntityImpl) livingEntity).getMinecraftEntity());
    }
}

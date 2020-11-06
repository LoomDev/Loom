package org.loomdev.loom.entity.projectile.thrown;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.thrown.ThrownExperienceBottle;

public class ThrownExperienceBottleImpl extends AbstractThrowableItemImpl implements ThrownExperienceBottle {

    public ThrownExperienceBottleImpl(net.minecraft.world.entity.projectile.ThrownExperienceBottle entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<ThrownExperienceBottle> getType() {
        return EntityType.EXPERIENCE_BOTTLE;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.projectile.ThrownExperienceBottle getMinecraftEntity() {
        return (net.minecraft.world.entity.projectile.ThrownExperienceBottle) super.getMinecraftEntity();
    }
}

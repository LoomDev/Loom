package org.loomdev.loom.entity.projectile.thrown;

import net.minecraft.entity.projectile.thrown.ExperienceBottleEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.thrown.ExperienceBottle;

public class ExperienceBottleImpl extends ThrownItemImpl implements ExperienceBottle {
    public ExperienceBottleImpl(ExperienceBottleEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.EXPERIENCE_BOTTLE;
    }

    @Override
    public @NotNull ExperienceBottleEntity getMinecraftEntity() {
        return (ExperienceBottleEntity) super.getMinecraftEntity();
    }
}

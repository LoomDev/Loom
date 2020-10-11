package org.loomdev.loom.entity.projectile.thrown;

import net.minecraft.entity.projectile.thrown.PotionEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.thrown.Potion;

public class PotionImpl extends ThrownItemImpl implements Potion {

    public PotionImpl(PotionEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.POTION;
    }

    @Override
    public @NotNull PotionEntity getMinecraftEntity() {
        return (PotionEntity) super.getMinecraftEntity();
    }
}

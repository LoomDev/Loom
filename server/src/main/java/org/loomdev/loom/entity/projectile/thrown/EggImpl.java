package org.loomdev.loom.entity.projectile.thrown;

import net.minecraft.entity.projectile.thrown.EggEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.thrown.Egg;

public class EggImpl extends ThrownItemImpl implements Egg {

    public EggImpl(EggEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.EGG;
    }

    @Override
    public @NotNull EggEntity getMinecraftEntity() {
        return (EggEntity) super.getMinecraftEntity();
    }
}

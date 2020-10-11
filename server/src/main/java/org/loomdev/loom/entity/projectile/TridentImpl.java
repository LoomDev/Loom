package org.loomdev.loom.entity.projectile;

import net.minecraft.entity.projectile.TridentEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.Trident;

public class TridentImpl extends PersistentProjectileImpl implements Trident {

    public TridentImpl(TridentEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.TRIDENT;
    }

    @Override
    public @NotNull TridentEntity getMinecraftEntity() {
        return (TridentEntity) super.getMinecraftEntity();
    }
}

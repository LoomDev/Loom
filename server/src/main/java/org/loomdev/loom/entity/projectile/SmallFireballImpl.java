package org.loomdev.loom.entity.projectile;

import net.minecraft.entity.projectile.SmallFireballEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.SmallFireball;

public class SmallFireballImpl extends SizedFireballImpl implements SmallFireball {
    public SmallFireballImpl(SmallFireballEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SMALL_FIREBALL;
    }

    @Override
    public @NotNull SmallFireballEntity getMinecraftEntity() {
        return (SmallFireballEntity) super.getMinecraftEntity();
    }
}

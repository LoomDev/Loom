package org.loomdev.loom.entity.misc;

import net.minecraft.entity.LightningEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.misc.Lightning;
import org.loomdev.loom.entity.EntityImpl;

public class LightningImpl extends EntityImpl implements Lightning {

    public LightningImpl(LightningEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.LIGHTNING_BOLT;
    }

    @Override
    public @NotNull LightningEntity getMinecraftEntity() {
        return (LightningEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean isCosmetic() {
        return getMinecraftEntity().cosmetic;
    }
}

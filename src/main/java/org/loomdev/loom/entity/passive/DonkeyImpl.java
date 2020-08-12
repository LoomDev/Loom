package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.DonkeyEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Donkey;

public class DonkeyImpl extends AbstractDonkeyImpl implements Donkey {

    public DonkeyImpl(DonkeyEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.DONKEY;
    }

    @Override
    public DonkeyEntity getMinecraftEntity() {
        return (DonkeyEntity) super.getMinecraftEntity();
    }
}

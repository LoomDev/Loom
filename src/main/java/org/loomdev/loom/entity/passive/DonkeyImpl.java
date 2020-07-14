package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.DonkeyEntity;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Donkey;

public class DonkeyImpl extends AbstractDonkeyImpl implements Donkey {

    public DonkeyImpl(DonkeyEntity entity) {
        super(entity);
    }

    @Override
    public @NonNull EntityType getType() {
        return EntityType.DONKEY;
    }

    @Override
    public DonkeyEntity getMinecraftEntity() {
        return (DonkeyEntity) super.getMinecraftEntity();
    }
}

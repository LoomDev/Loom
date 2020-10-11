package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.PufferfishEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Pufferfish;

public class PufferfishImpl extends FishImpl implements Pufferfish {

    public PufferfishImpl(PufferfishEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PUFFERFISH;
    }

    @Override
    public @NotNull PufferfishEntity getMinecraftEntity() {
        return (PufferfishEntity) super.getMinecraftEntity();
    }

    @Override
    public @NotNull PuffState getPuffState() {
        return PuffState.values()[getMinecraftEntity().getPuffState()];
    }

    @Override
    public void setPuffState(@NotNull PuffState puffState) {
        getMinecraftEntity().setPuffState(puffState.ordinal());
    }
}

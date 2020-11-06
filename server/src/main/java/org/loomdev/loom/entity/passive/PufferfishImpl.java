package org.loomdev.loom.entity.passive;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Pufferfish;
import org.loomdev.loom.entity.animal.FishImpl;

public class PufferfishImpl extends FishImpl implements Pufferfish {

    public PufferfishImpl(net.minecraft.world.entity.animal.Pufferfish entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Pufferfish> getType() {
        return EntityType.PUFFERFISH;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.Pufferfish getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.Pufferfish) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public PuffState getPuffState() {
        return PuffState.values()[getMinecraftEntity().getPuffState()];
    }

    @Override
    public void setPuffState(@NotNull PuffState puffState) {
        getMinecraftEntity().setPuffState(puffState.ordinal());
    }
}

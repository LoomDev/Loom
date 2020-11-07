package org.loomdev.loom.entity.animal.fish;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.animal.fish.Pufferfish;

public class PufferfishImpl extends AbstractFishImpl implements Pufferfish {

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

    @Override
    public int getDeflateTicks() {
        return getMinecraftEntity().deflateTimer;
    }

    @Override
    public void setDeflateTicks(int ticks) {
        getMinecraftEntity().deflateTimer = ticks;
    }
}

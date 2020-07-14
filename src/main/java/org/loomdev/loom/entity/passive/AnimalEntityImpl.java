package org.loomdev.loom.entity.passive;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.block.Material;
import org.loomdev.api.entity.passive.AnimalEntity;

import java.util.UUID;

public class AnimalEntityImpl extends PassiveEntityImpl implements AnimalEntity {
    public AnimalEntityImpl(net.minecraft.entity.passive.AnimalEntity entity) {
        super(entity);
    }

    @Override
    public net.minecraft.entity.passive.AnimalEntity getMinecraftEntity() {
        return (net.minecraft.entity.passive.AnimalEntity) super.getMinecraftEntity();
    }

    @Override
    public int getLoveTicks() {
        return getMinecraftEntity().getLoveTicks();
    }

    @Override
    public void setLoveTicks(int ticks) {
        getMinecraftEntity().setLoveTicks(ticks);
    }

    @Override
    public void resetLoveTicks() {
        getMinecraftEntity().resetLoveTicks();
    }

    @Override
    public boolean isInLove() {
        return getMinecraftEntity().isInLove();
    }

    @Override
    public UUID getBreedCause() {
        return getMinecraftEntity().lovingPlayer;
    }

    @Override
    public void setBreedCause(@NonNull UUID uuid) {
        getMinecraftEntity().lovingPlayer = uuid;
    }

    @Override
    public boolean isBreedingItem(@NonNull Material material) {
        return getMinecraftEntity().isBreedingItem(null); // TODO transform
    }

    @Override
    public Material getBreedingItem() {
        throw new UnsupportedOperationException("This operation has not been implemented yet"); // TODO
    }

    @Override
    public void setBreedingItem(@NonNull Material material) {
        throw new UnsupportedOperationException("This operation has not been implemented yet"); // TODO
    }
}

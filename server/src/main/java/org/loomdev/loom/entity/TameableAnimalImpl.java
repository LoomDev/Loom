package org.loomdev.loom.entity;

import net.minecraft.world.entity.TamableAnimal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.animal.TameableEntity;
import org.loomdev.loom.entity.animal.AnimalImpl;

import java.util.Optional;
import java.util.UUID;

public abstract class TameableAnimalImpl extends AnimalImpl implements TameableEntity {

    public TameableAnimalImpl(TamableAnimal entity) {
        super(entity);
    }

    @Override
    @NotNull
    public TamableAnimal getMinecraftEntity() {
        return (TamableAnimal) super.getMinecraftEntity();
    }

    @Override
    @Nullable
    public UUID getOwnerId() {
        return getMinecraftEntity().getOwnerUUID();
    }

    @Override
    public void setOwnerId(UUID uuid) {
        getMinecraftEntity().setOwnerUUID(uuid);
    }

    @Override
    public boolean isTamed() {
        return getMinecraftEntity().isTame();
    }

    @Override
    public void setTamed(boolean flag) {
        getMinecraftEntity().setTame(flag);
    }
}

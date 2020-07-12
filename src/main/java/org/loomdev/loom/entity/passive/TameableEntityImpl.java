package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.AnimalEntity;
import org.loomdev.api.entity.passive.TameableEntity;

import java.util.Optional;
import java.util.UUID;

public class TameableEntityImpl extends AnimalEntityImpl implements TameableEntity {

    public TameableEntityImpl(net.minecraft.entity.passive.TameableEntity entity) {
        super(entity);
    }

    @Override
    public net.minecraft.entity.passive.TameableEntity getMinecraftEntity() {
        return (net.minecraft.entity.passive.TameableEntity) super.getMinecraftEntity();
    }

    @Override
    public Optional<UUID> getOwnerId() {
        return Optional.ofNullable(getMinecraftEntity().getOwnerUuid());
    }

    @Override
    public void setOwnerId(UUID uuid) {
        getMinecraftEntity().setOwnerUuid(uuid);
    }

    @Override
    public boolean isTamed() {
        return getMinecraftEntity().isTamed();
    }

    @Override
    public void setTamed(boolean flag) {
        getMinecraftEntity().setTamed(flag);
    }
}

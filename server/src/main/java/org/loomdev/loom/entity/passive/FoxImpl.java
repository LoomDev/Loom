package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.FoxEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Fox;

import java.util.List;
import java.util.UUID;

public class FoxImpl extends AnimalEntityImpl implements Fox {
    public FoxImpl(FoxEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.FOX;
    }

    @Override
    public @NotNull FoxEntity getMinecraftEntity() {
        return (FoxEntity) super.getMinecraftEntity();
    }

    @Override
    public @NotNull Variant getVariant() {
        return Variant.values()[getMinecraftEntity().getFoxType().getId()];
    }

    @Override
    public void setVariant(@NotNull Variant type) {
        getMinecraftEntity().setType(FoxEntity.Type.fromId(type.ordinal()));
    }

    @Override
    public boolean isCrouching() {
        return getMinecraftEntity().isInSneakingPose();
    }

    @Override
    public void setCrouching(boolean b) {
        getMinecraftEntity().setCrouching(true);
    }

    @Override
    public void setSleeping(boolean b) {
        getMinecraftEntity().setSleeping(b);
    }

    @Override
    public boolean isChasing() {
        return getMinecraftEntity().isChasing();
    }

    @Override
    public void setChasing(boolean b) {
        getMinecraftEntity().setChasing(b);
    }

    @Override
    public boolean isRollingHead() {
        return getMinecraftEntity().isRollingHead();
    }

    @Override
    public void setRollingHead(boolean b) {
        getMinecraftEntity().setRollingHead(b);
    }

    @Override
    public List<UUID> getTrusted() {
        return getMinecraftEntity().getTrustedUuids();
    }
}

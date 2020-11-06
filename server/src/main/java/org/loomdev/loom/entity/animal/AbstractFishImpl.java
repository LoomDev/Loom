package org.loomdev.loom.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Fox;

import java.util.List;
import java.util.UUID;

public class AbstractFishImpl extends AnimalImpl implements Fox {

    public AbstractFishImpl(net.minecraft.world.entity.animal.Fox entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType<Fox> getType() {
        return EntityType.FOX;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.Fox getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.Fox) super.getMinecraftEntity();
    }

    @Override
    public @NotNull Variant getVariant() {
        return Variant.values()[getMinecraftEntity().getFoxType().getId()];
    }

    @Override
    public void setVariant(@NotNull Variant type) {
        getMinecraftEntity().setFoxType(net.minecraft.world.entity.animal.Fox.Type.byId(type.ordinal()));
    }

    @Override
    public boolean isCrouching() {
        return getMinecraftEntity().isCrouching();
    }

    @Override
    public void setCrouching(boolean b) {
        getMinecraftEntity().setIsCrouching(true);
    }

    @Override
    public void setSleeping(boolean b) {
        getMinecraftEntity().setSleeping(b);
    }

    @Override
    public boolean isPouncing() {
        return getMinecraftEntity().isPouncing();
    }

    @Override
    public void setPouncing(boolean pouncing) {
        getMinecraftEntity().setIsPouncing(pouncing);
    }

    @Override
    public boolean isRollingHead() {
        return getMinecraftEntity().isInterested();
    }

    @Override
    public void setRollingHead(boolean b) {
        getMinecraftEntity().setIsInterested(b);
    }

    @Override
    @NotNull
    public List<UUID> getTrusted() {
        return getMinecraftEntity().getTrustedUUIDs();
    }

    @Override
    public void trust(@NotNull UUID uuid) {
        getMinecraftEntity().addTrustedUUID(uuid);
    }
}

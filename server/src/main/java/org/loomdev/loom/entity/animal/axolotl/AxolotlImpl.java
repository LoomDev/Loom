package org.loomdev.loom.entity.animal.axolotl;

import net.minecraft.world.entity.animal.axolotl.Axolotl;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.loom.entity.animal.AnimalImpl;

public class AxolotlImpl extends AnimalImpl implements org.loomdev.api.entity.animal.axolotl.Axolotl {

    public AxolotlImpl(Axolotl entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<org.loomdev.api.entity.animal.axolotl.Axolotl> getType() {
        return EntityType.AXOLOTL;
    }

    @Override
    @NotNull
    public Axolotl getMinecraftEntity() {
        return (Axolotl) super.getMinecraftEntity();
    }

    @Override
    public boolean isPlayingDead() {
        return getMinecraftEntity().isPlayingDead();
    }

    @Override
    public void setPlayingDead(boolean playingDead) {
        getMinecraftEntity().setPlayingDead(playingDead);
    }

    @Override
    @NotNull
    public Variant getVariant() {
        return Variant.values()[getMinecraftEntity().getVariant().ordinal()];
    }

    @Override
    public void setVariant(@NotNull Variant variant) {
        getMinecraftEntity().setVariant(Axolotl.Variant.values()[variant.ordinal()]);
    }

    @Override
    public void setFromBucket(boolean flag) {
        getMinecraftEntity().setFromBucket(flag);
    }
}

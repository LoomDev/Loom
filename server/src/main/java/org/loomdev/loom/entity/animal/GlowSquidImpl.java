package org.loomdev.loom.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.animal.GlowSquid;

public class GlowSquidImpl extends SquidImpl implements GlowSquid {

    public GlowSquidImpl(net.minecraft.world.entity.GlowSquid entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<GlowSquid> getType() {
        return EntityType.GLOW_SQUID;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.GlowSquid getMinecraftEntity() {
        return (net.minecraft.world.entity.GlowSquid) super.getMinecraftEntity();
    }

    @Override
    public int getDarkTicks() {
        return getMinecraftEntity().getDarkTicksRemaining();
    }

    @Override
    public void setDarkTicks(int ticks) {
        getMinecraftEntity().setDarkTicks(ticks);
    }
}

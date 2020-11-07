package org.loomdev.loom.entity.projectile;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.LlamaSpit;

public class LlamaSpitImpl extends AbstractProjectileImpl implements LlamaSpit {

    public LlamaSpitImpl(net.minecraft.world.entity.projectile.LlamaSpit entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<LlamaSpit> getType() {
        return EntityType.LLAMA_SPIT;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.projectile.LlamaSpit getMinecraftEntity() {
        return (net.minecraft.world.entity.projectile.LlamaSpit) super.getMinecraftEntity();
    }
}

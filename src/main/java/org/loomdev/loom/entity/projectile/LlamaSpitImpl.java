package org.loomdev.loom.entity.projectile;

import net.minecraft.entity.projectile.LlamaSpitEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.LlamaSpit;

public class LlamaSpitImpl extends ProjectileImpl implements LlamaSpit {

    public LlamaSpitImpl(LlamaSpitEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.LLAMA_SPIT;
    }

    @Override
    public LlamaSpitEntity getMinecraftEntity() {
        return (LlamaSpitEntity) super.getMinecraftEntity();
    }
}

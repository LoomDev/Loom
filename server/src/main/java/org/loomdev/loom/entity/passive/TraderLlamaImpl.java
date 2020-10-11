package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.TraderLlamaEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.TraderLlama;

public class TraderLlamaImpl extends LlamaImpl implements TraderLlama {

    public TraderLlamaImpl(TraderLlamaEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.TRADER_LLAMA;
    }

    @Override
    public @NotNull TraderLlamaEntity getMinecraftEntity() {
        return (TraderLlamaEntity) super.getMinecraftEntity();
    }
}

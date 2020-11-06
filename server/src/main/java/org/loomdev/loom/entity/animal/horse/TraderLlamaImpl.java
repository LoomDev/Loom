package org.loomdev.loom.entity.animal.horse;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.TraderLlama;

public class TraderLlamaImpl extends LlamaImpl implements TraderLlama {

    public TraderLlamaImpl(net.minecraft.world.entity.animal.horse.TraderLlama entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<TraderLlama> getType() {
        return EntityType.TRADER_LLAMA;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.horse.TraderLlama getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.horse.TraderLlama) super.getMinecraftEntity();
    }
}

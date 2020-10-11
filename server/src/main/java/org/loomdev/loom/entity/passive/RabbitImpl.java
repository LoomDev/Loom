package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.RabbitEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Rabbit;

public class RabbitImpl extends AnimalEntityImpl implements Rabbit {

    public RabbitImpl(RabbitEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.RABBIT;
    }

    @Override
    public @NotNull RabbitEntity getMinecraftEntity() {
        return (RabbitEntity) super.getMinecraftEntity();
    }

    @Override
    public @NotNull Variant getVariant() {
        return Variant.getByMcId(getMinecraftEntity().getRabbitType());
    }

    @Override
    public void setVariant(@NotNull Variant variant) {
        getMinecraftEntity().setRabbitType(variant.getMcId());
    }
}

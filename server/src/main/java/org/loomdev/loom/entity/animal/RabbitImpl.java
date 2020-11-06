package org.loomdev.loom.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Rabbit;

public class RabbitImpl extends AnimalImpl implements Rabbit {

    public RabbitImpl(net.minecraft.world.entity.animal.Rabbit entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Rabbit> getType() {
        return EntityType.RABBIT;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.Rabbit getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.Rabbit) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public Variant getVariant() {
        return Variant.getByMcId(getMinecraftEntity().getRabbitType());
    }

    @Override
    public void setVariant(@NotNull Variant variant) {
        getMinecraftEntity().setRabbitType(variant.getMcId());
    }
}

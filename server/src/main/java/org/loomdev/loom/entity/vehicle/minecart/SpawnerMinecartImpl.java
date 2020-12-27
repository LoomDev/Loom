package org.loomdev.loom.entity.vehicle.minecart;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.MinecartSpawner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.vehicle.minecart.SpawnerMinecart;

import java.util.Optional;

public class SpawnerMinecartImpl extends AbstractMinecartImpl implements SpawnerMinecart {

    public SpawnerMinecartImpl(MinecartSpawner entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<SpawnerMinecart> getType() {
        return EntityType.SPAWNER_MINECART;
    }

    @Override
    @NotNull
    public MinecartSpawner getMinecraftEntity() {
        return (MinecartSpawner) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public Optional<EntityType<?>> getEntityType() {
        return Optional.ofNullable(getMinecraftEntity().spawner.getEntityId(null, null))
                .map(id -> EntityType.getById(id.toString()));
    }

    @Override
    public void setEntityType(@Nullable EntityType<?> entityType) {
        if (entityType == null) return;
        getMinecraftEntity().spawner.setEntityId(Registry.ENTITY_TYPE.get(new ResourceLocation(entityType.getKey().toString())));
    }
}

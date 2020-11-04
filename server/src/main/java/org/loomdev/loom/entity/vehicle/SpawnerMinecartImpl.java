package org.loomdev.loom.entity.vehicle;

import net.minecraft.entity.vehicle.SpawnerMinecartEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.vehicle.SpawnerMinecart;

public class SpawnerMinecartImpl extends MinecartImpl implements SpawnerMinecart {

    public SpawnerMinecartImpl(SpawnerMinecartEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType<SpawnerMinecart> getType() {
        return EntityType.SPAWNER_MINECART;
    }

    @Override
    public @NotNull SpawnerMinecartEntity getMinecraftEntity() {
        return (SpawnerMinecartEntity) super.getMinecraftEntity();
    }

    @Override
    public @Nullable EntityType<?> getEntityType() {
        Identifier id = getMinecraftEntity().logic.getEntityId(null, getMinecraftEntity().getBlockPos());
        return id == null ? null : EntityType.getById(id.toString());
    }

    @Override
    public void setEntityType(@Nullable EntityType<?> entityType) {
        if (entityType == null) return;
        getMinecraftEntity().logic.setEntityId(
            Registry.ENTITY_TYPE.get(new Identifier(entityType.getKey().toString()))
        );
    }
}

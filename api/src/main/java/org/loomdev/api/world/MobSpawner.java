package org.loomdev.api.world;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;

import java.util.Optional;

public interface MobSpawner {

    @NotNull
    Optional<EntityType<?>> getEntityType();

    void setEntityType(@Nullable EntityType<?> type);

    // TODO delay
    // TODO min spawn delay
    // TODO max spawn delay
    // TODO spawn count
    // TODO max nearby entities
    // TODO required player range
    // TODO spawn range
    // TODO spawn data
    // TODO spawn potentials

}

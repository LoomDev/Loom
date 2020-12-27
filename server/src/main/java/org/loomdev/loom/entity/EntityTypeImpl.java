package org.loomdev.loom.entity;

import com.google.common.collect.ImmutableSet;
import net.kyori.adventure.text.Component;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.Loom;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.SpawnGroup;
import org.loomdev.loom.util.registry.GenericWrapped;
import org.loomdev.loom.util.transformer.TextTransformer;

import java.util.Optional;

public class EntityTypeImpl extends GenericWrapped implements EntityType<Entity> {

    private final net.minecraft.world.entity.EntityType<?> mcEntity;

    public EntityTypeImpl(String key) {
        super(key);
        this.mcEntity = Registry.ENTITY_TYPE.get(new ResourceLocation(key));
    }

    @NotNull
    public net.minecraft.world.entity.EntityType<?> getMinecraftEntity() {
        return mcEntity;
    }

    @Override
    @NotNull
    public SpawnGroup getSpawnGroup() {
        return Loom.getRegistry().getWrapped(SpawnGroup.class, mcEntity.getCategory().getName());
    }

    @Override
    @NotNull
    public ImmutableSet<BlockType> getCanSpawnInside() {
        throw new UnsupportedOperationException("This feature is not yet available."); // TODO implement (needs a fix for mc EntityType class)
    }

    @Override
    public boolean isSummonable() {
        return mcEntity.canSummon();
    }

    @Override
    public boolean isFireImmune() {
        return mcEntity.fireImmune();
    }

    @Override
    public boolean canSpawnFarFromPlayer() {
        return mcEntity.canSpawnFarFromPlayer();
    }

    @Override
    public int maxTrackDistance() {
        return mcEntity.clientTrackingRange();
    }

    @Override
    @NotNull
    public Optional<Component> getName() {
        return Optional.ofNullable(getMinecraftEntity().getDescription())
                .map(TextTransformer::toLoom);
    }
}

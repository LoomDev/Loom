package org.loomdev.loom.entity;

import com.google.common.collect.ImmutableSet;
import net.kyori.adventure.text.Component;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.Loom;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.SpawnGroup;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.loom.util.transformer.TextTransformer;

public class EntityTypeImpl implements EntityType<Entity> {

    private final net.minecraft.entity.EntityType<?> mcEntity;
    private final NamespacedKey namespacedKey;

    public EntityTypeImpl(String key) {
        this.mcEntity = Registry.ENTITY_TYPE.get(new Identifier(key));
        this.namespacedKey = NamespacedKey.of(key);
    }

    @Override
    public @NotNull SpawnGroup getSpawnGroup() {
        return Loom.getRegistry().getWrapped(SpawnGroup.class, this.mcEntity.getSpawnGroup().getName());
    }

    @Override
    public @NotNull ImmutableSet<BlockType> getCanSpawnInside() {
        throw new UnsupportedOperationException("This feature is not yet available."); // TODO implement (needs a fix for mc EntityType class)
    }

    @Override
    public boolean isSummonable() {
        return this.mcEntity.isSummonable();
    }

    @Override
    public boolean isFireImmune() {
        return this.mcEntity.isFireImmune();
    }

    @Override
    public boolean canSpawnFarFromPlayer() {
        return this.mcEntity.isSpawnableFarFromPlayer();
    }

    @Override
    public int maxTrackDistance() {
        return this.mcEntity.getMaxTrackDistance();
    }

    @Override
    public @Nullable Component getName() {
        return TextTransformer.toLoom(this.mcEntity.getName());
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return this.namespacedKey;
    }
}

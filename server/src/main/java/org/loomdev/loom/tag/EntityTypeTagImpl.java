package org.loomdev.loom.tag;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.tag.EntityTypeTag;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.loom.entity.EntityTypeImpl;
import org.loomdev.loom.util.registry.GenericWrapped;

import java.util.Random;
import java.util.stream.Stream;

public class EntityTypeTagImpl extends GenericWrapped implements EntityTypeTag {

    private final net.minecraft.tags.Tag<net.minecraft.world.entity.EntityType<?>> tag;

    public EntityTypeTagImpl(NamespacedKey key) {
        super(key);
        this.tag = EntityTypeTags.HELPER.getAllTags().getTag(new ResourceLocation(key.getNamespace(), key.getKey()));

        if (this.tag == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean contains(@NotNull EntityType<?> type) {
        return tag.contains(((EntityTypeImpl) type).getMinecraftEntity());
    }

    @Override
    @NotNull
    public Stream<EntityType<?>> getValues() {
        return tag.getValues().stream()
                .map(block -> EntityType.getById(getEntityKey(block)));
    }

    @Override
    @NotNull
    public EntityType<?> getRandom(@NotNull Random random) {
        return EntityType.getById(getEntityKey(tag.getRandomElement(random)));
    }

    @NotNull
    private String getEntityKey(net.minecraft.world.entity.EntityType<?> entity) {
        return Registry.ENTITY_TYPE.getKey(entity).toString();
    }
}

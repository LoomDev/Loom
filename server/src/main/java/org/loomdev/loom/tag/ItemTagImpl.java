package org.loomdev.loom.tag;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.item.ItemType;
import org.loomdev.api.tag.ItemTag;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.loom.item.ItemTypeImpl;
import org.loomdev.loom.util.registry.GenericWrapped;

import java.util.Random;
import java.util.stream.Stream;

public class ItemTagImpl extends GenericWrapped implements ItemTag {

    private final net.minecraft.tags.Tag<Item> tag;

    public ItemTagImpl(NamespacedKey key) {
        super(key);
        this.tag = ItemTags.HELPER.getAllTags().getTag(new ResourceLocation(key.getNamespace(), key.getKey()));

        if (this.tag == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean contains(@NotNull ItemType type) {
        return tag.contains(((ItemTypeImpl) type).getMinecraftItem());
    }

    @Override
    @NotNull
    public Stream<ItemType> getValues() {
        return tag.getValues().stream()
                .map(item -> ItemType.getById(getItemKey(item)));
    }

    @Override
    @NotNull
    public ItemType getRandom(@NotNull Random random) {
        return ItemType.getById(getItemKey(tag.getRandomElement(random)));
    }

    @NotNull
    private String getItemKey(Item item) {
        return Registry.ITEM.getKey(item).toString();
    }
}

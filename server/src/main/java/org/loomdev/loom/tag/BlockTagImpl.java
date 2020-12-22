package org.loomdev.loom.tag;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.tag.BlockTag;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.loom.block.BlockTypeImpl;
import org.loomdev.loom.util.registry.GenericWrapped;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class BlockTagImpl extends GenericWrapped implements BlockTag {

    private final net.minecraft.tags.Tag<Block> tag;

    public BlockTagImpl(NamespacedKey key) {
        super(key);
        this.tag = BlockTags.HELPER.getAllTags().getTag(new ResourceLocation(key.getNamespace(), key.getKey()));

        if (this.tag == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean contains(@NotNull BlockType type) {
        return tag.contains(((BlockTypeImpl) type).getMinecraftBlock());
    }

    @Override
    @NotNull
    @Unmodifiable
    public List<BlockType> getValues() {
        return tag.getValues().stream()
                .map(block -> BlockType.getById(getBlockKey(block)))
                .collect(Collectors.toList());
    }

    @Override
    @NotNull
    public BlockType getRandom(@NotNull Random random) {
        return BlockType.getById(getBlockKey(tag.getRandomElement(random)));
    }

    @NotNull
    private String getBlockKey(Block block) {
        return Registry.BLOCK.getKey(block).toString();
    }
}

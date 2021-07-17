package org.loomdev.loom.tag;

import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.tag.BlockTag;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.loom.block.BlockTypeImpl;
import org.loomdev.loom.transformer.Transformer;
import org.loomdev.loom.util.registry.GenericWrapped;

import java.util.Random;
import java.util.stream.Stream;

public class BlockTagImpl extends GenericWrapped implements BlockTag {

    private final net.minecraft.tags.Tag<Block> tag;

    public BlockTagImpl(NamespacedKey key) {
        super(key);
        this.tag = BlockTags.HELPER.getAllTags().getTag(Transformer.NAMESPACED_KEY.toMinecraft(key));

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
    public Stream<BlockType> getValues() {
        return tag.getValues().stream()
                .map(block -> BlockType.getById(getBlockKey(block)));
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

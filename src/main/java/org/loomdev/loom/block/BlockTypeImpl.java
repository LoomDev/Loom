package org.loomdev.loom.block;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.item.ItemType;
import org.loomdev.api.util.NamespacedKey;

public class BlockTypeImpl implements BlockType {

    private final Block mcBlock;
    private final NamespacedKey namespacedKey;

    public BlockTypeImpl(String key) {
        this.mcBlock = Registry.BLOCK.get(new Identifier(key));
        this.namespacedKey = NamespacedKey.of(key);
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return namespacedKey;
    }

    @Override
    public float getSlipperiness() {
        return this.mcBlock.getSlipperiness();
    }

    @Override
    public float getVelocityMultiplier() {
        return this.mcBlock.getVelocityMultiplier();
    }

    @Override
    public float getJumpVelocityMultiplier() {
        return this.mcBlock.getJumpVelocityMultiplier();
    }

    @Override
    public @NotNull ItemType asItem() {
        Identifier identifier = Registry.ITEM.getId(this.mcBlock.asItem());
        return Loom.getRegistry().getWrapped(ItemType.class, identifier.toString());
    }
}

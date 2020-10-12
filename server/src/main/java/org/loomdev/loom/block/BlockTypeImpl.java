package org.loomdev.loom.block;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.item.ItemType;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.loom.util.registry.GenericWrapped;

public class BlockTypeImpl extends GenericWrapped implements BlockType {

    private final Block mcBlock;

    public BlockTypeImpl(String key) {
        super(key);
        this.mcBlock = Registry.BLOCK.get(new Identifier(key));
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

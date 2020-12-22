package org.loomdev.loom.block;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.item.ItemType;

import org.loomdev.loom.util.registry.GenericWrapped;

public class BlockTypeImpl extends GenericWrapped implements BlockType {

    private final Block mcBlock;

    public BlockTypeImpl(String key) {
        super(key);
        this.mcBlock = Registry.BLOCK.get(new ResourceLocation(key));
    }

    @NotNull
    public Block getMinecraftBlock() {
        return mcBlock;
    }

    @Override
    public float getSlipperiness() {
        return mcBlock.getFriction();
    }

    @Override
    public float getVelocityMultiplier() {
        return mcBlock.getSpeedFactor();
    }

    @Override
    public float getJumpVelocityMultiplier() {
        return mcBlock.getJumpFactor();
    }

    @Override
    public boolean isSolid() {
        return mcBlock.hasCollision && mcBlock.properties.canOcclude;
    }

    @Override
    public boolean hasCollision() {
        return mcBlock.hasCollision;
    }

    @Override
    @NotNull
    public ItemType asItem() {
        var resourceLocation = Registry.ITEM.getKey(mcBlock.asItem());
        return Loom.getRegistry().getWrapped(ItemType.class, resourceLocation.toString());
    }
}

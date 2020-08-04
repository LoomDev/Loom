package org.loomdev.loom.world;

import com.google.common.base.Preconditions;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.WorldChunk;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.world.Chunk;
import org.loomdev.api.world.World;
import org.loomdev.loom.block.BlockImpl;

public class ChunkImpl implements Chunk {

    private WorldChunk chunk;

    public ChunkImpl(WorldChunk mcChunk) {
        this.chunk = mcChunk;
    }

    public static ChunkImpl from(WorldChunk chunk) {
        return new ChunkImpl(chunk);
    }

    public WorldChunk getMinecraftChunk() {
        return this.chunk;
    }

    @Override
    public int getX() {
        return this.chunk.getPos().x;
    }

    @Override
    public int getZ() {
        return this.chunk.getPos().z;
    }

    @Override
    public long getKey() {
        return ChunkPos.toLong(this.getX(), this.getZ());
    }

    @Override
    public @NotNull World getWorld() {
        return null; // TODO implement after world uuids
    }

    @Override
    public @NotNull Block getBlock(int x, int y, int z) {
        Preconditions.checkArgument(0 <= x && x <= 15, "x-coordinate out of range (expected 0-15, got %s).", x);
        Preconditions.checkArgument(0 <= y && y <= 255, "y-coordinate out of range (expected 0-255, got %s).", x);
        Preconditions.checkArgument(0 <= z && z <= 15, "z-coordinate out of range (expected 0-15, got %s).", x);
        return BlockImpl.at(getMinecraftChunk().getWorld(), new BlockPos((this.getX() << 4) | this.getX(), y, (this.getZ() << 4) | this.getZ()));
    }

    @Override
    public boolean isLoaded() {
        return false;
    }

    @Override
    public long getInhabitedTime() {
        return 0;
    }

    @Override
    public void setInhabitedTime(long l) {

    }
}

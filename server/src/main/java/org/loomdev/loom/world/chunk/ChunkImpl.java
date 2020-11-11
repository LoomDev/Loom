package org.loomdev.loom.world.chunk;

import com.google.common.base.Preconditions;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.world.level.chunk.LevelChunk;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.math.vector.Vector3i;
import org.loomdev.api.world.Chunk;
import org.loomdev.api.world.World;
import org.loomdev.loom.block.BlockPointerImpl;

import java.lang.ref.WeakReference;

public class ChunkImpl implements Chunk {

    private final WeakReference<LevelChunk> chunk;
    private final Vector3i position;

    public ChunkImpl(LevelChunk mcChunk) {
        this.chunk = new WeakReference<>(Preconditions.checkNotNull(mcChunk));
        this.position = new Vector3i(mcChunk.getPos().x, 0, mcChunk.getPos().z);
    }

    @NotNull
    public static ChunkImpl of(@NotNull LevelChunk chunk) {
        return new ChunkImpl(chunk);
    }

    @Nullable
    public LevelChunk getMinecraftChunk() {
        return chunk.get();
    }

    @Override
    @NotNull
    public Vector3i getPosition() {
        return position;
    }

    @Override
    @NotNull
    public World getWorld() {
        return null; // TODO implement after world uuids
    }

    @Override
    @NotNull
    public BlockPointer getBlock(int x, int y, int z) {
        Preconditions.checkArgument(0 <= x && x <= 15, "x-coordinate out of range (expected 0-15, got %s).", x);
        Preconditions.checkArgument(0 <= y && y <= 255, "y-coordinate out of range (expected 0-255, got %s).", x);
        Preconditions.checkArgument(0 <= z && z <= 15, "z-coordinate out of range (expected 0-15, got %s).", x);
        return getMinecraftChunk().getLevel().getLoomWorld().getBlock((x << 4) | x, y, (z << 4) | z);
    }

    @Override
    public boolean isLoaded() {
        return false;
        //return getMinecraftChunk().;
    }

    @Override
    @NotNull
    public ChunkState getChunkState() {
        return ChunkState.values()[getMinecraftChunk().getFullStatus().ordinal()];
    }

    @Override
    public void setChunkState(@NotNull ChunkState state) {
        getMinecraftChunk().setFullStatus(() -> ChunkHolder.FullChunkStatus.values()[state.ordinal()]);
    }

    @Override
    public long getInhabitedTime() {
        return getMinecraftChunk().getInhabitedTime();
    }

    @Override
    public void setInhabitedTime(long ticks) {
        getMinecraftChunk().setInhabitedTime(ticks);
    }
}

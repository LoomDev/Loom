package org.loomdev.api.world;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.math.vector.Vector3d;
import org.loomdev.api.math.vector.Vector3i;

import java.lang.ref.WeakReference;
import java.util.Objects;

public class Location {

    private final WeakReference<World> world;
    private final double x;
    private final double y;
    private final double z;
    private final float pitch;
    private final float yaw;

    public Location(@NotNull World world, double x, double y, double z) {
        this(world, x, y, z, 0, 0);
    }

    public Location(@NotNull World world, double x, double y, double z, float pitch, float yaw) {
        this.world = new WeakReference<>(world);
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    @NotNull
    public World getWorld() {
        return world.get();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return pitch;
    }

    public Vector3d getPosition() {
        return new Vector3d(x, y, z);
    }

    @NotNull
    public Vector3i getBlockPosition() {
        return new Vector3i((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));
    }

    @NotNull
    public Vector3i getChunkPosition() {
        return new Vector3i((int) Math.floor(x) >> 4, (int) Math.floor(y), (int) Math.floor(z) >> 4);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location position = (Location) o;
        return Double.compare(position.x, x) == 0 &&
                Double.compare(position.y, y) == 0 &&
                Double.compare(position.z, z) == 0 &&
                Float.compare(position.pitch, pitch) == 0 &&
                Float.compare(position.yaw, yaw) == 0 &&
                Objects.equals(world, position.world);
    }

    @Override
    public int hashCode() {
        return Objects.hash(world, x, y, z, pitch, yaw);
    }

    // TODO equals(), hash, clone(), and toString()
    // TODO getChunk()
}

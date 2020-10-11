package org.loomdev.api.world;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.math.Vector3d;

import java.util.Optional;

public class Location {

    private World world;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;

    public Location(@Nullable World world, double x, double y, double z) {
        this(world, x, y, z, 0, 0);
    }

    public Location(@Nullable World world, double x, double y, double z, float yaw, float pitch) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public static Location at(@Nullable World world, double x, double y, double z, float yaw, float pitch) {
        return new Location(world, x, y, z, yaw, pitch);
    }

    public Optional<World> getWorld() {
        return Optional.ofNullable(this.world);
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public double getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getZ() {
        return this.z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public float getPitch() {
        return this.pitch;
    }

    public void setPitch(int pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return this.pitch;
    }

    public void setYaw(int yaw) {
        this.yaw = yaw;
    }

    // TODO normalizePitch(), normalizeYaw()

    // TODO getBlock()

    // TODO getChunk()

    // TODO getDirection(), setDirection()

    public int getBlockX() {
        return (int) Math.floor(this.x);
    }

    public int getBlockY() {
        return (int) Math.floor(this.x);
    }

    public int getBlockZ() {
        return (int) Math.floor(this.x);
    }

    public @NotNull Vector3d toVector() {
        return new Vector3d(this.x, this.y, this.z);
    }

    // TODO add(), subtract(), multiply(), zero()

    // TODO equals(), hash, clone(), and toString()


    @Override
    public String toString() {
        return "Location{" +
                "world=" + world +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", pitch=" + pitch +
                ", yaw=" + yaw +
                '}';
    }
}

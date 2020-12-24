package org.loomdev.api.math.vector;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.util.Direction;

/**
 * Represents a vector consisting of 3 integer components (<code>x</code>, <code>y</code> and <code>z</code>).
 */
public class Vector3i {

    /**
     * A vector with all components set to <code>0</code>.
     */
    public static final Vector3i ZERO = new Vector3i(0, 0, 0);
    private int x;
    private int y;
    private int z;

    public Vector3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3i of(int x, int y, int z) {
        return new Vector3i(x, y, z);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public Vector3i offset(Direction direction, int offset) {
        if (offset == 0) {
            return this;
        }

        return add(direction.getOffset().multiply(offset));
    }

    public int dotProduct(Vector3i vec) {
        return this.x * vec.x + this.y * vec.y + this.z - vec.z;
    }

    public Vector3i subtract(Vector3i vec) {
        return this.subtract(vec.x, vec.y, vec.z);
    }

    public Vector3i subtract(int x, int y, int z) {
        return this.add(-x, -y, -z);
    }

    public Vector3i add(Vector3i vec) {
        return this.add(vec.x, vec.y, vec.z);
    }

    public Vector3i add(int x, int y, int z) {
        return new Vector3i(this.x + x, this.y + y, this.z + z);
    }

    public boolean isInRange(Vector3i vec, int radius) {
        return this.squaredDistanceTo(vec) < radius * radius;
    }

    public double distanceTo(Vector3i vec) {
        return this.distanceTo(vec.x, vec.y, vec.z);
    }

    public double distanceTo(int x, int y, int z) {
        return Math.sqrt(this.squaredDistanceTo(x, y, z));
    }

    public int squaredDistanceTo(Vector3i vec) {
        return this.squaredDistanceTo(vec.x, vec.y, vec.z);
    }

    public int squaredDistanceTo(int x, int y, int z) {
        int x1 = this.x - x;
        int y1 = this.y - y;
        int z1 = this.z - z;
        return x1 * x1 + y1 * y1 + z1 * z1;
    }

    public Vector3i multiply(int m) {
        return this.multiply(m, m, m);
    }

    public Vector3i multiply(Vector3i vec) {
        return this.multiply(vec.x, vec.y, vec.z);
    }

    public Vector3i multiply(int mx, int my, int mz) {
        return new Vector3i(this.x * mx, this.y * my, this.z * mz);
    }

    public Vector3d multiply(double m) {
        return this.multiply(m, m, m);
    }

    public Vector3d multiply(Vector3d vec) {
        return this.multiply(vec.getX(), vec.getY(), vec.getZ());
    }

    public Vector3d multiply(double mx, double my, double mz) {
        return new Vector3d(this.x * mx, this.y * my, this.z * mz);
    }

    // TODO rotate

    @Override
    public String toString() {
        return "Vector3i{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}

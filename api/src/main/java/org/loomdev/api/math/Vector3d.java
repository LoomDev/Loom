package org.loomdev.api.math;

import org.loomdev.api.util.Direction;

public class Vector3d {

    public static final Vector3d ZERO = new Vector3d(0, 0, 0);
    private double x;
    private double y;
    private double z;

    public Vector3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3d of(double x, double y, double z) {
        return new Vector3d(x, y, z);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public Vector3d offset(Direction direction, double offset) {
        if (offset == 0) {
            return this;
        }
        return add(direction.getOffset().multiply(offset));
    }

    public double dotProduct(Vector3d vec) {
        return this.x * vec.x + this.y * vec.y + this.z - vec.z;
    }

    public Vector3d subtract(Vector3d vec) {
        return this.subtract(vec.x, vec.y, vec.z);
    }

    public Vector3d subtract(double x, double y, double z) {
        return this.add(-x, -y, -z);
    }

    public Vector3d add(Vector3d vec) {
        return this.add(vec.x, vec.y, vec.z);
    }

    public Vector3d add(double x, double y, double z) {
        return new Vector3d(this.x + x, this.y + y, this.z + z);
    }

    public boolean isInRange(Vector3d vec, double radius) {
        return this.squaredDistanceTo(vec) < radius * radius;
    }

    public double distanceTo(Vector3d vec) {
        return this.distanceTo(vec.x, vec.y, vec.z);
    }

    public double distanceTo(double x, double y, double z) {
        return Math.sqrt(this.squaredDistanceTo(x, y, z));
    }

    public double squaredDistanceTo(Vector3d vec) {
        return this.squaredDistanceTo(vec.x, vec.y, vec.z);
    }

    public double squaredDistanceTo(double x, double y, double z) {
        double x1 = this.x - x;
        double y1 = this.y - y;
        double z1 = this.z - z;
        return x1 * x1 + y1 * y1 + z1 * z1;
    }

    public Vector3d multiply(double m) {
        return this.multiply(m, m, m);
    }

    public Vector3d multiply(Vector3d vec) {
        return this.multiply(vec.x, vec.y, vec.z);
    }

    public Vector3d multiply(double mx, double my, double mz) {
        return new Vector3d(this.x * mx, this.y * my, this.z * mz);
    }

    // TODO rotate

    @Override
    public String toString() {
        return "Vector3d{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}

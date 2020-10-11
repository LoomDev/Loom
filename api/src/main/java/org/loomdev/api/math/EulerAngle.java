package org.loomdev.api.math;

public class EulerAngle {

    protected final float pitch;
    protected final float yaw;
    protected final float roll;

    private EulerAngle(float pitch, float yaw, float roll) {
        this.pitch = !Float.isInfinite(pitch) && !Float.isNaN(pitch) ? pitch % 360.0F : 0.0F;
        this.yaw = !Float.isInfinite(yaw) && !Float.isNaN(yaw) ? yaw % 360.0F : 0.0F;
        this.roll = !Float.isInfinite(roll) && !Float.isNaN(roll) ? roll % 360.0F : 0.0F;

    }

    public static EulerAngle of(float pitch, float yaw, float roll) {
        return new EulerAngle(pitch, yaw, roll);
    }

    public EulerAngle subtract(float pitch, float yaw, float roll) {
        return add(-pitch, -yaw, -roll);
    }

    public EulerAngle add(float pitch, float yaw, float roll) {
        return new EulerAngle(
                pitch,
                yaw,
                roll
        );
    }

    public float getPitch() {
        return this.pitch;
    }

    public float getYaw() {
        return this.yaw;
    }

    public float getRoll() {
        return this.roll;
    }

    @Override
    public String toString() {
        return "EulerAngle{" +
                "pitch=" + pitch +
                ", yaw=" + yaw +
                ", roll=" + roll +
                '}';
    }
}

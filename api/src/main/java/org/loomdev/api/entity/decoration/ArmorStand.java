package org.loomdev.api.entity.decoration;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.math.EulerAngle;

public interface ArmorStand extends LivingEntity {

    EulerAngle DEFAULT_HEAD_ROTATION = new EulerAngle(0.0F, 0.0F, 0.0F);
    EulerAngle DEFAULT_BODY_ROTATION = new EulerAngle(0.0F, 0.0F, 0.0F);
    EulerAngle DEFAULT_LEFT_ARM_ROTATION = new EulerAngle(-10.0F, 0.0F, -10.0F);
    EulerAngle DEFAULT_RIGHT_ARM_ROTATION = new EulerAngle(-15.0F, 0.0F, 10.0F);
    EulerAngle DEFAULT_LEFT_LEG_ROTATION = new EulerAngle(-1.0F, 0.0F, -1.0F);
    EulerAngle DEFAULT_RIGHT_LEG_ROTATION = new EulerAngle(1.0F, 0.0F, 1.0F);

    @NotNull EulerAngle getBodyPose();

    void setBodyPose(@NotNull EulerAngle eulerAngle);

    @NotNull EulerAngle getLeftArmPose();

    void setLeftArmPose(@NotNull EulerAngle eulerAngle);

    @NotNull EulerAngle getRightArmPose();

    void setRightArmPose(@NotNull EulerAngle eulerAngle);

    @NotNull EulerAngle getLeftLegPose();

    void setLeftLegPose(@NotNull EulerAngle eulerAngle);

    @NotNull EulerAngle getRightLegPose();

    void setRightLegPose(@NotNull EulerAngle eulerAngle);

    @NotNull EulerAngle getHeadPose();

    void setHeadPose(@NotNull EulerAngle eulerAngle);

    boolean isBasePlateHidden();

    void setBasePlateHidden(boolean flag);

    boolean isMarker();

    void setMarker(boolean flag);

    boolean isSmall();

    void setSmall(boolean flag);

    boolean areArmsVisible();

    void setArmsVisible(boolean flag);

}


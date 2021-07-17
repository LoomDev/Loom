package org.loomdev.loom.entity.decoration;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.decoration.ArmorStand;
import org.loomdev.api.math.EulerAngle;
import org.loomdev.loom.entity.LivingEntityImpl;

import static org.loomdev.loom.transformer.Transformer.EULER_ANGLE;

public class ArmorStandImpl extends LivingEntityImpl implements ArmorStand {

    public ArmorStandImpl(net.minecraft.world.entity.decoration.ArmorStand entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<ArmorStand> getType() {
        return EntityType.ARMOR_STAND;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.decoration.ArmorStand getMinecraftEntity() {
        return (net.minecraft.world.entity.decoration.ArmorStand) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public EulerAngle getBodyPose() {
        return EULER_ANGLE.toLoom(getMinecraftEntity().getBodyPose());
    }

    @Override
    public void setBodyPose(@NotNull EulerAngle eulerAngle) {
        getMinecraftEntity().setBodyPose(EULER_ANGLE.toMinecraft(eulerAngle));
    }

    @Override
    @NotNull
    public EulerAngle getLeftArmPose() {
        return EULER_ANGLE.toLoom(getMinecraftEntity().leftArmPose);
    }

    @Override
    public void setLeftArmPose(@NotNull EulerAngle eulerAngle) {
        getMinecraftEntity().setLeftArmPose(EULER_ANGLE.toMinecraft(eulerAngle));
    }

    @Override
    @NotNull
    public EulerAngle getRightArmPose() {
        return EULER_ANGLE.toLoom(getMinecraftEntity().rightArmPose);
    }

    @Override
    public void setRightArmPose(@NotNull EulerAngle eulerAngle) {
        getMinecraftEntity().setRightArmPose(EULER_ANGLE.toMinecraft(eulerAngle));
    }

    @Override
    @NotNull
    public EulerAngle getLeftLegPose() {
        return EULER_ANGLE.toLoom(getMinecraftEntity().leftLegPose);
    }

    @Override
    public void setLeftLegPose(@NotNull EulerAngle eulerAngle) {
        getMinecraftEntity().setLeftLegPose(EULER_ANGLE.toMinecraft(eulerAngle));
    }

    @Override
    @NotNull
    public EulerAngle getRightLegPose() {
        return EULER_ANGLE.toLoom(getMinecraftEntity().rightLegPose);
    }

    @Override
    public void setRightLegPose(@NotNull EulerAngle eulerAngle) {
        getMinecraftEntity().setRightLegPose(EULER_ANGLE.toMinecraft(eulerAngle));
    }

    @Override
    @NotNull
    public EulerAngle getHeadPose() {
        return EULER_ANGLE.toLoom(getMinecraftEntity().getHeadPose());
    }

    @Override
    public void setHeadPose(@NotNull EulerAngle eulerAngle) {
        getMinecraftEntity().setHeadPose(EULER_ANGLE.toMinecraft(eulerAngle));
    }

    @Override
    public boolean isBasePlateHidden() {
        return getMinecraftEntity().isNoBasePlate();
    }

    @Override
    public void setBasePlateHidden(boolean flag) {
        getMinecraftEntity().setNoBasePlate(flag);
    }

    @Override
    public boolean isMarker() {
        return getMinecraftEntity().isMarker();
    }

    @Override
    public void setMarker(boolean flag) {
        getMinecraftEntity().setMarker(flag);
    }

    @Override
    public boolean isSmall() {
        return getMinecraftEntity().isSmall();
    }

    @Override
    public void setSmall(boolean flag) {
        getMinecraftEntity().setSmall(flag);
    }

    @Override
    public boolean areArmsVisible() {
        return getMinecraftEntity().isShowArms();
    }

    @Override
    public void setArmsVisible(boolean flag) {
        getMinecraftEntity().setShowArms(flag);
    }
}

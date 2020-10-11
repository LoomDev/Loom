package org.loomdev.loom.entity.decoration;

import net.minecraft.entity.decoration.ArmorStandEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.decoration.ArmorStand;
import org.loomdev.api.math.EulerAngle;
import org.loomdev.loom.entity.LivingEntityImpl;
import org.loomdev.loom.util.transformer.EulerAngleTransformer;

public class ArmorStandImpl extends LivingEntityImpl implements ArmorStand {

    public ArmorStandImpl(ArmorStandEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ARMOR_STAND;
    }

    @Override
    public @NotNull ArmorStandEntity getMinecraftEntity() {
        return (ArmorStandEntity) super.getMinecraftEntity();
    }

    @Override
    public @NotNull EulerAngle getBodyPose() {
        return EulerAngleTransformer.toLoom(getMinecraftEntity().getBodyRotation());
    }

    @Override
    public void setBodyPose(@NotNull EulerAngle eulerAngle) {
        getMinecraftEntity().setBodyRotation(EulerAngleTransformer.toMinecraft(eulerAngle));
    }

    @Override
    public @NotNull EulerAngle getLeftArmPose() {
        return EulerAngleTransformer.toLoom(getMinecraftEntity().leftArmRotation);
    }

    @Override
    public void setLeftArmPose(@NotNull EulerAngle eulerAngle) {
        getMinecraftEntity().setLeftArmRotation(EulerAngleTransformer.toMinecraft(eulerAngle));
    }

    @Override
    public @NotNull EulerAngle getRightArmPose() {
        return EulerAngleTransformer.toLoom(getMinecraftEntity().rightArmRotation);
    }

    @Override
    public void setRightArmPose(@NotNull EulerAngle eulerAngle) {
        getMinecraftEntity().setRightArmRotation(EulerAngleTransformer.toMinecraft(eulerAngle));
    }

    @Override
    public @NotNull EulerAngle getLeftLegPose() {
        return EulerAngleTransformer.toLoom(getMinecraftEntity().leftLegRotation);
    }

    @Override
    public void setLeftLegPose(@NotNull EulerAngle eulerAngle) {
        getMinecraftEntity().setLeftLegRotation(EulerAngleTransformer.toMinecraft(eulerAngle));
    }

    @Override
    public @NotNull EulerAngle getRightLegPose() {
        return EulerAngleTransformer.toLoom(getMinecraftEntity().rightLegRotation);
    }

    @Override
    public void setRightLegPose(@NotNull EulerAngle eulerAngle) {
        getMinecraftEntity().setRightLegRotation(EulerAngleTransformer.toMinecraft(eulerAngle));
    }

    @Override
    public @NotNull EulerAngle getHeadPose() {
        return EulerAngleTransformer.toLoom(getMinecraftEntity().getHeadRotation());
    }

    @Override
    public void setHeadPose(@NotNull EulerAngle eulerAngle) {
        getMinecraftEntity().setHeadRotation(EulerAngleTransformer.toMinecraft(eulerAngle));
    }

    @Override
    public boolean isBasePlateHidden() {
        return getMinecraftEntity().shouldHideBasePlate();
    }

    @Override
    public void setBasePlateHidden(boolean flag) {
        getMinecraftEntity().setHideBasePlate(flag);
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
        return getMinecraftEntity().shouldShowArms();
    }

    @Override
    public void setArmsVisible(boolean flag) {
        getMinecraftEntity().setShowArms(flag);
    }
}

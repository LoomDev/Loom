package org.loomdev.loom.entity.decoration;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.decoration.ArmorStand;
import org.loomdev.api.math.EulerAngle;
import org.loomdev.loom.entity.LivingEntityImpl;
import org.loomdev.loom.util.transformer.EulerAngleTransformer;

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
    public @NotNull EulerAngle getBodyPose() {
        return EulerAngleTransformer.toLoom(getMinecraftEntity().getBodyPose());
    }

    @Override
    public void setBodyPose(@NotNull EulerAngle eulerAngle) {
        getMinecraftEntity().setBodyPose(EulerAngleTransformer.toMinecraft(eulerAngle));
    }

    @Override
    public @NotNull EulerAngle getLeftArmPose() {
        return EulerAngleTransformer.toLoom(getMinecraftEntity().leftArmPose);
    }

    @Override
    public void setLeftArmPose(@NotNull EulerAngle eulerAngle) {
        getMinecraftEntity().setLeftArmPose(EulerAngleTransformer.toMinecraft(eulerAngle));
    }

    @Override
    public @NotNull EulerAngle getRightArmPose() {
        return EulerAngleTransformer.toLoom(getMinecraftEntity().rightArmPose);
    }

    @Override
    public void setRightArmPose(@NotNull EulerAngle eulerAngle) {
        getMinecraftEntity().setRightArmPose(EulerAngleTransformer.toMinecraft(eulerAngle));
    }

    @Override
    public @NotNull EulerAngle getLeftLegPose() {
        return EulerAngleTransformer.toLoom(getMinecraftEntity().leftLegPose);
    }

    @Override
    public void setLeftLegPose(@NotNull EulerAngle eulerAngle) {
        getMinecraftEntity().setLeftLegPose(EulerAngleTransformer.toMinecraft(eulerAngle));
    }

    @Override
    public @NotNull EulerAngle getRightLegPose() {
        return EulerAngleTransformer.toLoom(getMinecraftEntity().rightLegPose);
    }

    @Override
    public void setRightLegPose(@NotNull EulerAngle eulerAngle) {
        getMinecraftEntity().setRightLegPose(EulerAngleTransformer.toMinecraft(eulerAngle));
    }

    @Override
    public @NotNull EulerAngle getHeadPose() {
        return EulerAngleTransformer.toLoom(getMinecraftEntity().getHeadPose());
    }

    @Override
    public void setHeadPose(@NotNull EulerAngle eulerAngle) {
        getMinecraftEntity().setHeadPose(EulerAngleTransformer.toMinecraft(eulerAngle));
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

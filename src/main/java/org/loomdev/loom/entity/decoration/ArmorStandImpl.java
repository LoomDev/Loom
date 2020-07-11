package org.loomdev.loom.entity.decoration;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.decoration.ArmorStand;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.math.EulerAngle;
import org.loomdev.api.util.Hand;
import org.loomdev.loom.entity.LivingEntityImpl;
import org.loomdev.loom.util.transformer.EulerAngleTransformer;

import java.util.Optional;

public class ArmorStandImpl extends LivingEntityImpl implements ArmorStand {

    public ArmorStandImpl(ArmorStandEntity entity) {
        super(entity);
    }

    @Override
    public @NonNull EntityType getType() {
        return EntityType.ARMOR_STAND;
    }

    @Override
    public ArmorStandEntity getMinecraftEntity() {
        return (ArmorStandEntity) super.getMinecraftEntity();
    }

    @Override
    public @NonNull Optional<ItemStack> getItemInHand(@NonNull Hand hand) {
        return Optional.empty(); // TODO
    }

    @Override
    public void setItemInHand(@NonNull Hand hand, @NonNull ItemStack itemStack) {
        // TODO
    }

    @Override
    public @NonNull Optional<ItemStack> getBoots() {
        return Optional.empty(); // TODO
    }

    @Override
    public void setBoots(@NonNull ItemStack itemStack) {
        // TODO
    }

    @Override
    public @NonNull Optional<ItemStack> getLeggings() {
        return Optional.empty(); // TODO
    }

    @Override
    public void setLeggings(@NonNull ItemStack itemStack) {
        // TODO
    }

    @Override
    public @NonNull Optional<ItemStack> getChestplate() {
        return Optional.empty(); // TODO
    }

    @Override
    public void setChestplate(@NonNull ItemStack itemStack) {
        // TODO
    }

    @Override
    public @NonNull Optional<ItemStack> getHelmet() {
        return Optional.empty(); // TODO
    }

    @Override
    public void setHelmet(@NonNull ItemStack itemStack) {
        // TODO
    }

    @Override
    public @NonNull EulerAngle getBodyPose() {
        return EulerAngleTransformer.toLoom(getMinecraftEntity().getBodyRotation());
    }

    @Override
    public void setBodyPose(@NonNull EulerAngle eulerAngle) {
        getMinecraftEntity().setBodyRotation(EulerAngleTransformer.toMinecraft(eulerAngle));
    }

    @Override
    public @NonNull EulerAngle getLeftArmPose() {
        return EulerAngleTransformer.toLoom(getMinecraftEntity().leftArmRotation);
    }

    @Override
    public void setLeftArmPose(@NonNull EulerAngle eulerAngle) {
        getMinecraftEntity().setLeftArmRotation(EulerAngleTransformer.toMinecraft(eulerAngle));
    }

    @Override
    public @NonNull EulerAngle getRightArmPose() {
        return EulerAngleTransformer.toLoom(getMinecraftEntity().rightArmRotation);
    }

    @Override
    public void setRightArmPose(@NonNull EulerAngle eulerAngle) {
        getMinecraftEntity().setRightArmRotation(EulerAngleTransformer.toMinecraft(eulerAngle));
    }

    @Override
    public @NonNull EulerAngle getLeftLegPose() {
        return EulerAngleTransformer.toLoom(getMinecraftEntity().leftLegRotation);
    }

    @Override
    public void setLeftLegPose(@NonNull EulerAngle eulerAngle) {
        getMinecraftEntity().setLeftLegRotation(EulerAngleTransformer.toMinecraft(eulerAngle));
    }

    @Override
    public @NonNull EulerAngle getRightLegPose() {
        return EulerAngleTransformer.toLoom(getMinecraftEntity().rightLegRotation);
    }

    @Override
    public void setRightLegPose(@NonNull EulerAngle eulerAngle) {
        getMinecraftEntity().setRightLegRotation(EulerAngleTransformer.toMinecraft(eulerAngle));
    }

    @Override
    public @NonNull EulerAngle getHeadPose() {
        return EulerAngleTransformer.toLoom(getMinecraftEntity().getHeadRotation());
    }

    @Override
    public void setHeadPose(@NonNull EulerAngle eulerAngle) {
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

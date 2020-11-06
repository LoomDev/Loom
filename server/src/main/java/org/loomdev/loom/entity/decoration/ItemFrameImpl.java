package org.loomdev.loom.entity.decoration;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.decoration.ItemFrame;
import org.loomdev.api.item.ItemStack;
import org.loomdev.loom.item.ItemStackImpl;

public class ItemFrameImpl extends HangingEntityImpl implements ItemFrame {

    public ItemFrameImpl(net.minecraft.world.entity.decoration.ItemFrame entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<ItemFrame> getType() {
        return EntityType.ITEM_FRAME;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.decoration.ItemFrame getMinecraftEntity() {
        return (net.minecraft.world.entity.decoration.ItemFrame) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public ItemStack getHeldItem() {
        return ItemStackImpl.of(getMinecraftEntity().getItem());
    }

    @Override
    public void setHeldItem(@NotNull ItemStack itemStack) {
        getMinecraftEntity().setItem(((ItemStackImpl) itemStack).getMinecraftItemStack(), false);
    }

    @Override
    public boolean isVisible() {
        return !getMinecraftEntity().isInvisible();
    }

    @Override
    public void setVisible(boolean flag) {
        getMinecraftEntity().setInvisible(!flag);
    }

    @Override
    public boolean isFixed() {
        return getMinecraftEntity().fixed;
    }

    @Override
    public void setFixed(boolean flag) {
        getMinecraftEntity().fixed = flag;
    }

    @Override
    public float getItemDropChance() {
        return getMinecraftEntity().dropChance;
    }

    @Override
    public void setItemDropChance(float chance) {
        getMinecraftEntity().dropChance = chance;
    }

    @Override
    public @NotNull Rotation getRotation() {
        return Rotation.getByInt(getMinecraftEntity().getRotation());
    }

    @Override
    public void setRotation(@NotNull Rotation rotation) {
        getMinecraftEntity().setRotation(rotation.ordinal());
    }
}

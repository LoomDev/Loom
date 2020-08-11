package org.loomdev.loom.entity.decoration;

import net.minecraft.entity.decoration.ItemFrameEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.decoration.ItemFrame;
import org.loomdev.api.item.ItemStack;
import org.loomdev.loom.item.ItemStackImpl;

public class ItemFrameImpl extends DecorationEntityImpl implements ItemFrame {

    public ItemFrameImpl(ItemFrameEntity entity) {
        super(entity);
    }

    @Override
    public ItemFrameEntity getMinecraftEntity() {
        return (ItemFrameEntity) super.getMinecraftEntity();
    }

    @Override
    public @NotNull ItemStack getHeldItemStack() {
        return ItemStackImpl.ofMcStack(getMinecraftEntity().getHeldItemStack());
    }

    @Override
    public void setHeldItemStack(@NotNull ItemStack itemStack) {
        getMinecraftEntity().setHeldItemStack(((ItemStackImpl) itemStack).getMinecraftItemStack(), false);
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
        return getMinecraftEntity().itemDropChance;
    }

    @Override
    public void setItemDropChance(float chance) {
        getMinecraftEntity().itemDropChance = chance;
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

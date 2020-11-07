package org.loomdev.loom.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.animal.Wolf;
import org.loomdev.api.util.DyeColor;
import org.loomdev.loom.entity.TameableAnimalImpl;

public class WolfImpl extends TameableAnimalImpl implements Wolf {

    public WolfImpl(net.minecraft.world.entity.animal.Wolf entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Wolf> getType() {
        return EntityType.WOLF;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.Wolf getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.Wolf) super.getMinecraftEntity();
    }

    @Override
    public boolean isBegging() {
        return getMinecraftEntity().isInterested();
    }

    @Override
    public void setBegging(boolean flag) {
        getMinecraftEntity().setIsInterested(flag);
    }

    @Override
    @NotNull
    public DyeColor getCollarColor() {
        return DyeColor.getById(getMinecraftEntity().getCollarColor().getId());
    }

    @Override
    public void setCollarColor(@NotNull DyeColor dyeColor) {
        getMinecraftEntity().setCollarColor(net.minecraft.world.item.DyeColor.byId(dyeColor.getId()));
    }
}

package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.WolfEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Wolf;
import org.loomdev.api.util.DyeColor;

public class WolfImpl extends TameableEntityImpl implements Wolf {

    public WolfImpl(WolfEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType<Wolf> getType() {
        return EntityType.WOLF;
    }

    @Override
    public @NotNull WolfEntity getMinecraftEntity() {
        return (WolfEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean isBegging() {
        return getMinecraftEntity().isBegging();
    }

    @Override
    public void setBegging(boolean flag) {
        getMinecraftEntity().setBegging(flag);
    }

    @Override
    public @NotNull DyeColor getCollarColor() {
        return DyeColor.getById(getMinecraftEntity().getCollarColor().getId());
    }

    @Override
    public void setCollarColor(@NotNull DyeColor dyeColor) {
        getMinecraftEntity().setCollarColor(net.minecraft.util.DyeColor.byId(dyeColor.getId()));
    }
}

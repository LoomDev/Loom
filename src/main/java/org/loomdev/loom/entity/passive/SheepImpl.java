package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.sound.SoundCategory;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Sheep;
import org.loomdev.api.util.DyeColor;

public class SheepImpl extends AnimalEntityImpl implements Sheep {

    public SheepImpl(SheepEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SHEEP;
    }

    @Override
    public @NotNull SheepEntity getMinecraftEntity() {
        return (SheepEntity) super.getMinecraftEntity();
    }

    @Override
    public @NotNull DyeColor getColor() {
        return DyeColor.getById(getMinecraftEntity().getColor().getId());
    }

    @Override
    public void setColor(@NotNull DyeColor dyeColor) {
        getMinecraftEntity().setColor(net.minecraft.util.DyeColor.byId(dyeColor.getId()));
    }

    @Override
    public boolean isShearable() {
        return getMinecraftEntity().isShearable();
    }

    @Override
    public void shear() {
        getMinecraftEntity().sheared(SoundCategory.NEUTRAL);
    }

    @Override
    public void setSheared(boolean flag) {
        getMinecraftEntity().setSheared(flag);
    }
}

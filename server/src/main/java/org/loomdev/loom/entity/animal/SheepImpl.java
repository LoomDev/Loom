package org.loomdev.loom.entity.animal;

import net.minecraft.sounds.SoundSource;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.animal.Sheep;
import org.loomdev.api.util.DyeColor;

public class SheepImpl extends AnimalImpl implements Sheep {

    public SheepImpl(net.minecraft.world.entity.animal.Sheep entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Sheep> getType() {
        return EntityType.SHEEP;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.Sheep getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.Sheep) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public DyeColor getColor() {
        return DyeColor.getById(getMinecraftEntity().getColor().getId());
    }

    @Override
    public void setColor(@NotNull DyeColor dyeColor) {
        getMinecraftEntity().setColor(net.minecraft.world.item.DyeColor.byId(dyeColor.getId()));
    }

    @Override
    public boolean isShearable() {
        return getMinecraftEntity().readyForShearing();
    }

    @Override
    public void shear() {
        getMinecraftEntity().shear(SoundSource.NEUTRAL);
    }

    @Override
    public void setSheared(boolean flag) {
        getMinecraftEntity().setSheared(flag);
    }
}

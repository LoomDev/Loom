package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.CatEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Cat;
import org.loomdev.api.util.DyeColor;

public class CatImpl extends TameableEntityImpl implements Cat {

    public CatImpl(CatEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.CAT;
    }

    @Override
    public CatEntity getMinecraftEntity() {
        return (CatEntity) super.getMinecraftEntity();
    }

    @Override
    public Variant getVariant() {
        return Variant.values()[getMinecraftEntity().getCatType()];
    }

    @Override
    public void setVariant(Variant type) {
        getMinecraftEntity().setCatType(type.ordinal());
    }

    @Override
    public DyeColor getCollarColor() {
        return DyeColor.getById(getMinecraftEntity().getCollarColor().getId());
    }

    @Override
    public void setCollarColor(DyeColor dyeColor) {
        getMinecraftEntity().setCollarColor(net.minecraft.util.DyeColor.byId(dyeColor.getId()));
    }

    @Override
    public void hiss() {
        getMinecraftEntity().hiss();
    }

    @Override
    public boolean isSitting() {
        return getMinecraftEntity().isSitting();
    }

    @Override
    public void setSitting(boolean flag) {
        getMinecraftEntity().setSitting(flag);
    }
}

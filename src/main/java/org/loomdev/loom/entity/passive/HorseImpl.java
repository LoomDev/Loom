package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.HorseColor;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.passive.HorseMarking;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Horse;

public class HorseImpl extends HorseBaseImpl implements Horse {

    public HorseImpl(HorseEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.HORSE;
    }

    @Override
    public HorseEntity getMinecraftEntity() {
        return (HorseEntity) super.getMinecraftEntity();
    }

    @Override
    public @NotNull Color getColor() {
        return Color.getByIndex(getMinecraftEntity().getColor().getIndex());
    }

    @Override
    public void setColor(@NotNull Color color) {
        getMinecraftEntity().setVariant(HorseColor.byIndex(color.getIndex()), getMinecraftEntity().getMarking());
    }

    @Override
    public @NotNull Markings getMarkings() {
        return Markings.getByIndex(getMinecraftEntity().getMarking().getIndex());
    }

    @Override
    public void setMarkings(@NotNull Markings markings) {
        getMinecraftEntity().setVariant(getMinecraftEntity().getColor(), HorseMarking.byIndex(markings.getIndex()));
    }
}

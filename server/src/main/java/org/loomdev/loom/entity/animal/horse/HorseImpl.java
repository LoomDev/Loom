package org.loomdev.loom.entity.animal.horse;

import net.minecraft.world.entity.animal.horse.Variant;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Horse;

public class HorseImpl extends AbstractHorseImpl implements Horse {

    public HorseImpl(net.minecraft.world.entity.animal.horse.Horse entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Horse> getType() {
        return EntityType.HORSE;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.horse.Horse getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.horse.Horse) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public Color getColor() {
        return Color.getByIndex(getMinecraftEntity().getVariant().getId());
    }

    @Override
    public void setColor(@NotNull Color color) {
        var mcEntity = getMinecraftEntity();
        mcEntity.setVariantAndMarkings(Variant.byId(color.getIndex()), mcEntity.getMarkings());
    }

    @Override
    @NotNull
    public Markings getMarkings() {
        return Markings.getByIndex(getMinecraftEntity().getMarkings().getId());
    }

    @Override
    public void setMarkings(@NotNull Markings markings) {
        var mcEntity = getMinecraftEntity();
        mcEntity.setVariantAndMarkings(mcEntity.getVariant(), net.minecraft.world.entity.animal.horse.Markings.byId(markings.getIndex()));
    }
}

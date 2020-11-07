package org.loomdev.loom.entity.animal.horse;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.animal.horse.ChestedHorse;

public abstract class AbstractChestedHorseImpl extends AbstractHorseImpl implements ChestedHorse {

    public AbstractChestedHorseImpl(net.minecraft.world.entity.animal.horse.AbstractChestedHorse entity) {
        super(entity);
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.horse.AbstractChestedHorse getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.horse.AbstractChestedHorse) super.getMinecraftEntity();
    }

    @Override
    public boolean hasChest() {
        return getMinecraftEntity().hasChest();
    }

    @Override
    public void setHasChest(boolean chest) {
        getMinecraftEntity().setChest(chest);
    }
}

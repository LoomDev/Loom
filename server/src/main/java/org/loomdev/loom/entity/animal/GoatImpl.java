package org.loomdev.loom.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.animal.Goat;

public class GoatImpl extends AnimalImpl implements Goat {

    public GoatImpl(net.minecraft.world.entity.animal.goat.Goat entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<? extends Goat> getType() {
        return EntityType.GOAT;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.goat.Goat getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.goat.Goat) super.getMinecraftEntity();
    }

    @Override
    public boolean isScreamingGoat() {
        return getMinecraftEntity().getIsScreaming();
    }

    @Override
    public void setScreamingGoat(boolean flag) {
        getMinecraftEntity().isScreamingGoat = flag;
    }
}

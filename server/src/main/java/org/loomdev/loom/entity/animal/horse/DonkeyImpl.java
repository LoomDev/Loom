package org.loomdev.loom.entity.animal.horse;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.animal.horse.Donkey;

public class DonkeyImpl extends AbstractChestedHorseImpl implements Donkey {

    public DonkeyImpl(net.minecraft.world.entity.animal.horse.Donkey entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Donkey> getType() {
        return EntityType.DONKEY;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.horse.Donkey getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.horse.Donkey) super.getMinecraftEntity();
    }
}

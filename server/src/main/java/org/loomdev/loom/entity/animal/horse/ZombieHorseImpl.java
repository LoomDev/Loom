package org.loomdev.loom.entity.animal.horse;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.ZombieHorse;

public class ZombieHorseImpl extends AbstractHorseImpl {

    public ZombieHorseImpl(net.minecraft.world.entity.animal.horse.ZombieHorse entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<ZombieHorse> getType() {
        return EntityType.ZOMBIE_HORSE;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.horse.ZombieHorse getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.horse.ZombieHorse) super.getMinecraftEntity();
    }
}

package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.ZombieHorseEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.ZombieHorse;
import org.loomdev.loom.entity.passive.HorseBaseImpl;

public class ZombieHorseImpl extends HorseBaseImpl {

    public ZombieHorseImpl(ZombieHorseEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType<ZombieHorse> getType() {
        return EntityType.ZOMBIE_HORSE;
    }

    @Override
    public @NotNull ZombieHorseEntity getMinecraftEntity() {
        return (ZombieHorseEntity) super.getMinecraftEntity();
    }
}

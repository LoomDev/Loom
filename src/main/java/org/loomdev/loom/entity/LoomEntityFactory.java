package org.loomdev.loom.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.loomdev.loom.entity.mob.ZombieImpl;
import org.loomdev.loom.entity.player.PlayerImpl;

public final class LoomEntityFactory {

    private LoomEntityFactory() {
        throw new UnsupportedOperationException("LoomEntityFactory should never be instantiated");
    }

    public static EntityImpl getLoomEntity(Entity entity) {
        if (entity instanceof ZombieEntity) {
            return new ZombieImpl((HostileEntity) entity);
        } else if (entity instanceof PlayerEntity) {
            return new PlayerImpl((PlayerEntity) entity);
        }
        return null; // TODO
    }

}

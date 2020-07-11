package org.loomdev.loom.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.loomdev.api.entity.decoration.ArmorStand;
import org.loomdev.loom.entity.decoration.ArmorStandImpl;
import org.loomdev.loom.entity.mob.ZombieImpl;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.entity.projectile.ArrowImpl;

public final class LoomEntityFactory {

    private LoomEntityFactory() {
        throw new UnsupportedOperationException("LoomEntityFactory should never be instantiated");
    }

    public static EntityImpl getLoomEntity(Entity entity) {
        if (entity instanceof ZombieEntity) {
            return new ZombieImpl((HostileEntity) entity);
        } else if (entity instanceof ServerPlayerEntity) {
            return new PlayerImpl((ServerPlayerEntity) entity);
        } else if (entity instanceof ArmorStandEntity) {
            return new ArmorStandImpl((ArmorStandEntity) entity);
        } else if (entity instanceof ArrowEntity) {
            return new ArrowImpl((ArrowEntity) entity);
        }
        return null; // TODO
    }

}

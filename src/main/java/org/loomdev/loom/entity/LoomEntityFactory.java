package org.loomdev.loom.entity;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.CaveSpiderEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.loomdev.loom.entity.decoration.ArmorStandImpl;
import org.loomdev.loom.entity.mob.BlazeImpl;
import org.loomdev.loom.entity.mob.CaveSpiderImpl;
import org.loomdev.loom.entity.mob.SpiderImpl;
import org.loomdev.loom.entity.mob.ZombieImpl;
import org.loomdev.loom.entity.passive.BatImpl;
import org.loomdev.loom.entity.passive.BeeImpl;
import org.loomdev.loom.entity.passive.CatImpl;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.entity.projectile.ArrowImpl;
import org.loomdev.loom.entity.vehicle.BoatImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public final class LoomEntityFactory {
    private LoomEntityFactory() { throw new UnsupportedOperationException("LoomEntityFactory should never be instantiated"); }

    private static final Map<Class<? extends Entity>, Class<? extends EntityImpl>> entityClassMappings = new HashMap<>();

    public static EntityImpl getLoomEntity(Entity entity) {
        if (!entityClassMappings.containsKey(entity.getClass())) {
            throw new IllegalStateException("No entity class mapping was found for " + entity.getClass());
        }

        try {
            Class<? extends EntityImpl> loomClass = entityClassMappings.get(entity.getClass());
            Constructor<? extends EntityImpl> ctor = loomClass.getConstructor(entity.getClass());
            return ctor.newInstance(entity);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    static {
        register(AreaEffectCloudEntity.class, AreaEffectCloudEntityImpl.class);
        register(ArmorStandEntity.class, ArmorStandImpl.class);
        register(ArrowEntity.class, ArrowImpl.class);
        register(BatEntity.class, BatImpl.class);
        register(BeeEntity.class, BeeImpl.class);
        register(BlazeEntity.class, BlazeImpl.class);
        register(BoatEntity.class, BoatImpl.class);
        register(CatEntity.class, CatImpl.class);
        register(CaveSpiderEntity.class, CaveSpiderImpl.class);

        register(ServerPlayerEntity.class, PlayerImpl.class);
        register(SpiderEntity.class, SpiderImpl.class);
        register(ZombieEntity.class, ZombieImpl.class);
    }

    private static void register(Class<? extends Entity> mcClass, Class<? extends EntityImpl> loomClass) {
        entityClassMappings.put(mcClass, loomClass);
    }

}

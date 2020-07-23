package org.loomdev.loom.entity;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.DragonFireballEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.loomdev.api.entity.mob.ElderGuardian;
import org.loomdev.loom.entity.boss.dragon.EnderDragonImpl;
import org.loomdev.loom.entity.boss.dragon.EnderDragonPartImpl;
import org.loomdev.loom.entity.decoration.ArmorStandImpl;
import org.loomdev.loom.entity.decoration.EndCrystalImpl;
import org.loomdev.loom.entity.misc.LightningImpl;
import org.loomdev.loom.entity.mob.*;
import org.loomdev.loom.entity.passive.*;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.entity.projectile.ArrowImpl;
import org.loomdev.loom.entity.projectile.DragonFireballImpl;
import org.loomdev.loom.entity.projectile.FireworkRocketImpl;
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
            return null; // TODO change back
            // throw new IllegalStateException("No entity class mapping was found for " + entity.getClass());
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
        register(ChickenEntity.class, ChickenImpl.class);
        register(CodEntity.class, CodImpl.class);
        register(CowEntity.class, CowImpl.class);
        register(CreeperEntity.class, CreeperImpl.class);
        register(DolphinEntity.class, DolphinImpl.class);
        register(DonkeyEntity.class, DonkeyImpl.class);
        register(DragonFireballEntity.class, DragonFireballImpl.class);
        register(DrownedEntity.class, DrownedImpl.class);
        register(ElderGuardianEntity.class, ElderGuardianImpl.class);
        register(EndCrystalEntity.class, EndCrystalImpl.class);
        register(EnderDragonEntity.class, EnderDragonImpl.class);
        register(EnderDragonPart.class, EnderDragonPartImpl.class);
        register(EndermanEntity.class, EndermanImpl.class);
        register(EndermiteEntity.class, EndermiteImpl.class);
        register(EvokerEntity.class, EvokerImpl.class);
        register(EvokerFangsEntity.class, EvokerImpl.class);
        register(FireworkRocketEntity.class, FireworkRocketImpl.class);
        register(FoxEntity.class, FoxImpl.class);
        register(GhastEntity.class, GhastImpl.class);
        register(GiantEntity.class, GiantImpl.class);
        register(HoglinEntity.class, HoglinImpl.class);
        register(HorseEntity.class, HorseImpl.class);
        register(HuskEntity.class, HuskImpl.class);
        register(IllusionerEntity.class, IllusionerImpl.class);
        register(IronGolemEntity.class, IronGolemImpl.class);
        register(ItemEntity.class, ItemImpl.class);

        register(GuardianEntity.class, GuardianImpl.class);

        register(LightningEntity.class, LightningImpl.class);

        // FIXME temp so i can start the world;

        register(ServerPlayerEntity.class, PlayerImpl.class);
        register(SpiderEntity.class, SpiderImpl.class);
        register(ZombieEntity.class, ZombieImpl.class);
    }

    private static void register(Class<? extends Entity> mcClass, Class<? extends EntityImpl> loomClass) {
        entityClassMappings.put(mcClass, loomClass);
    }

}

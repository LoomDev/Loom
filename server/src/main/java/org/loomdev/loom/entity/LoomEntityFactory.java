package org.loomdev.loom.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.decoration.LeashKnotEntity;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.entity.projectile.thrown.*;
import net.minecraft.entity.vehicle.*;
import net.minecraft.server.network.ServerPlayerEntity;
import org.loomdev.loom.entity.boss.WitherImpl;
import org.loomdev.loom.entity.boss.dragon.EnderDragonImpl;
import org.loomdev.loom.entity.boss.dragon.EnderDragonPartImpl;
import org.loomdev.loom.entity.decoration.*;
import org.loomdev.loom.entity.misc.LightningImpl;
import org.loomdev.loom.entity.mob.*;
import org.loomdev.loom.entity.passive.*;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.entity.projectile.*;
import org.loomdev.loom.entity.projectile.thrown.*;
import org.loomdev.loom.entity.vehicle.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public final class LoomEntityFactory {

    private LoomEntityFactory() {
        throw new UnsupportedOperationException("LoomEntityFactory should never be instantiated");
    }

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
        register(ChestMinecartEntity.class, ChestMinecartImpl.class);
        register(ChickenEntity.class, ChickenImpl.class);
        register(CodEntity.class, CodImpl.class);
        register(CommandBlockMinecartEntity.class, CommandBlockMinecartImpl.class);
        register(CowEntity.class, CowImpl.class);
        register(CreeperEntity.class, CreeperImpl.class);
        register(DolphinEntity.class, DolphinImpl.class);
        register(DonkeyEntity.class, DonkeyImpl.class);
        register(DragonFireballEntity.class, DragonFireballImpl.class);
        register(DrownedEntity.class, DrownedImpl.class);
        register(EggEntity.class, EggImpl.class);
        register(ElderGuardianEntity.class, ElderGuardianImpl.class);
        register(EndCrystalEntity.class, EndCrystalImpl.class);
        register(EnderDragonEntity.class, EnderDragonImpl.class);
        register(EnderPearlEntity.class, EnderPearlImpl.class);
        register(EnderDragonPart.class, EnderDragonPartImpl.class);
        register(EndermanEntity.class, EndermanImpl.class);
        register(EndermiteEntity.class, EndermiteImpl.class);
        register(EvokerEntity.class, EvokerImpl.class);
        register(EvokerFangsEntity.class, EvokerImpl.class);
        register(ExperienceBottleEntity.class, ExperienceBottleImpl.class);
        register(ExperienceOrbEntity.class, ExperienceOrbImpl.class);
        register(EyeOfEnderEntity.class, EyeOfEnderImpl.class);
        register(FireworkRocketEntity.class, FireworkRocketImpl.class);
        register(FallingBlockEntity.class, FallingBlockImpl.class);
        register(FishingBobberEntity.class, FishingBobberImpl.class);
        register(FoxEntity.class, FoxImpl.class);
        register(GhastEntity.class, GhastImpl.class);
        register(GiantEntity.class, GiantImpl.class);
        register(GuardianEntity.class, GuardianImpl.class);
        register(HoglinEntity.class, HoglinImpl.class);
        register(HorseEntity.class, HorseImpl.class);
        register(HuskEntity.class, HuskImpl.class);
        register(IllusionerEntity.class, IllusionerImpl.class);
        register(IronGolemEntity.class, IronGolemImpl.class);
        register(ItemEntity.class, ItemImpl.class);
        register(ItemFrameEntity.class, ItemFrameImpl.class);
        register(FireballEntity.class, LargeFireballImpl.class);
        register(LeashKnotEntity.class, LeashKnotImpl.class);
        register(LightningEntity.class, LightningImpl.class);
        register(LlamaEntity.class, LlamaImpl.class);
        register(LlamaSpitEntity.class, LlamaSpitImpl.class);
        register(MagmaCubeEntity.class, MagmaCubeImpl.class);
        register(FurnaceMinecartEntity.class, FurnaceMinecartImpl.class);
        register(HopperMinecartEntity.class, HopperMinecartImpl.class);
        register(MinecartEntity.class, RideableMinecartImpl.class);
        register(SpawnerMinecartEntity.class, SpawnerMinecartImpl.class);
        register(TntMinecartEntity.class, TntMinecartImpl.class);
        register(MuleEntity.class, MuleImpl.class);
        register(MooshroomEntity.class, MooshroomImpl.class);
        register(OcelotEntity.class, OcelotImpl.class);
        register(PaintingEntity.class, PaintingImpl.class);
        register(PandaEntity.class, PandaImpl.class);
        register(ParrotEntity.class, ParrotImpl.class);
        register(PhantomEntity.class, PhantomImpl.class);
        register(PigEntity.class, PigImpl.class);
        register(PiglinEntity.class, PiglinImpl.class);
        register(PiglinBruteEntity.class, PiglinBruteImpl.class);
        register(PillagerEntity.class, PillagerImpl.class);
        register(PolarBearEntity.class, PolarBearImpl.class);
        register(TntEntity.class, TntImpl.class);
        register(PufferfishEntity.class, PufferfishImpl.class);
        register(RabbitEntity.class, RabbitImpl.class);
        register(RavagerEntity.class, RavagerImpl.class);
        register(SalmonEntity.class, SalmonImpl.class);
        register(SheepEntity.class, SheepImpl.class);
        register(ShulkerEntity.class, ShulkerImpl.class);
        register(ShulkerBulletEntity.class, ShulkerBulletImpl.class);
        register(SilverfishEntity.class, SilverfishImpl.class);
        register(SkeletonEntity.class, SkeletonImpl.class);
        register(SkeletonHorseEntity.class, SkeletonHorseImpl.class);
        register(SlimeEntity.class, SlimeImpl.class);
        register(SmallFireballEntity.class, SmallFireballImpl.class);
        register(SnowGolemEntity.class, SnowGolemImpl.class);
        register(SnowballEntity.class, SnowballImpl.class);
        register(SpectralArrowEntity.class, SpectralArrowImpl.class);
        register(SpiderEntity.class, SpiderImpl.class);
        register(SquidEntity.class, SquidImpl.class);
        register(StrayEntity.class, StrayImpl.class);
        register(StriderEntity.class, StriderImpl.class);
        register(PotionEntity.class, PotionImpl.class);
        register(TurtleEntity.class, TurtleImpl.class);
        register(TridentEntity.class, TridentImpl.class);
        register(TraderLlamaEntity.class, TraderLlamaImpl.class);
        register(TropicalFishEntity.class, TropicalFishImpl.class);
        register(VexEntity.class, VexImpl.class);
        register(VillagerEntity.class, VillagerImpl.class);
        register(VindicatorEntity.class, VindicatorImpl.class);
        register(WanderingTraderEntity.class, WanderingTraderImpl.class);
        register(WitchEntity.class, WitchImpl.class);
        register(WitherEntity.class, WitherImpl.class);
        register(WitherSkeletonEntity.class, WitherSkeletonImpl.class);
        register(WitherSkullEntity.class, WitherSkullImpl.class);
        register(WolfEntity.class, WolfImpl.class);
        register(ZoglinEntity.class, ZoglinImpl.class);
        register(ZombieEntity.class, ZombieImpl.class);
        register(ZombieHorseEntity.class, ZombieHorseImpl.class);
        register(ZombieVillagerEntity.class, ZombieVillagerImpl.class);
        register(ZombifiedPiglinEntity.class, ZombifiedPiglinImpl.class);
        register(ServerPlayerEntity.class, PlayerImpl.class);
    }

    private static void register(Class<? extends Entity> mcClass, Class<? extends EntityImpl> loomClass) {
        entityClassMappings.put(mcClass, loomClass);
    }

}

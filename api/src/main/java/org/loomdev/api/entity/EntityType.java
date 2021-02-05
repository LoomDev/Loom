package org.loomdev.api.entity;

import com.google.common.collect.ImmutableSet;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.entity.animal.Bee;
import org.loomdev.api.entity.animal.Cat;
import org.loomdev.api.entity.animal.Chicken;
import org.loomdev.api.entity.animal.Cow;
import org.loomdev.api.entity.animal.Dolphin;
import org.loomdev.api.entity.animal.Fox;
import org.loomdev.api.entity.animal.GlowSquid;
import org.loomdev.api.entity.animal.Mooshroom;
import org.loomdev.api.entity.animal.Ocelot;
import org.loomdev.api.entity.animal.Panda;
import org.loomdev.api.entity.animal.Parrot;
import org.loomdev.api.entity.animal.Pig;
import org.loomdev.api.entity.animal.PolarBear;
import org.loomdev.api.entity.animal.Rabbit;
import org.loomdev.api.entity.animal.Sheep;
import org.loomdev.api.entity.animal.Squid;
import org.loomdev.api.entity.animal.Turtle;
import org.loomdev.api.entity.animal.Wolf;
import org.loomdev.api.entity.animal.axolotl.Axolotl;
import org.loomdev.api.entity.animal.fish.Cod;
import org.loomdev.api.entity.animal.fish.Pufferfish;
import org.loomdev.api.entity.animal.fish.Salmon;
import org.loomdev.api.entity.animal.fish.TropicalFish;
import org.loomdev.api.entity.animal.golem.IronGolem;
import org.loomdev.api.entity.animal.golem.SnowGolem;
import org.loomdev.api.entity.animal.horse.Donkey;
import org.loomdev.api.entity.animal.horse.Horse;
import org.loomdev.api.entity.animal.horse.Llama;
import org.loomdev.api.entity.animal.horse.Mule;
import org.loomdev.api.entity.animal.horse.SkeletonHorse;
import org.loomdev.api.entity.animal.horse.TraderLlama;
import org.loomdev.api.entity.animal.horse.ZombieHorse;
import org.loomdev.api.entity.boss.Wither;
import org.loomdev.api.entity.boss.enderdragon.EndCrystal;
import org.loomdev.api.entity.boss.enderdragon.EnderDragon;
import org.loomdev.api.entity.decoration.*;
import org.loomdev.api.entity.effect.AreaEffectCloud;
import org.loomdev.api.entity.item.FallingBlock;
import org.loomdev.api.entity.item.ItemEntity;
import org.loomdev.api.entity.item.PrimedTnt;
import org.loomdev.api.entity.misc.ExperienceOrb;
import org.loomdev.api.entity.misc.LightningBolt;
import org.loomdev.api.entity.monster.Blaze;
import org.loomdev.api.entity.monster.CaveSpider;
import org.loomdev.api.entity.monster.Creeper;
import org.loomdev.api.entity.monster.ElderGuardian;
import org.loomdev.api.entity.monster.Enderman;
import org.loomdev.api.entity.monster.Endermite;
import org.loomdev.api.entity.monster.Strider;
import org.loomdev.api.entity.npc.Villager;
import org.loomdev.api.entity.npc.WanderingTrader;
import org.loomdev.api.entity.projectile.EvokerFangs;
import org.loomdev.api.entity.monster.Ghast;
import org.loomdev.api.entity.monster.Guardian;
import org.loomdev.api.entity.monster.MagmaCube;
import org.loomdev.api.entity.monster.Phantom;
import org.loomdev.api.entity.monster.Shulker;
import org.loomdev.api.entity.monster.Silverfish;
import org.loomdev.api.entity.monster.Slime;
import org.loomdev.api.entity.monster.Spider;
import org.loomdev.api.entity.monster.Witch;
import org.loomdev.api.entity.monster.hoglin.Hoglin;
import org.loomdev.api.entity.monster.hoglin.Zoglin;
import org.loomdev.api.entity.monster.hoglin.ZombifiedPiglin;
import org.loomdev.api.entity.monster.illager.Evoker;
import org.loomdev.api.entity.monster.illager.Illusioner;
import org.loomdev.api.entity.monster.illager.Pillager;
import org.loomdev.api.entity.monster.illager.Ravager;
import org.loomdev.api.entity.monster.illager.Vex;
import org.loomdev.api.entity.monster.illager.Vindicator;
import org.loomdev.api.entity.monster.piglin.Piglin;
import org.loomdev.api.entity.monster.piglin.PiglinBrute;
import org.loomdev.api.entity.monster.skeleton.Skeleton;
import org.loomdev.api.entity.monster.skeleton.Stray;
import org.loomdev.api.entity.monster.skeleton.WitherSkeleton;
import org.loomdev.api.entity.monster.zombie.Drowned;
import org.loomdev.api.entity.monster.zombie.Giant;
import org.loomdev.api.entity.monster.zombie.Husk;
import org.loomdev.api.entity.monster.zombie.Zombie;
import org.loomdev.api.entity.monster.zombie.ZombieVillager;
import org.loomdev.api.entity.passive.*;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.entity.projectile.*;
import org.loomdev.api.entity.projectile.thrown.*;
import org.loomdev.api.entity.vehicle.*;
import org.loomdev.api.entity.vehicle.minecart.ChestMinecart;
import org.loomdev.api.entity.vehicle.minecart.CommandBlockMinecart;
import org.loomdev.api.entity.vehicle.minecart.FurnaceMinecart;
import org.loomdev.api.entity.vehicle.minecart.HopperMinecart;
import org.loomdev.api.entity.vehicle.minecart.RideableMinecart;
import org.loomdev.api.entity.vehicle.minecart.SpawnerMinecart;
import org.loomdev.api.entity.vehicle.minecart.TntMinecart;
import org.loomdev.api.util.registry.Keyed;
import org.loomdev.api.util.registry.Registry;

import java.util.Optional;

public interface EntityType<T extends Entity> extends Keyed {

    // region :: EntityTypes

    EntityType<AreaEffectCloud> AREA_EFFECT_CLOUD = getById("minecraft:area_effect_cloud");
    EntityType<ArmorStand> ARMOR_STAND = getById("minecraft:armor_stand");
    EntityType<Arrow> ARROW = getById("minecraft:arrow");
    EntityType<Axolotl> AXOLOTL = getById("minecraft:axolotl");
    EntityType<Bat> BAT = getById("minecraft:bat");
    EntityType<Bee> BEE = getById("minecraft:bee");
    EntityType<Blaze> BLAZE = getById("minecraft:blaze");
    EntityType<Boat> BOAT = getById("minecraft:boat");
    EntityType<Cat> CAT = getById("minecraft:cat");
    EntityType<CaveSpider> CAVE_SPIDER = getById("minecraft:cave_spider");
    EntityType<ChestMinecart> CHEST_MINECART = getById("minecraft:chest_minecart");
    EntityType<Chicken> CHICKEN = getById("minecraft:chicken");
    EntityType<Cod> COD = getById("minecraft:cod");
    EntityType<CommandBlockMinecart> COMMAND_BLOCK_MINECART = getById("minecraft:command_block_minecart");
    EntityType<Cow> COW = getById("minecraft:cow");
    EntityType<Creeper> CREEPER = getById("minecraft:creeper");
    EntityType<Dolphin> DOLPHIN = getById("minecraft:dolphin");
    EntityType<Donkey> DONKEY = getById("minecraft:donkey");
    EntityType<DragonFireball> DRAGON_FIREBALL = getById("minecraft:dragon_fireball");
    EntityType<Drowned> DROWNED = getById("minecraft:drowned");
    EntityType<ThrownEgg> EGG = getById("minecraft:egg");
    EntityType<ElderGuardian> ELDER_GUARDIAN = getById("minecraft:elder_guardian");
    EntityType<EndCrystal> END_CRYSTAL = getById("minecraft:end_crystal");
    EntityType<EnderDragon> ENDER_DRAGON = getById("minecraft:ender_dragon");
    EntityType<ThrownEnderpearl> ENDER_PEARL = getById("minecraft:ender_pearl");
    EntityType<Enderman> ENDERMAN = getById("minecraft:enderman");
    EntityType<Endermite> ENDERMITE = getById("minecraft:endermite");
    EntityType<Evoker> EVOKER = getById("minecraft:evoker");
    EntityType<EvokerFangs> EVOKER_FANGS = getById("minecraft:evoker_fangs");
    EntityType<ThrownExperienceBottle> EXPERIENCE_BOTTLE = getById("minecraft:experience_bottle");
    EntityType<ExperienceOrb> EXPERIENCE_ORB = getById("minecraft:experience_orb");
    EntityType<ThrownEyeOfEnder> EYE_OF_ENDER = getById("minecraft:eye_of_ender");
    EntityType<FallingBlock> FALLING_BLOCK = getById("minecraft:falling_block");
    EntityType<Fireball> FIREBALL = getById("minecraft:fireball");
    EntityType<FireworkRocket> FIREWORK_ROCKET = getById("minecraft:firework_rocket");
    EntityType<FishingHook> FISHING_BOBBER = getById("minecraft:fishing_bobber");
    EntityType<Fox> FOX = getById("minecraft:fox");
    EntityType<FurnaceMinecart> FURNACE_MINECART = getById("minecraft:furnace_minecart");
    EntityType<Ghast> GHAST = getById("minecraft:ghast");
    EntityType<Giant> GIANT = getById("minecraft:giant");
    EntityType<Guardian> GUARDIAN = getById("minecraft:guardian");
    EntityType<Hoglin> HOGLIN = getById("minecraft:hoglin");
    EntityType<HopperMinecart> HOPPER_MINECART = getById("minecraft:hopper_minecart");
    EntityType<Horse> HORSE = getById("minecraft:horse");
    EntityType<Husk> HUSK = getById("minecraft:husk");
    EntityType<Illusioner> ILLUSIONER = getById("minecraft:illusioner");
    EntityType<IronGolem> IRON_GOLEM = getById("minecraft:iron_golem");
    EntityType<ItemEntity> ITEM = getById("minecraft:item");
    EntityType<ItemFrame> ITEM_FRAME = getById("minecraft:item_frame");
    EntityType<LeashKnot> LEASH_KNOT = getById("minecraft:leash_knot");
    EntityType<LightningBolt> LIGHTNING_BOLT = getById("minecraft:lightning_bolt");
    EntityType<Llama> LLAMA = getById("minecraft:llama");
    EntityType<LlamaSpit> LLAMA_SPIT = getById("minecraft:llama_spit");
    EntityType<MagmaCube> MAGMA_CUBE = getById("minecraft:magma_cube");
    EntityType<RideableMinecart> MINECART = getById("minecraft:minecart");
    EntityType<Mooshroom> MOOSHROOM = getById("minecraft:mooshroom");
    EntityType<Mule> MULE = getById("minecraft:mule");
    EntityType<Ocelot> OCELOT = getById("minecraft:ocelot");
    EntityType<Painting> PAINTING = getById("minecraft:painting");
    EntityType<Panda> PANDA = getById("minecraft:panda");
    EntityType<Parrot> PARROT = getById("minecraft:parrot");
    EntityType<Phantom> PHANTOM = getById("minecraft:phantom");
    EntityType<Pig> PIG = getById("minecraft:pig");
    EntityType<Piglin> PIGLIN = getById("minecraft:piglin");
    EntityType<PiglinBrute> PIGLIN_BRUTE = getById("minecraft:piglin_brute");
    EntityType<Pillager> PILLAGER = getById("minecraft:pillager");
    EntityType<Player> PLAYER = getById("minecraft:player");
    EntityType<PolarBear> POLAR_BEAR = getById("minecraft:polar_bear");
    EntityType<ThrownPotion> POTION = getById("minecraft:potion");
    EntityType<Pufferfish> PUFFERFISH = getById("minecraft:pufferfish");
    EntityType<Rabbit> RABBIT = getById("minecraft:rabbit");
    EntityType<Ravager> RAVAGER = getById("minecraft:ravager");
    EntityType<Salmon> SALMON = getById("minecraft:salmon");
    EntityType<Sheep> SHEEP = getById("minecraft:sheep");
    EntityType<Shulker> SHULKER = getById("minecraft:shulker");
    EntityType<ShulkerBullet> SHULKER_BULLET = getById("minecraft:shulker_bullet");
    EntityType<Silverfish> SILVERFISH = getById("minecraft:silverfish");
    EntityType<Skeleton> SKELETON = getById("minecraft:skeleton");
    EntityType<SkeletonHorse> SKELETON_HORSE = getById("minecraft:skeleton_horse");
    EntityType<Slime> SLIME = getById("minecraft:slime");
    EntityType<SmallFireball> SMALL_FIREBALL = getById("minecraft:small_fireball");
    EntityType<SnowGolem> SNOW_GOLEM = getById("minecraft:snow_golem");
    EntityType<ThrownSnowball> SNOWBALL = getById("minecraft:snowball");
    EntityType<SpawnerMinecart> SPAWNER_MINECART = getById("minecraft:spawner_minecart");
    EntityType<SpectralArrow> SPECTRAL_ARROW = getById("minecraft:spectral_arrow");
    EntityType<Spider> SPIDER = getById("minecraft:spider");
    EntityType<Squid> SQUID = getById("minecraft:squid");
    EntityType<Stray> STRAY = getById("minecraft:stray");
    EntityType<Strider> STRIDER = getById("minecraft:strider");
    EntityType<PrimedTnt> TNT = getById("minecraft:tnt");
    EntityType<TntMinecart> TNT_MINECART = getById("minecraft:tnt_minecart");
    EntityType<TraderLlama> TRADER_LLAMA = getById("minecraft:trader_llama");
    EntityType<ThrownTrident> TRIDENT = getById("minecraft:trident");
    EntityType<TropicalFish> TROPICAL_FISH = getById("minecraft:tropical_fish");
    EntityType<Turtle> TURTLE = getById("minecraft:turtle");
    EntityType<Vex> VEX = getById("minecraft:vex");
    EntityType<Villager> VILLAGER = getById("minecraft:villager");
    EntityType<Vindicator> VINDICATOR = getById("minecraft:vindicator");
    EntityType<WanderingTrader> WANDERING_TRADER = getById("minecraft:wandering_trader");
    EntityType<Witch> WITCH = getById("minecraft:witch");
    EntityType<Wither> WITHER = getById("minecraft:wither");
    EntityType<WitherSkeleton> WITHER_SKELETON = getById("minecraft:wither_skeleton");
    EntityType<WitherSkull> WITHER_SKULL = getById("minecraft:wither_skull");
    EntityType<Wolf> WOLF = getById("minecraft:wolf");
    EntityType<Zoglin> ZOGLIN = getById("minecraft:zoglin");
    EntityType<Zombie> ZOMBIE = getById("minecraft:zombie");
    EntityType<ZombieHorse> ZOMBIE_HORSE = getById("minecraft:zombie_horse");
    EntityType<ZombieVillager> ZOMBIE_VILLAGER = getById("minecraft:zombie_villager");
    EntityType<ZombifiedPiglin> ZOMBIFIED_PIGLIN = getById("minecraft:zombified_piglin");
    EntityType<ItemFrame> GLOW_ITEM_FRAME = getById("minecraft:glow_item_frame");
    EntityType<GlowSquid> GLOW_SQUID = getById("minecraft:glow_squid");

    // endregion :: EntityTypes

    static <T extends Entity> EntityType<T> getById(String id) {
        return Registry.get().getWrapped(EntityType.class, id);
    }

    /**
     * Gets the entity's spawn group (category).
     *
     * @return The spawn group.
     */
    @NotNull
    SpawnGroup getSpawnGroup();

    @NotNull
    ImmutableSet<BlockType> getCanSpawnInside();

    /**
     * Gets whether the entity can be spawned (with {@code /summon} or from spawners, etc.).
     *
     * @return Whether the entity is summonable.
     */
    boolean isSummonable();

    boolean isFireImmune();

    boolean canSpawnFarFromPlayer();

    int maxTrackDistance();

    @NotNull
    Optional<Component> getName();

    // TODO EntityDimensions?

}

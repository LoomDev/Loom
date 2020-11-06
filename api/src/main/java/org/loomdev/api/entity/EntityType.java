package org.loomdev.api.entity;

import com.google.common.collect.ImmutableSet;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.Loom;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.entity.boss.Wither;
import org.loomdev.api.entity.boss.dragon.EnderDragon;
import org.loomdev.api.entity.decoration.*;
import org.loomdev.api.entity.effect.AreaEffectCloud;
import org.loomdev.api.entity.misc.LightningBolt;
import org.loomdev.api.entity.mob.*;
import org.loomdev.api.entity.passive.*;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.entity.projectile.*;
import org.loomdev.api.entity.projectile.thrown.*;
import org.loomdev.api.entity.vehicle.*;
import org.loomdev.api.util.registry.Keyed;

public interface EntityType<T extends Entity> extends Keyed {

    // region :: EntityTypes

    EntityType<AreaEffectCloud> AREA_EFFECT_CLOUD = getById("minecraft:area_effect_cloud");
    EntityType<ArmorStand> ARMOR_STAND = getById("minecraft:armor_stand");
    EntityType<Arrow> ARROW = getById("minecraft:arrow");
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
    EntityType<Enderpearl> ENDER_PEARL = getById("minecraft:ender_pearl");
    EntityType<Enderman> ENDERMAN = getById("minecraft:enderman");
    EntityType<Endermite> ENDERMITE = getById("minecraft:endermite");
    EntityType<Evoker> EVOKER = getById("minecraft:evoker");
    EntityType<EvokerFangs> EVOKER_FANGS = getById("minecraft:evoker_fangs");
    EntityType<ThrownExperienceBottle> EXPERIENCE_BOTTLE = getById("minecraft:experience_bottle");
    EntityType<ExperienceOrb> EXPERIENCE_ORB = getById("minecraft:experience_orb");
    EntityType<EyeOfEnder> EYE_OF_ENDER = getById("minecraft:eye_of_ender");
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
    EntityType<Item> ITEM = getById("minecraft:item");
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
    EntityType<Snowball> SNOWBALL = getById("minecraft:snowball");
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

    // endregion :: EntityTypes

    static <T extends Entity> EntityType<T> getById(String id) {
        return Loom.getRegistry().getWrapped(EntityType.class, id);
    }

    @NotNull
    SpawnGroup getSpawnGroup();

    @NotNull
    ImmutableSet<BlockType> getCanSpawnInside();

    boolean isSummonable();

    boolean isFireImmune();

    boolean canSpawnFarFromPlayer();

    int maxTrackDistance();

    @Nullable
    Component getName();

    // TODO EntityDimensions?

}

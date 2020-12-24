package org.loomdev.api.particle;

import org.loomdev.api.Loom;
import org.loomdev.api.util.registry.Keyed;

/**
 * Represents a type of particle.
 */
public interface ParticleType extends Keyed {

    // region ParticleTypes

    ParticleType AMBIENT_ENTITY_EFFECT = getById("");
    ParticleType ANGRY_VILLAGER = getById("minecraft:angry_villager");
    ParticleType ASH = getById("minecraft:ash");
    ParticleType BARRIER = getById("minecraft:barrier");
    ParticleType BLOCK = getById("minecraft:block");
    ParticleType BUBBLE = getById("minecraft:bubble");
    ParticleType BUBBLE_COLUMN_UP = getById("minecraft:bubble_column_up");
    ParticleType BUBBLE_POP = getById("minecraft:bubble_pop");
    ParticleType CAMPFIRE_COSY_SMOKE = getById("minecraft:campfire_cosy_smoke");
    ParticleType CAMPFIRE_SIGNAL_SMOKE = getById("minecraft:campfire_signal_smoke");
    ParticleType CLOUD = getById("minecraft:cloud");
    ParticleType COMPOSTER = getById("minecraft:composter");
    ParticleType CRIMSON_SPORE = getById("minecraft:crimson_spore");
    ParticleType CRIT = getById("minecraft:crit");
    ParticleType CURRENT_DOWN = getById("minecraft:current_down");
    ParticleType DAMAGE_INDICATOR = getById("minecraft:damage_indicator");
    ParticleType DOLPHIN = getById("minecraft:dolphin");
    ParticleType DRAGON_BREATH = getById("minecraft:dragon_breath");
    ParticleType DRIPPING_DRIPSTONE_LAVA = getById("minecraft:dripping_dripstone_lava");
    ParticleType DRIPPING_DRIPSTONE_WATER = getById("minecraft:dripping_dripstone_water");
    ParticleType DRIPPING_HONEY = getById("minecraft:dripping_honey");
    ParticleType DRIPPING_LAVA = getById("minecraft:dripping_lava");
    ParticleType DRIPPING_OBSIDIAN_TEAR = getById("minecraft:dripping_obsidian_tear");
    ParticleType DRIPPING_WATER = getById("minecraft:dripping_water");
    ParticleType DUST = getById("minecraft:dust");
    ParticleType EFFECT = getById("minecraft:effect");
    ParticleType ELDER_GUARDIAN = getById("minecraft:elder_guardian");
    ParticleType ENCHANT = getById("minecraft:enchant");
    ParticleType ENCHANTED_HIT = getById("minecraft:enchanted_hit");
    ParticleType END_ROD = getById("minecraft:end_rod");
    ParticleType ENTITY_EFFECT = getById("minecraft:entity_effect");
    ParticleType EXPLOSION = getById("minecraft:explosion");
    ParticleType EXPLOSION_EMITTER = getById("minecraft:explosion_emitter");
    ParticleType FALLING_DRIPSTONE_LAVA = getById("minecraft:falling_dripstone_lava");
    ParticleType FALLING_DRIPSTONE_WATER = getById("minecraft:falling_dripstone_water");
    ParticleType FALLING_DUST = getById("minecraft:falling_dust");
    ParticleType FALLING_HONEY = getById("minecraft:falling_honey");
    ParticleType FALLING_LAVA = getById("minecraft:falling_lava");
    ParticleType FALLING_NECTAR = getById("minecraft:falling_nectar");
    ParticleType FALLING_OBSIDIAN_TEAR = getById("minecraft:falling_obsidian_tear");
    ParticleType FALLING_WATER = getById("minecraft:falling_water");
    ParticleType FIREWORK = getById("minecraft:firework");
    ParticleType FISHING = getById("minecraft:fishing");
    ParticleType FLAME = getById("minecraft:flame");
    ParticleType FLASH = getById("minecraft:flash");
    ParticleType HAPPY_VILLAGER = getById("minecraft:happy_villager");
    ParticleType HEART = getById("minecraft:heart");
    ParticleType INSTANT_EFFECT = getById("minecraft:instant_effect");
    ParticleType ITEM = getById("minecraft:item");
    ParticleType ITEM_SLIME = getById("minecraft:item_slime");
    ParticleType ITEM_SNOWBALL = getById("minecraft:item_snowball");
    ParticleType LANDING_HONEY = getById("minecraft:landing_honey");
    ParticleType LANDING_LAVA = getById("minecraft:landing_lava");
    ParticleType LANDING_OBSIDIAN_TEAR = getById("minecraft:landing_obsidian_tear");
    ParticleType LARGE_SMOKE = getById("minecraft:large_smoke");
    ParticleType LAVA = getById("minecraft:lava");
    ParticleType MYCELIUM = getById("minecraft:mycelium");
    ParticleType NAUTILUS = getById("minecraft:nautilus");
    ParticleType NOTE = getById("minecraft:note");
    ParticleType POOF = getById("minecraft:poof");
    ParticleType PORTAL = getById("minecraft:portal");
    ParticleType RAIN = getById("minecraft:rain");
    ParticleType REVERSE_PORTAL = getById("minecraft:reverse_portal");
    ParticleType SMOKE = getById("minecraft:smoke");
    ParticleType SNEEZE = getById("minecraft:sneeze");
    ParticleType SOUL = getById("minecraft:soul");
    ParticleType SOUL_FIRE_FLAME = getById("minecraft:soul_fire_flame");
    ParticleType SPIT = getById("minecraft:spit");
    ParticleType SPLASH = getById("minecraft:splash");
    ParticleType SQUID_INK = getById("minecraft:squid_ink");
    ParticleType SWEEP_ATTACK = getById("minecraft:sweep_attack");
    ParticleType TOTEM_OF_UNDYING = getById("minecraft:totem_of_undying");
    ParticleType UNDERWATER = getById("minecraft:underwater");
    ParticleType WARPED_SPORE = getById("minecraft:warped_spore");
    ParticleType WHITE_ASH = getById("minecraft:white_ash");
    ParticleType WITCH = getById("minecraft:witch");
    ParticleType SMALL_FLAME = getById("minecraft:small_flame");
    ParticleType SNOWFLAKE = getById("minecraft:snowflake");
    ParticleType VIBRATION = getById("minecraft:vibration");
    ParticleType DUST_COLOR_TRANSITION = getById("minecraft:dust_color_transition");

    // endregion ParticleTypes

    /**
     * Gets a ParticleType from its id.
     * @param id The id of the particle type to get.
     * @return The particle type if found otherwise null.
     */
    static ParticleType getById(String id) {
        return Loom.getRegistry().getWrapped(ParticleType.class, id);
    }

}

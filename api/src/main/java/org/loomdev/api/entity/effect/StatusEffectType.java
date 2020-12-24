package org.loomdev.api.entity.effect;

import org.loomdev.api.Loom;
import org.loomdev.api.util.registry.Keyed;

public interface StatusEffectType extends Keyed {

    // region StatusEffectTypes

    StatusEffectType SPEED = getById("minecraft:speed");
    StatusEffectType SLOWNESS = getById("minecraft:slowness");
    StatusEffectType HASTE = getById("minecraft:haste");
    StatusEffectType MINING_FATIGUE = getById("minecraft:mining_fatigue");
    StatusEffectType STRENGTH = getById("minecraft:strength");
    StatusEffectType INSTANT_HEALTH = getById("minecraft:instant_health");
    StatusEffectType INSTANT_DAMAGE = getById("minecraft:instant_damage");
    StatusEffectType JUMP_BOOST = getById("minecraft:jump_boost");
    StatusEffectType NAUSEA = getById("minecraft:nausea");
    StatusEffectType REGENERATION = getById("minecraft:regeneration");
    StatusEffectType RESISTANCE = getById("minecraft:resistance");
    StatusEffectType FIRE_RESISTANCE = getById("minecraft:fire_resistance");
    StatusEffectType WATER_BREATHING = getById("minecraft:water_breathing");
    StatusEffectType INVISIBILITY = getById("minecraft:invisibility");
    StatusEffectType BLINDNESS = getById("minecraft:blindness");
    StatusEffectType NIGHT_VISION = getById("minecraft:night_vision");
    StatusEffectType HUNGER = getById("minecraft:hunger");
    StatusEffectType WEAKNESS = getById("minecraft:weakness");
    StatusEffectType POISON = getById("minecraft:poison");
    StatusEffectType WITHER = getById("minecraft:wither");
    StatusEffectType HEALTH_BOOST = getById("minecraft:health_boost");
    StatusEffectType ABSORPTION = getById("minecraft:absorption");
    StatusEffectType SATURATION = getById("minecraft:saturation");
    StatusEffectType GLOWING = getById("minecraft:glowing");
    StatusEffectType LEVITATION = getById("minecraft:levitation");
    StatusEffectType LUCK = getById("minecraft:luck");
    StatusEffectType UNLUCK = getById("minecraft:unluck");
    StatusEffectType SLOW_FALLING = getById("minecraft:slow_falling");
    StatusEffectType CONDUIT_POWER = getById("minecraft:conduit_power");
    StatusEffectType DOLPHINS_GRACE = getById("minecraft:dolphins_grace");
    StatusEffectType BAD_OMEN = getById("minecraft:bad_omen");
    StatusEffectType HERO_OF_THE_VILLAGE = getById("minecraft:hero_of_the_village");

    // endregion StatusEffectTypes

    /**
     * Gets a status effect type by its id.
     * @param id The id of the status effect type to get.
     * @return The status effect type if found, otherwise null.
     */
    static StatusEffectType getById(String id) {
        return Loom.getRegistry().getWrapped(StatusEffectType.class, id);
    }
}

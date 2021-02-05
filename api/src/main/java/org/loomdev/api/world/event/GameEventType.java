package org.loomdev.api.world.event;

import org.loomdev.api.util.registry.Keyed;
import org.loomdev.api.util.registry.Registry;

public interface GameEventType extends Keyed {

    // region GameEvents

    GameEventType EXPLODE = getById("minecraft:explode");
    GameEventType BLOCK_DESTROY = getById("minecraft:block_destroy");
    GameEventType BLOCK_OPEN = getById("minecraft:block_open");
    GameEventType FLUID_PLACE = getById("minecraft:fluid_place");
    GameEventType BLOCK_ATTACH = getById("minecraft:block_attach");
    GameEventType WOLF_SHAKING = getById("minecraft:wolf_shaking");
    GameEventType ENTITY_PLACE = getById("minecraft:entity_place");
    GameEventType SPLASH = getById("minecraft:splash");
    GameEventType BLOCK_DETACH = getById("minecraft:block_detach");
    GameEventType MOB_INTERACT = getById("minecraft:mob_interact");
    GameEventType ENTITY_DAMAGED = getById("minecraft:entity_damaged");
    GameEventType PISTON_CONTRACT = getById("minecraft:piston_contract");
    GameEventType BLOCK_CHANGE = getById("minecraft:block_change");
    GameEventType FLAP = getById("minecraft:flap");
    GameEventType PROJECTILE_SHOOT = getById("minecraft:projectile_shoot");
    GameEventType DISPENSE_FAIL = getById("minecraft:dispense_fail");
    GameEventType HIT_GROUND = getById("minecraft:hit_ground");
    GameEventType BLOCK_UNPRESS = getById("minecraft:block_unpress");
    GameEventType PISTON_EXTEND = getById("minecraft:piston_extend");
    GameEventType PROJECTILE_LAND = getById("minecraft:projectile_land");
    GameEventType EQUIP = getById("minecraft:equip");
    GameEventType ENTITY_KILLED = getById("minecraft:entity_killed");
    GameEventType ELYTRA_FREE_FALL = getById("minecraft:elytra_free_fall");
    GameEventType RAVAGER_ROAR = getById("minecraft:ravager_roar");
    GameEventType MINECART_MOVING = getById("minecraft:minecart_moving");
    GameEventType STEP = getById("minecraft:step");
    GameEventType CONTAINER_CLOSE = getById("minecraft:container_close");
    GameEventType FISHING_ROD_CAST = getById("minecraft:fishing_rod_cast");
    GameEventType LIGHTNING_STRIKE = getById("minecraft:lightning_strike");
    GameEventType SHULKER_OPEN = getById("minecraft:shulker_open");
    GameEventType CONTAINER_OPEN = getById("minecraft:container_open");
    GameEventType PRIME_FUSE = getById("minecraft:prime_fuse");
    GameEventType SWIM = getById("minecraft:swim");
    GameEventType BLOCK_CLOSE = getById("minecraft:block_close");
    GameEventType SHEAR = getById("minecraft:shear");
    GameEventType DRINKING_FINISH = getById("minecraft:drinking_finish");
    GameEventType FLUID_PICKUP = getById("minecraft:fluid_pickup");
    GameEventType EAT = getById("minecraft:eat");
    GameEventType FISHING_ROD_REEL_IN = getById("minecraft:fishing_rod_reel_in");
    GameEventType RING_BELL = getById("minecraft:ring_bell");
    GameEventType BLOCK_SWITCH = getById("minecraft:block_switch");
    GameEventType BLOCK_UNSWITCH = getById("minecraft:block_unswitch");
    GameEventType BLOCK_PLACE = getById("minecraft:block_place");
    GameEventType SHULKER_CLOSE = getById("minecraft:shulker_close");
    GameEventType BLOCK_PRESS = getById("minecraft:block_press");

    // endregion GameEvents

    static GameEventType getById(String key) {
        return Registry.get().getWrapped(GameEventType.class, key);
    }

    int getNotificationRadius();
}

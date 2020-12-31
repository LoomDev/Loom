package org.loomdev.api.world.event;

import org.loomdev.api.Loom;
import org.loomdev.api.util.registry.Keyed;

public interface GameEventType extends Keyed {

    // region GameEvents

    GameEventType STEP = getById("minecraft:step");
    GameEventType SWIM = getById("minecraft:swim");
    GameEventType FLAP = getById("minecraft:flap");
    GameEventType ELYTRA_FREE_FALL = getById("minecraft:elytra_free_fall");
    GameEventType HIT_GROUND = getById("minecraft:hit_ground");
    GameEventType SPLASH = getById("minecraft:splash");
    GameEventType PROJECTILE_SHOOT = getById("minecraft:projectile_shoot");
    GameEventType PROJECTILE_LAND = getById("minecraft:projectile_land");
    GameEventType ENTITY_HIT = getById("minecraft:entity_hit");
    GameEventType BLOCK_PLACE = getById("minecraft:block_place");
    GameEventType BLOCK_DESTROY = getById("minecraft:block_destroy");
    GameEventType FLUID_PLACE = getById("minecraft:fluid_place");
    GameEventType FLUID_PICKUP = getById("minecraft:fluid_pickup");
    GameEventType BLOCK_OPEN = getById("minecraft:block_open");
    GameEventType BLOCK_CLOSE = getById("minecraft:block_close");
    GameEventType BLOCK_SWITCH = getById("minecraft:block_switch");
    GameEventType BLOCK_UNSWITCH = getById("minecraft:block_unswitch");
    GameEventType BLOCK_ATTACH = getById("minecraft:block_attach");
    GameEventType BLOCK_DETACH = getById("minecraft:block_detach");
    GameEventType BLOCK_PRESS = getById("minecraft:block_press");
    GameEventType BLOCK_UNPRESS = getById("minecraft:block_unpress");
    GameEventType CONTAINER_OPEN = getById("minecraft:container_open");
    GameEventType CONTAINER_CLOSE = getById("minecraft:container_close");
    GameEventType EXPLODE = getById("minecraft:explode");
    GameEventType ARMOR_STAND_ADD_ITEM = getById("minecraft:armor_stand_add_item");
    GameEventType WOLF_SHAKING = getById("minecraft:wolf_shaking");
    GameEventType DISPENSE_FAIL = getById("minecraft:dispense_fail");
    GameEventType FISHING_ROD_CAST = getById("minecraft:fishing_rod_cast");
    GameEventType FISHING_ROD_REEL_IN = getById("minecraft:fishing_rod_reel_in");
    GameEventType PISTON_EXTEND = getById("minecraft:piston_extend");
    GameEventType PISTON_CONTRACT = getById("minecraft:piston_contract");
    GameEventType FLINT_AND_STEEL_USE = getById("minecraft:flint_and_steel_use");
    GameEventType EATING_FINISH = getById("minecraft:eating_finish");
    GameEventType LIGHTNING_STRIKE = getById("minecraft:lightning_strike");

    // endregion GameEvents

    static GameEventType getById(String key) {
        return Loom.getRegistry().getWrapped(GameEventType.class, key);
    }

    int getNotificationRadius();
}

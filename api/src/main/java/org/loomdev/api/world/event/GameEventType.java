package org.loomdev.api.world.event;

import org.loomdev.api.Loom;
import org.loomdev.api.util.registry.Keyed;

public interface GameEventType extends Keyed {

    // region GameEvents
    GameEventType STEP = getById("step");
    GameEventType SWIM = getById("swim");
    GameEventType FLAP = getById("flap");
    GameEventType ELYTRA_FREE_FALL = getById("elytra_free_fall");
    GameEventType HIT_GROUND = getById("hit_ground");
    GameEventType SPLASH = getById("splash");
    GameEventType PROJECTILE_SHOOT = getById("projectile_shoot");
    GameEventType PROJECTILE_LAND = getById("projectile_land");
    GameEventType ENTITY_HIT = getById("entity_hit");
    GameEventType BLOCK_PLACE = getById("block_place");
    GameEventType BLOCK_DESTROY = getById("block_destroy");
    GameEventType FLUID_PLACE = getById("fluid_place");
    GameEventType FLUID_PICKUP = getById("fluid_pickup");
    GameEventType BLOCK_OPEN = getById("block_open");
    GameEventType BLOCK_CLOSE = getById("block_close");
    GameEventType BLOCK_SWITCH = getById("block_switch");
    GameEventType BLOCK_UNSWITCH = getById("block_unswitch");
    GameEventType BLOCK_ATTACH = getById("block_attach");
    GameEventType BLOCK_DETACH = getById("block_detach");
    GameEventType BLOCK_PRESS = getById("block_press");
    GameEventType BLOCK_UNPRESS = getById("block_unpress");
    GameEventType CONTAINER_OPEN = getById("container_open");
    GameEventType CONTAINER_CLOSE = getById("container_close");
    GameEventType EXPLODE = getById("explode");
    GameEventType ARMOR_STAND_ADD_ITEM = getById("armor_stand_add_item");
    GameEventType WOLF_SHAKING = getById("wolf_shaking");
    GameEventType DISPENSE_FAIL = getById("dispense_fail");
    GameEventType FISHING_ROD_CAST = getById("fishing_rod_cast");
    GameEventType FISHING_ROD_REEL_IN = getById("fishing_rod_reel_in");
    GameEventType PISTON_EXTEND = getById("piston_extend");
    GameEventType PISTON_CONTRACT = getById("piston_contract");
    GameEventType FLINT_AND_STEEL_USE = getById("flint_and_steel_use");
    GameEventType EATING_FINISH = getById("eating_finish");
    GameEventType LIGHTNING_STRIKE = getById("lightning_strike");
    // endregion GameEvents

    static GameEventType getById(String key) {
        return Loom.getRegistry().getWrapped(GameEventType.class, key);
    }

    int getNotificationRadius();
}

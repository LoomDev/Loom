package org.loomdev.api.entity.damage;

import java.util.Map;
import java.util.HashMap;

/**
 * Represents a cause of damage.
 */
public enum DamageSource {
    IN_FIRE("inFire"),
    LIGHTNING_BOLT("lightningBolt"),
    ON_FIRE("onFire"),
    LAVA("lava"),
    /**
     * Damage caused by magma blocks.
     */
    HOT_FLOOR("hotFloor"),
    /**
     * Damage caused by being inside a block.
     */
    INSIDE_BLOCK("inWall"),
    /**
     * Damage caused by too many entities being in the same spot.
     */
    CRAMMING("cramming"),
    DROWN("drown"),
    STARVE("starve"),
    CACTUS("cactus"),
    FALL("fall"),
    FLY_INTO_WALL("flyIntoWall"),
    OUT_OF_WORLD("outOfWorld"),
    /**
     * Damage caused for no particular reason.
     */
    GENERIC("generic"),
    /**
     * Damage caused by potions.
     */
    MAGIC("magic"),
    WITHER("wither"),
    FALLING_ANVIL("anvil"),
    FALLING_BLOCK("fallingBlock"),
    DRAGON_BREATH("dragonBreath"),
    /**
     * Damage caused by being out of water.
     */
    DRY_OUT("dryout"),
    SWEET_BERRY_BUSH("sweetBerryBush"),
    FREEZE("freeze"),
    FALLING_STALACTITE("fallingStalactite");

    private static Map<String, DamageSource> byName = new HashMap<>();
    private String name;

    DamageSource(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the damage source.
     * Unlike {@link #name()}, it returns the name in the format {@code damageSource}.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets a damage source by its name.
     * Unlike {@link #valueOf(String)}, the name is in the format {@code damageSource}.
     *
     * @param name The name.
     * @return The source.
     */
    public static DamageSource getByName(String name) {
        return byName.get(name);
    }

    static {
        for (DamageSource source : values()) {
            byName.put(source.getName(), source);
        }
    }
}

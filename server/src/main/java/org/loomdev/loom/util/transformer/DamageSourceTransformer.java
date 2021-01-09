package org.loomdev.loom.util.transformer;

import net.minecraft.world.damagesource.DamageSource;
import org.jetbrains.annotations.NotNull;

public class DamageSourceTransformer {

    private DamageSourceTransformer() {
        throw new UnsupportedOperationException("DamageSourceTransformer shouldn't be initialized.");
    }

    @NotNull
    public static DamageSource toMinecraft(org.loomdev.api.entity.damage.DamageSource source) {
        switch (source) {
            case IN_FIRE:
                return DamageSource.IN_FIRE;
            case LIGHTNING_BOLT:
                return DamageSource.LIGHTNING_BOLT;
            case ON_FIRE:
                return DamageSource.ON_FIRE;
            case LAVA:
                return DamageSource.LAVA;
            case HOT_FLOOR:
                return DamageSource.HOT_FLOOR;
            case IN_WALL:
                return DamageSource.IN_WALL;
            case CRAMMING:
                return DamageSource.CRAMMING;
            case DROWN:
                return DamageSource.DROWN;
            case STARVE:
                return DamageSource.STARVE;
            case CACTUS:
                return DamageSource.CACTUS;
            case FALL:
                return DamageSource.FALL;
            case FLY_INTO_WALL:
                return DamageSource.FLY_INTO_WALL;
            case OUT_OF_WORLD:
                return DamageSource.OUT_OF_WORLD;
            case GENERIC:
                return DamageSource.GENERIC;
            case MAGIC:
                return DamageSource.MAGIC;
            case WITHER:
                return DamageSource.WITHER;
            case FALLING_ANVIL:
                return DamageSource.ANVIL;
            case FALLING_BLOCK:
                return DamageSource.FALLING_BLOCK;
            case DRAGON_BREATH:
                return DamageSource.DRAGON_BREATH;
            case DRYOUT:
                return DamageSource.DRY_OUT;
            case SWEET_BERRY_BUSH:
                return DamageSource.SWEET_BERRY_BUSH;
            case FREEZE:
                return DamageSource.FREEZE;
            case FALLING_STALACTITE:
                return DamageSource.FALLING_STALACTITE;
            default:
                return DamageSource.GENERIC;
        }
    }
}

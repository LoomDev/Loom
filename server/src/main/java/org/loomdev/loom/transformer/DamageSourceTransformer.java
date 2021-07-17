package org.loomdev.loom.transformer;

import net.minecraft.world.damagesource.DamageSource;
import org.jetbrains.annotations.NotNull;

public final class DamageSourceTransformer implements Transformer<DamageSource, org.loomdev.api.entity.damage.DamageSource> {

    protected DamageSourceTransformer() {
    }

    @Override
    @NotNull
    public DamageSource toMinecraft(@NotNull org.loomdev.api.entity.damage.DamageSource damageSource) {
        return switch (damageSource) {
            case IN_FIRE -> DamageSource.IN_FIRE;
            case LIGHTNING_BOLT -> DamageSource.LIGHTNING_BOLT;
            case ON_FIRE -> DamageSource.ON_FIRE;
            case LAVA -> DamageSource.LAVA;
            case HOT_FLOOR -> DamageSource.HOT_FLOOR;
            case INSIDE_BLOCK -> DamageSource.IN_WALL;
            case CRAMMING -> DamageSource.CRAMMING;
            case DROWN -> DamageSource.DROWN;
            case STARVE -> DamageSource.STARVE;
            case CACTUS -> DamageSource.CACTUS;
            case FALL -> DamageSource.FALL;
            case FLY_INTO_WALL -> DamageSource.FLY_INTO_WALL;
            case OUT_OF_WORLD -> DamageSource.OUT_OF_WORLD;
            case MAGIC -> DamageSource.MAGIC;
            case WITHER -> DamageSource.WITHER;
            case FALLING_ANVIL -> DamageSource.ANVIL;
            case FALLING_BLOCK -> DamageSource.FALLING_BLOCK;
            case DRAGON_BREATH -> DamageSource.DRAGON_BREATH;
            case DRY_OUT -> DamageSource.DRY_OUT;
            case SWEET_BERRY_BUSH -> DamageSource.SWEET_BERRY_BUSH;
            case FREEZE -> DamageSource.FREEZE;
            case FALLING_STALACTITE -> DamageSource.FALLING_STALACTITE;
            case GENERIC -> DamageSource.GENERIC;
        };
    }

    @NotNull
    @Override
    public org.loomdev.api.entity.damage.DamageSource toLoom(@NotNull DamageSource minecraftObject) {
        throw new UnsupportedOperationException();
    }
}

package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.PiglinBruteEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.PiglinBrute;

public class PiglinBruteImpl extends AbstractPiglinImpl implements PiglinBrute {

    public PiglinBruteImpl(PiglinBruteEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PIGLIN_BRUTE;
    }

    @Override
    public @NotNull PiglinBruteEntity getMinecraftEntity() {
        return (PiglinBruteEntity) super.getMinecraftEntity();
    }
}

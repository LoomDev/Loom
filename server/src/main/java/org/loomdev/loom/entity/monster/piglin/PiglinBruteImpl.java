package org.loomdev.loom.entity.monster.piglin;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.PiglinBrute;

public class PiglinBruteImpl extends AbstractPiglinImpl implements PiglinBrute {

    public PiglinBruteImpl(net.minecraft.world.entity.monster.piglin.PiglinBrute entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<PiglinBrute> getType() {
        return EntityType.PIGLIN_BRUTE;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.piglin.PiglinBrute getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.piglin.PiglinBrute) super.getMinecraftEntity();
    }
}

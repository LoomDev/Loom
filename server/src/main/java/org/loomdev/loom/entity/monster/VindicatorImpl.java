package org.loomdev.loom.entity.monster;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Vindicator;

public class VindicatorImpl extends AbstractIllagerImpl implements Vindicator {

    public VindicatorImpl(net.minecraft.world.entity.monster.Vindicator entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Vindicator> getType() {
        return EntityType.VINDICATOR;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.Vindicator getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Vindicator) super.getMinecraftEntity();
    }
}

package org.loomdev.loom.entity.animal.golem;

import net.minecraft.world.entity.animal.AbstractGolem;
import org.jetbrains.annotations.NotNull;
import org.loomdev.loom.entity.PathfinderMob;

public abstract class AbstractGolemImpl extends PathfinderMob {

    public AbstractGolemImpl(AbstractGolem entity) {
        super(entity);
    }

    @Override
    @NotNull
    public AbstractGolem getMinecraftEntity() {
        return (AbstractGolem) super.getMinecraftEntity();
    }
}

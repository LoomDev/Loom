package org.loomdev.loom.entity.monster;

import net.minecraft.world.entity.monster.AbstractIllager;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.mob.Illager;
import org.loomdev.loom.entity.raid.RaiderImpl;

public abstract class AbstractIllagerImpl extends RaiderImpl implements Illager {

    public AbstractIllagerImpl(AbstractIllager entity) {
        super(entity);
    }

    @Override
    @NotNull
    public AbstractIllager getMinecraftEntity() {
        return (AbstractIllager) super.getMinecraftEntity();
    }
}

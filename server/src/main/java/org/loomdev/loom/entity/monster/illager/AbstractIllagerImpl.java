package org.loomdev.loom.entity.monster.illager;

import net.minecraft.world.entity.monster.AbstractIllager;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.monster.illager.Illager;
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

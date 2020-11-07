package org.loomdev.loom.entity.monster.illager;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.monster.illager.Ravager;
import org.loomdev.loom.entity.raid.RaiderImpl;

public class RavagerImpl extends RaiderImpl implements Ravager {

    public RavagerImpl(net.minecraft.world.entity.monster.Ravager entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Ravager> getType() {
        return EntityType.RAVAGER;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.Ravager getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Ravager) super.getMinecraftEntity();
    }
}

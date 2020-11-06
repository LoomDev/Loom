package org.loomdev.loom.entity.monster;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Evoker;

public class EvokerImpl extends SpellcastingIllagerImpl implements Evoker {

    public EvokerImpl(net.minecraft.world.entity.monster.Evoker entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Evoker> getType() {
        return EntityType.EVOKER;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.Evoker getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Evoker) super.getMinecraftEntity();
    }
}

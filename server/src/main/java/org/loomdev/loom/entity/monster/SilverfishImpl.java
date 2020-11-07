package org.loomdev.loom.entity.monster;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.monster.Silverfish;

public class SilverfishImpl extends MonsterImpl implements Silverfish {

    public SilverfishImpl(net.minecraft.world.entity.monster.Silverfish entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Silverfish> getType() {
        return EntityType.SILVERFISH;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.Silverfish getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Silverfish) super.getMinecraftEntity();
    }
}

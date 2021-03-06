package org.loomdev.loom.entity.monster.zombie;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.monster.zombie.Husk;

public class HuskImpl extends ZombieImpl implements Husk {

    public HuskImpl(net.minecraft.world.entity.monster.Husk entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Husk> getType() {
        return EntityType.HUSK;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.Husk getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Husk) super.getMinecraftEntity();
    }
}

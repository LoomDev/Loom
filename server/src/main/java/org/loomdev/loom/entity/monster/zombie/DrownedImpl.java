package org.loomdev.loom.entity.monster.zombie;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.monster.zombie.Drowned;

public class DrownedImpl extends ZombieImpl implements Drowned {

    public DrownedImpl(net.minecraft.world.entity.monster.Drowned entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Drowned> getType() {
        return EntityType.DROWNED;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.Drowned getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Drowned) super.getMinecraftEntity();
    }
}

package org.loomdev.loom.entity.monster;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.monster.Enemy;
import org.loomdev.api.entity.monster.Slime;

public class SlimeImpl extends MobEntityImpl implements Slime, Enemy {

    public SlimeImpl(net.minecraft.world.entity.monster.Slime entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<? extends Slime> getType() {
        return EntityType.SLIME;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.Slime getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Slime) super.getMinecraftEntity();
    }

    @Override
    public int getSize() {
        return getMinecraftEntity().getSize();
    }

    @Override
    public void setSize(int size) {
        getMinecraftEntity().entityData.set(net.minecraft.world.entity.monster.Slime.ID_SIZE, size);
    }
}

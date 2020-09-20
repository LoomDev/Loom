package org.loomdev.loom.entity.mob;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.mob.MobEntity;
import org.loomdev.api.entity.player.Player;
import org.loomdev.loom.entity.LivingEntityImpl;

public abstract class MobEntityImpl extends LivingEntityImpl implements MobEntity {

    public MobEntityImpl(net.minecraft.entity.mob.MobEntity entity) {
        super(entity);
    }

    @Override
    public net.minecraft.entity.mob.@NotNull MobEntity getMinecraftEntity() {
        return (net.minecraft.entity.mob.MobEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean hasAi() {
        return getMinecraftEntity().isAiDisabled();
    }

    @Override
    public void setAi(boolean flag) {
        getMinecraftEntity().setAiDisabled(!flag);
    }

    @Override
    public boolean isPersistent() {
        return getMinecraftEntity().isPersistent();
    }

    @Override
    public void setPersistent(boolean flag) {
        getMinecraftEntity().persistent = flag;
    }

    @Override
    public boolean canBeLeashedBy(Player player) {
        return false;
    }

}

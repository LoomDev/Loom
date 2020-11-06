package org.loomdev.loom.entity.monster;

import net.minecraft.world.entity.Mob;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.mob.MobEntity;
import org.loomdev.api.entity.player.Player;
import org.loomdev.loom.entity.LivingEntityImpl;

public abstract class MobEntityImpl extends LivingEntityImpl implements MobEntity {

    public MobEntityImpl(Mob entity) {
        super(entity);
    }

    @Override
    @NotNull
    public Mob getMinecraftEntity() {
        return (Mob) super.getMinecraftEntity();
    }

    @Override
    public boolean hasAi() {
        return !getMinecraftEntity().isNoAi();
    }

    @Override
    public void setAi(boolean flag) {
        getMinecraftEntity().setNoAi(!flag);
    }

    @Override
    public boolean isPersistent() {
        return getMinecraftEntity().isPersistenceRequired();
    }

    @Override
    public void setPersistent(boolean flag) {
        getMinecraftEntity().persistenceRequired = flag;
    }

    @Override
    public boolean canBeLeashedBy(@NotNull Player player) {
        return false;
    }
}

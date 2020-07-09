package org.loomdev.loom.entity.mob;

import net.minecraft.entity.LivingEntity;
import org.loomdev.api.entity.mob.MobEntity;
import org.loomdev.loom.entity.LivingEntityImpl;

public class MobEntityImpl extends LivingEntityImpl implements MobEntity {
    public MobEntityImpl(net.minecraft.entity.mob.MobEntity entity) {
        super(entity);
    }

    @Override
    public net.minecraft.entity.mob.MobEntity getMinecraftEntity() {
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
}

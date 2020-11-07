package org.loomdev.loom.entity.monster;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.monster.Witch;
import org.loomdev.loom.entity.LivingEntityImpl;
import org.loomdev.loom.entity.raid.RaiderImpl;

public class WitchImpl extends RaiderImpl implements Witch, RangedAttackMob {

    public WitchImpl(net.minecraft.world.entity.monster.Witch entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Witch> getType() {
        return EntityType.WITCH;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.Witch getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Witch) super.getMinecraftEntity();
    }

    @Override
    public void performRangedAttack(@NotNull LivingEntity entity, float f) {
        getMinecraftEntity().performRangedAttack(((LivingEntityImpl) entity).getMinecraftEntity(), f);
    }
}

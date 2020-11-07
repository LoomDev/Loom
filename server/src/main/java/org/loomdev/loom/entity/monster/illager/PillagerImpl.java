package org.loomdev.loom.entity.monster.illager;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.monster.illager.Pillager;
import org.loomdev.loom.entity.EntityImpl;

public class PillagerImpl extends AbstractIllagerImpl implements Pillager {

    public PillagerImpl(net.minecraft.world.entity.monster.Pillager entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Pillager> getType() {
        return EntityType.PILLAGER;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.Pillager getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Pillager) super.getMinecraftEntity();
    }

    @Override
    public boolean isChargingCrossbow() {
        return getMinecraftEntity().entityData.get(net.minecraft.world.entity.monster.Pillager.IS_CHARGING_CROSSBOW);
    }

    @Override
    public void setChargingCrossbow(boolean charging) {
        getMinecraftEntity().setChargingCrossbow(charging);
    }

    @Override
    public boolean isAlliedTo(@NotNull Entity entity) {
        return getMinecraftEntity().isAlliedTo(((EntityImpl) entity).getMinecraftEntity());
    }
}

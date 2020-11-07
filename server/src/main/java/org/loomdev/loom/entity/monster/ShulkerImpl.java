package org.loomdev.loom.entity.monster;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.monster.Enemy;
import org.loomdev.api.entity.monster.Shulker;
import org.loomdev.api.util.DyeColor;
import org.loomdev.loom.entity.animal.golem.AbstractGolemImpl;

public class ShulkerImpl extends AbstractGolemImpl implements Shulker, Enemy {

    public ShulkerImpl(net.minecraft.world.entity.monster.Shulker entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Shulker> getType() {
        return EntityType.SHULKER;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.Shulker getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Shulker) super.getMinecraftEntity();
    }

    @Override
    public DyeColor getColor() {
        byte color = getMinecraftEntity().entityData.get(net.minecraft.world.entity.monster.Shulker.DATA_COLOR_ID);
        return color == 16 ? null : DyeColor.getById(color);
    }

    @Override
    public void setColor(DyeColor dyeColor) {
        var color = dyeColor == null ? 16 : (byte) dyeColor.getId();
        getMinecraftEntity().entityData.set(net.minecraft.world.entity.monster.Shulker.DATA_COLOR_ID, color);
    }
}

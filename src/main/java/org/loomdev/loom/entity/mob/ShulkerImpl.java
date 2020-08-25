package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.ShulkerEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Shulker;
import org.loomdev.api.util.DyeColor;
import org.loomdev.loom.GolemImpl;

public class ShulkerImpl extends GolemImpl implements Shulker {

    public ShulkerImpl(ShulkerEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SHULKER;
    }

    @Override
    public @NotNull ShulkerEntity getMinecraftEntity() {
        return (ShulkerEntity) super.getMinecraftEntity();
    }

    @Override
    public DyeColor getColor() {
        byte color = getMinecraftEntity().dataTracker.get(ShulkerEntity.COLOR);
        return color == 16 ? null : DyeColor.getById(color);
    }

    @Override
    public void setColor(DyeColor dyeColor) {
        getMinecraftEntity().dataTracker.set(ShulkerEntity.COLOR, dyeColor == null ? 16 : (byte) dyeColor.getId());
    }
}

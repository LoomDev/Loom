package org.loomdev.loom.entity.boss.dragon;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.boss.dragon.EnderDragon;
import org.loomdev.api.entity.boss.dragon.EnderDragonPart;
import org.loomdev.loom.entity.EntityImpl;

public class EnderDragonPartImpl extends EntityImpl implements EnderDragonPart {

    public EnderDragonPartImpl(net.minecraft.entity.boss.dragon.EnderDragonPart entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ENDER_DRAGON;
    }

    @Override
    public net.minecraft.entity.boss.dragon.@NotNull EnderDragonPart getMinecraftEntity() {
        return (net.minecraft.entity.boss.dragon.EnderDragonPart) super.getMinecraftEntity();
    }

    @Override
    public @NotNull EnderDragon getParent() {
        return (EnderDragonImpl) getMinecraftEntity().owner.getLoomEntity();
    }
}

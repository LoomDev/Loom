package org.loomdev.loom.entity.boss.dragon;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.boss.dragon.EnderDragon;
import org.loomdev.api.entity.boss.dragon.EnderDragonPart;
import org.loomdev.loom.entity.EntityImpl;

public class EnderDragonPartImpl extends EntityImpl implements EnderDragonPart {

    public EnderDragonPartImpl(net.minecraft.entity.boss.dragon.EnderDragonPart entity) {
        super(entity);
    }

    @Override
    public @NonNull EntityType getType() {
        return EntityType.UNKNOWN;
    }

    @Override
    public net.minecraft.entity.boss.dragon.EnderDragonPart getMinecraftEntity() {
        return (net.minecraft.entity.boss.dragon.EnderDragonPart) super.getMinecraftEntity();
    }

    @Override
    public @NonNull EnderDragon getParent() {
        return (EnderDragonImpl) getMinecraftEntity().owner.getLoomEntity();
    }
}

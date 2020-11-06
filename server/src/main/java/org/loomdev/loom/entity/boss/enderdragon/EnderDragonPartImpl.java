package org.loomdev.loom.entity.boss.enderdragon;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.boss.dragon.EnderDragon;
import org.loomdev.api.entity.boss.dragon.EnderDragonPart;
import org.loomdev.loom.entity.EntityImpl;

public class EnderDragonPartImpl extends EntityImpl implements EnderDragonPart {

    public EnderDragonPartImpl(net.minecraft.world.entity.boss.EnderDragonPart entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<EnderDragon> getType() { // TODO shouldn't this just return the type part, not the whole dragon?
        return EntityType.ENDER_DRAGON;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.boss.EnderDragonPart getMinecraftEntity() {
        return (net.minecraft.world.entity.boss.EnderDragonPart) super.getMinecraftEntity();
    }

    @Override
    public @NotNull EnderDragon getParent() {
        return (EnderDragonImpl) getMinecraftEntity().parentMob.getLoomEntity();
    }
}

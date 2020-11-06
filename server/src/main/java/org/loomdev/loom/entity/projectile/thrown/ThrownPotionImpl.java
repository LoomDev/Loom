package org.loomdev.loom.entity.projectile.thrown;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.thrown.ThrownPotion;

public class ThrownPotionImpl extends AbstractThrowableItemImpl implements ThrownPotion {

    public ThrownPotionImpl(net.minecraft.world.entity.projectile.ThrownPotion entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<ThrownPotion> getType() {
        return EntityType.POTION;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.projectile.ThrownPotion getMinecraftEntity() {
        return (net.minecraft.world.entity.projectile.ThrownPotion) super.getMinecraftEntity();
    }
}

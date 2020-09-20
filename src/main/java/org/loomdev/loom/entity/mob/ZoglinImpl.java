package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.ZoglinEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Zoglin;

public class ZoglinImpl extends HostileEntityImpl implements Zoglin {

    public ZoglinImpl(ZoglinEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType<Zoglin> getType() {
        return EntityType.ZOGLIN;
    }

    @Override
    public @NotNull ZoglinEntity getMinecraftEntity() {
        return (ZoglinEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean isBaby() {
        return getMinecraftEntity().isBaby();
    }

    @Override
    public void setBaby(boolean flag) {
        getMinecraftEntity().setBaby(flag);
    }

}

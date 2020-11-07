package org.loomdev.loom.entity.monster.hoglin;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.monster.hoglin.Zoglin;
import org.loomdev.loom.entity.monster.MonsterImpl;

public class ZoglinImpl extends MonsterImpl implements Zoglin {

    public ZoglinImpl(net.minecraft.world.entity.monster.Zoglin entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Zoglin> getType() {
        return EntityType.ZOGLIN;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.Zoglin getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Zoglin) super.getMinecraftEntity();
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

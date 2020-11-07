package org.loomdev.loom.entity.monster;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.monster.Endermite;

import java.util.OptionalInt;

public class EndermiteImpl extends MonsterImpl implements Endermite {

    public EndermiteImpl(net.minecraft.world.entity.monster.Endermite entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Endermite> getType() {
        return EntityType.ENDERMITE;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.Endermite getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Endermite) super.getMinecraftEntity();
    }

    @Override
    public boolean isPlayerSpawned() {
        return getMinecraftEntity().isPlayerSpawned();
    }

    @Override
    public void setPlayerSpawned(boolean flag) {
        getMinecraftEntity().setPlayerSpawned(flag);
    }

    @Override
    public int getLifeTime() {
        return getMinecraftEntity().life;
    }

    @Override
    public void setLifeTime(int ticks) {
        getMinecraftEntity().life = ticks;
    }

    @Override
    public int getMaxLifeTime() {
        return getMinecraftEntity().lifeTimeOverride.orElse(getMinecraftEntity().vanillaLife);
    }

    @Override
    public void setMaxLifeTime(int i) {
        getMinecraftEntity().lifeTimeOverride = OptionalInt.of(i);
    }

    @Override
    public void resetMaxLifeTime() {
        getMinecraftEntity().lifeTimeOverride = OptionalInt.empty();
    }
}

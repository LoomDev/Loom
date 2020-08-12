package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.EndermiteEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Endermite;

import java.util.OptionalInt;

public class EndermiteImpl extends HostileEntityImpl implements Endermite {

    public EndermiteImpl(EndermiteEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ENDERMITE;
    }

    @Override
    public EndermiteEntity getMinecraftEntity() {
        return (EndermiteEntity) super.getMinecraftEntity();
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
        return getMinecraftEntity().lifeTime;
    }

    @Override
    public void setLifeTime(int i) {
        getMinecraftEntity().lifeTime = i;
    }

    @Override
    public int getMaxLifeTime() {
        return getMinecraftEntity().overrideMaxLifeTime.orElse(getMinecraftEntity().defaultMaxLifeTime);
    }

    @Override
    public void setMaxLifeTime(int i) {
        getMinecraftEntity().overrideMaxLifeTime = OptionalInt.of(i);
    }

    @Override
    public void resetMaxLifeTime() {
        getMinecraftEntity().overrideMaxLifeTime.getClass();
    }
}

package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.SchoolingFishEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.passive.SchoolingFish;

import java.util.OptionalInt;

public abstract class SchoolingFishImpl extends FishImpl implements SchoolingFish {

    public SchoolingFishImpl(SchoolingFishEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull SchoolingFishEntity getMinecraftEntity() {
        return (SchoolingFishEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean hasLeader() {
        return getMinecraftEntity().hasLeader();
    }

    @Override
    public void joinGroupOf(SchoolingFish schoolingFish) {
        getMinecraftEntity().joinGroupOf(((SchoolingFishImpl) schoolingFish).getMinecraftEntity());
    }

    @Override
    public void leaveGroup() {
        getMinecraftEntity().leaveGroup();
    }

    @Override
    public int getMaxGroupSize() {
        return getMinecraftEntity().getMaxGroupSize();
    }

    @Override
    public void setMaxGroupSize(int count) {
        getMinecraftEntity().maxGroupSizeOverride = OptionalInt.of(count);
    }

    @Override
    public void resetMaxGroupSize() {
        getMinecraftEntity().maxGroupSizeOverride = OptionalInt.empty();
    }
}

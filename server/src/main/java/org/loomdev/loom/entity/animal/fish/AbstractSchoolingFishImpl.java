package org.loomdev.loom.entity.animal.fish;

import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.animal.fish.SchoolingFish;

import java.util.OptionalInt;

public abstract class AbstractSchoolingFishImpl extends AbstractFishImpl implements SchoolingFish { // TODO

    public AbstractSchoolingFishImpl(AbstractSchoolingFish entity) {
        super(entity);
    }

    @Override
    @NotNull
    public AbstractSchoolingFish getMinecraftEntity() {
        return (AbstractSchoolingFish) super.getMinecraftEntity();
    }

    @Override
    public boolean hasLeader() {
        return getMinecraftEntity().hasFollowers();
    }

    @Override
    public void joinGroupOf(SchoolingFish schoolingFish) {
        getMinecraftEntity().startFollowing(((AbstractSchoolingFishImpl) schoolingFish).getMinecraftEntity());
    }

    @Override
    public void leaveGroup() {
        getMinecraftEntity().stopFollowing();
    }

    @Override
    public int getMaxGroupSize() {
        return getMinecraftEntity().getMaxSchoolSize();
    }

    @Override
    public void setMaxGroupSize(int count) {
        getMinecraftEntity().maxSchoolSizeOverride = OptionalInt.of(count);
    }

    @Override
    public void resetMaxGroupSize() {
        getMinecraftEntity().maxSchoolSizeOverride = OptionalInt.empty();
    }
}

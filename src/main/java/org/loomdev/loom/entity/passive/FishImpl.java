package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.FishEntity;
import org.loomdev.api.entity.passive.Fish;
import org.loomdev.api.item.ItemStack;
import org.loomdev.loom.entity.mob.PathAwareEntityImpl;

public class FishImpl extends PathAwareEntityImpl implements Fish {

    public FishImpl(FishEntity entity) {
        super(entity);
    }

    @Override
    public FishEntity getMinecraftEntity() {
        return (FishEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean isFromBucket() {
        return getMinecraftEntity().isFromBucket();
    }

    @Override
    public void setFromBucket(boolean flag) {
        getMinecraftEntity().setFromBucket(flag);
    }

    @Override
    public ItemStack getFishBucket() {
        return null; // TODO convert getMinecraftEntity().getFishBucketItem() to Loom ItemStack.
    }
}

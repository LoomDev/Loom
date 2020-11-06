package org.loomdev.loom.entity.animal;

import net.minecraft.world.entity.animal.AbstractFish;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.passive.Fish;
import org.loomdev.api.item.ItemStack;
import org.loomdev.loom.entity.passive.WaterAnimalImpl;

public abstract class FishImpl extends WaterAnimalImpl implements Fish {

    public FishImpl(AbstractFish entity) {
        super(entity);
    }

    @Override
    @NotNull
    public AbstractFish getMinecraftEntity() {
        return (AbstractFish) super.getMinecraftEntity();
    }

    @Override
    public boolean isFromBucket() {
        return getMinecraftEntity().fromBucket();
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

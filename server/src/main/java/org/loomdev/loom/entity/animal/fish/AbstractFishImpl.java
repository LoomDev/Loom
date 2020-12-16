package org.loomdev.loom.entity.animal.fish;

import net.minecraft.world.entity.animal.AbstractFish;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.animal.fish.Fish;
import org.loomdev.api.item.ItemStack;
import org.loomdev.loom.entity.animal.AbstractWaterAnimalImpl;
import org.loomdev.loom.item.ItemStackImpl;

public abstract class AbstractFishImpl extends AbstractWaterAnimalImpl implements Fish {

    public AbstractFishImpl(AbstractFish entity) {
        super(entity);
    }

    @Override
    @NotNull
    public AbstractFish getMinecraftEntity() {
        return (AbstractFish) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public ItemStack getFishBucket() {
        return ItemStackImpl.of(getMinecraftEntity().getBucketItemStack());
    }

    @Override
    public boolean isFromBucket() {
        return getMinecraftEntity().fromBucket();
    }

    @Override
    public void setFromBucket(boolean flag) {
        getMinecraftEntity().setFromBucket(flag);
    }
}

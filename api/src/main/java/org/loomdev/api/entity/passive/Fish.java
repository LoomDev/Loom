package org.loomdev.api.entity.passive;

import org.loomdev.api.entity.mob.WaterAnimal;
import org.loomdev.api.item.ItemStack;

public interface Fish extends WaterAnimal {

    boolean isFromBucket();

    void setFromBucket(boolean flag);

    ItemStack getFishBucket();

}

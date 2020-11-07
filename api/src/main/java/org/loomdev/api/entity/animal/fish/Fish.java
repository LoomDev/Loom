package org.loomdev.api.entity.animal.fish;

import org.loomdev.api.entity.monster.WaterAnimal;
import org.loomdev.api.item.ItemStack;

public interface Fish extends WaterAnimal {

    boolean isFromBucket();

    void setFromBucket(boolean flag);

    ItemStack getFishBucket();

}

package org.loomdev.api.entity.passive;

import org.loomdev.api.entity.mob.WaterCreature;
import org.loomdev.api.item.ItemStack;

public interface Fish extends WaterCreature {

    boolean isFromBucket();

    void setFromBucket(boolean flag);

    ItemStack getFishBucket();

}

package org.loomdev.api.entity.animal.fish;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.animal.Bucketable;
import org.loomdev.api.entity.monster.WaterAnimal;
import org.loomdev.api.item.ItemStack;

public interface Fish extends WaterAnimal, Bucketable {

    boolean isFromBucket();

    @NotNull
    ItemStack getFishBucket();
}

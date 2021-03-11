package org.loomdev.loom.item.property;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeableLeatherItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.property.ItemProperty;
import org.loomdev.api.item.property.data.LeatherDyeData;
import org.loomdev.api.util.Color;
import org.loomdev.loom.item.ItemStackImpl;
import org.loomdev.loom.item.property.data.LeatherDyeDataImpl;

public class LeatherDyeProperty implements ItemProperty<LeatherDyeData> {

    @Override
    @Nullable
    public LeatherDyeData get(@NotNull ItemStack itemStack) {
        var mcStack = ((ItemStackImpl) itemStack).getMinecraftItemStack();
        var dyeableLeatherItem = (DyeableLeatherItem) mcStack.getItem();

        if (!dyeableLeatherItem.hasCustomColor(mcStack)) {
            return new LeatherDyeDataImpl();
        }

        var colorRgb = dyeableLeatherItem.getColor(mcStack);
        return new LeatherDyeDataImpl(Color.fromRgb(colorRgb));
    }

    @Override
    public void apply(@NotNull ItemStack itemStack, @NotNull LeatherDyeData data) {
        var mcStack = ((ItemStackImpl) itemStack).getMinecraftItemStack();
        var dyeableLeatherItem = (DyeableLeatherItem) mcStack.getItem();

        if (!data.hasCustomColor()) {
            dyeableLeatherItem.clearColor(mcStack);
            return;
        }

        dyeableLeatherItem.setColor(mcStack, data.getColor().asRgb());
    }

    @Override
    public boolean canApplyTo(@NotNull ItemStack itemStack) {
        var mcItem = Registry.ITEM.get(new ResourceLocation(itemStack.getType().getKey().toString()));
        return mcItem instanceof DyeableLeatherItem;
    }
}

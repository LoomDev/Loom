package org.loomdev.loom.item.property;

import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.property.ItemProperty;
import org.loomdev.api.item.property.data.RepairableData;
import org.loomdev.loom.item.ItemStackImpl;
import org.loomdev.loom.item.property.data.RepairableDataImpl;
import org.loomdev.loom.util.nbt.NbtType;

public class RepairableItemProperty implements ItemProperty<RepairableData> {

    private static final String REPAIR_COST = "RepairCost";

    @Override
    public @Nullable RepairableData get(@NotNull ItemStack itemStack) {
        var mcStack = ((ItemStackImpl) itemStack).getMinecraftItemStack();

        CompoundTag tag = mcStack.getTag();
        if (tag == null || !tag.contains(REPAIR_COST, NbtType.INT)) {
            return new RepairableDataImpl(0);
        }

        return new RepairableDataImpl(mcStack.getBaseRepairCost());
    }

    @Override
    public void apply(@NotNull ItemStack itemStack, @NotNull RepairableData repairableData) {
        var mcStack = ((ItemStackImpl) itemStack).getMinecraftItemStack();

        if (repairableData.hasRepairCost()) {
            mcStack.setRepairCost(repairableData.getRepairCost());
        }
    }

    @Override
    public boolean canApplyTo(@NotNull ItemStack itemStack) {
        return true;
    }
}

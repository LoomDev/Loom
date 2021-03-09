package org.loomdev.loom.item.property.data;

import org.loomdev.api.item.property.data.RepairableData;

import java.util.OptionalInt;

public class RepairableDataImpl implements RepairableData {

    private OptionalInt repairCost;

    public RepairableDataImpl() {
        this.repairCost = OptionalInt.empty();
    }

    public RepairableDataImpl(int repairCost) {
        this.repairCost = OptionalInt.of(repairCost);
    }

    @Override
    public int getRepairCost() {
        return this.repairCost.orElse(0);
    }

    @Override
    public void setRepairCost(int cost) {
        this.repairCost = OptionalInt.of(Math.max(0, cost));
    }

    @Override
    public void clearRepairCost() {
        this.repairCost = OptionalInt.empty();
    }

    @Override
    public boolean hasRepairCost() {
        return this.repairCost.isPresent();
    }
}

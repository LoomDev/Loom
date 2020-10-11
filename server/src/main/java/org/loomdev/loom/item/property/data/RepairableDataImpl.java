package org.loomdev.loom.item.property.data;

import org.loomdev.api.item.property.data.RepairableData;

public class RepairableDataImpl implements RepairableData {

    private int repairCost;

    public RepairableDataImpl(int repairCost) {
        this.repairCost = repairCost;
    }

    @Override
    public int getRepairCost() {
        return this.repairCost;
    }

    @Override
    public void setRepairCost(int cost) {
        this.repairCost = Math.max(0, cost);
    }

    @Override
    public boolean hasRepairCost() {
        return this.repairCost > 0;
    }
}

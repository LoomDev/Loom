package org.loomdev.api.item.property.data;

public interface RepairableData extends ItemPropertyData<RepairableData> {

    int getRepairCost();

    void setRepairCost(int cost);

    void clearRepairCost();

    boolean hasRepairCost();

}

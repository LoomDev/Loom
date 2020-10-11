package org.loomdev.api.util;

public enum Hand {
    MAIN_HAND(EquipmentSlot.MAINHAND),
    OFF_HAND(EquipmentSlot.OFFHAND);

    private final EquipmentSlot equipmentSlot;

    Hand(EquipmentSlot equipmentSlot) {
        this.equipmentSlot = equipmentSlot;
    }

    public EquipmentSlot getEquipmentSlot() {
        return equipmentSlot;
    }
}

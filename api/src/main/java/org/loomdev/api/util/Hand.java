package org.loomdev.api.util;

/**
 * Represents a player's hand.
 */
public enum Hand {

    MAIN_HAND(EquipmentSlot.MAINHAND),
    OFF_HAND(EquipmentSlot.OFFHAND);

    private final EquipmentSlot equipmentSlot;

    Hand(EquipmentSlot equipmentSlot) {
        this.equipmentSlot = equipmentSlot;
    }

    /**
     * Gets the equipment slot corresponding to the hand.
     * 
     * @return the slot
     */
    public EquipmentSlot getEquipmentSlot() {
        return equipmentSlot;
    }
}

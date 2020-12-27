package org.loomdev.api.util;

/**
 * Represents a player's hand.
 */
public enum Hand {

    MAIN_HAND(EquipmentSlot.MAIN_HAND),
    OFF_HAND(EquipmentSlot.OFF_HAND);

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

package org.loomdev.api.util;

public enum EquipmentSlot {

    MAINHAND(EquipmentSlot.Type.HAND, 0, 0, "mainhand"),
    OFFHAND(EquipmentSlot.Type.HAND, 1, 5, "offhand"),
    FEET(EquipmentSlot.Type.ARMOR, 0, 1, "feet"),
    LEGS(EquipmentSlot.Type.ARMOR, 1, 2, "legs"),
    CHEST(EquipmentSlot.Type.ARMOR, 2, 3, "chest"),
    HEAD(EquipmentSlot.Type.ARMOR, 3, 4, "head");

    private final EquipmentSlot.Type type;
    private final int entitySlotId;
    private final int armorStandSlotId;
    private final String name;

    EquipmentSlot(EquipmentSlot.Type arg, int entitySlotId, int armorStandSlotId, String name) {
        this.type = arg;
        this.entitySlotId = entitySlotId;
        this.armorStandSlotId = armorStandSlotId;
        this.name = name;
    }

    public EquipmentSlot.Type getType() {
        return this.type;
    }

    public int getEntitySlotId() {
        return this.entitySlotId;
    }

    public int getArmorStandSlotId() {
        return this.armorStandSlotId;
    }

    public String getName() {
        return this.name;
    }

    public static enum Type {
        HAND,
        ARMOR;

        private Type() {
        }
    }
}

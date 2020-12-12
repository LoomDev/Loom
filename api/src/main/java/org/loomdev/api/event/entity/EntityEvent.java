package org.loomdev.api.event.entity;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.event.Event;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.util.EquipmentSlot;

public class EntityEvent extends Event {

    private final Entity entity;

    public EntityEvent(Entity entity) {
        this.entity = entity;
    }

    @NotNull
    public Entity getEntity() {
        return this.entity;
    }

    public static class Damage extends EntityEvent {

        public Damage(Entity entity) {
            super(entity);
        }
    }

    public static class Equip extends EntityEvent {

        private EquipmentSlot slot;
        private ItemStack equipment;

        public Equip(Entity entity, EquipmentSlot slot, ItemStack equipment) {
            super(entity);
        }

        @NotNull
        public EquipmentSlot getSlot() {
            return slot;
        }

        public void setSlot(@NotNull EquipmentSlot slot) {
            this.slot = slot;
        }

        @NotNull
        public ItemStack getEquipment() {
            return equipment;
        }

        public void setEquipment(@NotNull ItemStack equipment) {
            this.equipment = equipment;
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }
}

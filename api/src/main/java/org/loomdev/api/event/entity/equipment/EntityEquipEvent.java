package org.loomdev.api.event.entity.equipment;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.entity.EntityEvent;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.util.EquipmentSlot;

/**
 * Fired when an entity equips an armor piece or tool.
 * The armor will not be equipped if this event is cancelled.
 *
 * The equipped armor is also able to be changed using {@link #setEquipment(ItemStack)}.
 */
public class EntityEquipEvent extends EntityEvent implements Cancellable {

    private EquipmentSlot slot;
    private ItemStack equipment;
    private boolean cancelled;

    public EntityEquipEvent(Entity entity, EquipmentSlot slot, ItemStack equipment) {
        super(entity);
        this.slot = slot;
        this.equipment = equipment;
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
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}

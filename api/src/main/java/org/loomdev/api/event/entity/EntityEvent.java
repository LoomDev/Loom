package org.loomdev.api.event.entity;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.damage.DamageSource;
import org.loomdev.api.event.Cancelable;
import org.loomdev.api.event.Event;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.util.EquipmentSlot;

public interface EntityEvent extends Event {

    @NotNull
    Entity getEntity();

    // TODO we should probably split this into environmental damage
    // and entity damaged by another entity
    interface Damage extends EntityEvent, Cancelable {

        @NotNull
        DamageSource getDamageSource();

        interface Environmental extends Damage {
        }

        interface Entity extends Damage {

            @NotNull
            Entity getAttackingEntity();
        }
    }

    interface Equip extends EntityEvent, Cancelable {

        @NotNull
        EquipmentSlot getEquipmentSlot();

        @NotNull
        ItemStack getEquipment();

        void setEquipment(@NotNull ItemStack itemStack);
    }

    interface MobGrief extends EntityEvent, Cancelable {

        @NotNull
        BlockPointer getModifiedPointer();
    }
}

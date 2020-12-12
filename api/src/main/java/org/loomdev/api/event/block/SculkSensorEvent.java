package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.event.Event;

import java.util.Optional;

// TODO Pass a SculkSensor block entity instance to this event once we have implemented it
public class SculkSensorEvent extends Event {

    private final BlockPointer pointer;

    public SculkSensorEvent(BlockPointer pointer) {
        this.pointer = pointer;
    }

    @NotNull
    public BlockPointer getPointer() {
        return pointer;
    }

    public static class Activate extends SculkSensorEvent {

        private final BlockPointer vibrationSource;
        private VibrationType vibrationType;

        public Activate(BlockPointer pointer, BlockPointer vibrationSource, VibrationType vibrationType) {
            super(pointer);
            this.vibrationSource = vibrationSource;
            this.vibrationType = vibrationType;
        }

        @NotNull
        public Optional<BlockPointer> getVibrationSource() {
            return Optional.ofNullable(vibrationSource);
        }

        @NotNull
        public VibrationType getVibrationType() {
            return vibrationType;
        }

        public void setVibrationType(@NotNull VibrationType vibrationType) {
            this.vibrationType = vibrationType;
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }

    public enum VibrationType {

        STEP,
        FLAP,
        SWIM,
        ELYTRA_FREE_FALL,
        HIT_GROUND,
        SPLASH,
        WOLF_SHAKING,
        PROJECTILE_SHOOT,
        PROJECTILE_LAND,
        EATING_START,
        EATING_FINISH,
        ENTITY_HIT,
        ARMOR_STAND_ADD_ITEM,
        BLOCK_OPEN,
        BLOCK_CLOSE,
        BLOCK_SWITCH,
        BLOCK_UNSWITCH,
        BLOCK_PRESS,
        BLOCK_UNPRESS,
        BLOCK_ATTACH,
        BLOCK_DETACH,
        CONTAINER_OPEN,
        CONTAINER_CLOSE,
        DISPENSE_FAIL,
        FLINT_AND_STEEL_USE,
        BLOCK_PLACE,
        BLOCK_DESTROY,
        FLUID_PLACE,
        FLUID_PICKUP,
        FISHING_ROD_CAST,
        FISHING_ROD_REEL_IN,
        PISTON_EXTEND,
        PISTON_CONTRACT,
        EXPLODE,
        LIGHTNING_STRIKE
    }
}

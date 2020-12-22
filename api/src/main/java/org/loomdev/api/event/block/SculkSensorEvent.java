package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.event.Event;
import org.loomdev.api.world.event.GameEventType;

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
        private GameEventType vibrationType;

        public Activate(BlockPointer pointer, BlockPointer vibrationSource, GameEventType vibrationType) {
            super(pointer);
            this.vibrationSource = vibrationSource;
            this.vibrationType = vibrationType;
        }

        @NotNull
        public Optional<BlockPointer> getVibrationSource() {
            return Optional.ofNullable(vibrationSource);
        }

        @NotNull
        public GameEventType getVibrationType() {
            return vibrationType;
        }

        public void setVibrationType(@NotNull GameEventType vibrationType) {
            this.vibrationType = vibrationType;
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }
}

package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.event.Cancelable;
import org.loomdev.api.world.event.GameEventType;

import java.util.Optional;

public interface SculkSensorEvent extends BlockEvent {

    interface Activate extends SculkSensorEvent, Cancelable {

        @NotNull
        Optional<BlockPointer> getVibrationPointer();

        @NotNull
        GameEventType getVibrationType();

        void setVibrationType(@NotNull GameEventType vibrationType);
    }
}

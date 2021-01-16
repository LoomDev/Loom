package org.loomdev.loom.event.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.PositionSource;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.event.block.SculkSensorEvent;
import org.loomdev.api.world.event.GameEventType;
import org.loomdev.loom.block.BlockPointerImpl;
import org.loomdev.loom.world.event.GameEventTypeImpl;

import java.util.Optional;

public class SculkSensorEventImpl extends BlockEventImpl implements SculkSensorEvent {

    public SculkSensorEventImpl(Level level, BlockPos blockPos) {
        super(level, blockPos);
    }

    public static class Activate extends SculkSensorEventImpl implements SculkSensorEvent.Activate {

        private final BlockPointer vibrationPointer;
        private GameEvent gameEvent;

        public Activate(Level level, BlockPos blockPos, PositionSource vibrationSource, GameEvent gameEvent) {
            super(level, blockPos);
            this.vibrationPointer = vibrationSource.getPosition(level)
                    .map(pos -> new BlockPointerImpl(level, pos))
                    .orElse(null);
            this.gameEvent = gameEvent;
        }

        @Override
        @NotNull
        public Optional<BlockPointer> getVibrationPointer() {
            return Optional.ofNullable(vibrationPointer);
        }

        @Override
        @NotNull
        public GameEventType getVibrationType() {
            return GameEventType.getById(Registry.GAME_EVENT.getKey(gameEvent).toString());
        }

        @Override
        public void setVibrationType(@NotNull GameEventType vibrationType) {
            this.gameEvent = ((GameEventTypeImpl) vibrationType).getMinecraftEvent();
        }

        public GameEvent getMinecraftEvent() {
            return gameEvent;
        }
    }
}

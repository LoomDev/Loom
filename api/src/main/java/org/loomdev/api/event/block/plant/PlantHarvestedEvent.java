package org.loomdev.api.event.block.plant;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.entity.passive.AnimalEntity;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.block.BlockEvent;

import java.util.Optional;

/**
 * Fired when a plant block is harvested and reverted back to its
 * initial growth stage. The plant will not drop items and revert to
 * its initial growth stage if this event is cancelled.
 *
 * This event is fired when:
 * <ul>
 * <li>A player harvests a sweet berry bush</li>
 * <li>A fox harvests a sweet berry bush</li>
 * </ul>
 */
public class PlantHarvestedEvent extends BlockEvent implements Cancellable {

    private Player player;
    private AnimalEntity animal;
    private final Cause cause;
    private boolean cancelled;

    public PlantHarvestedEvent(@NotNull Block block, @NotNull Player player) {
        super(block);
        this.player = player;
        this.cause = Cause.PLAYER;
    }

    public PlantHarvestedEvent(@NotNull Block block, @NotNull AnimalEntity animal) {
        super(block);
        this.animal = animal;
        this.cause = Cause.ANIMAL;
    }

    public @NotNull Optional<Player> getPlayer() {
        return Optional.ofNullable(this.player);
    }

    public @NotNull Optional<AnimalEntity> getAnimal() {
        return Optional.ofNullable(this.animal);
    }

    public @NotNull Cause getCause() {
        return this.cause;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Represents the entity that harvested
     * the plant block.
     */
    public enum Cause {
        PLAYER,
        ANIMAL
    }
}

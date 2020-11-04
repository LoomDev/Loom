package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.event.Cancellable;

import java.util.Optional;

/**
 * Fired when a block catches fire or starts burning.
 * The block will not ignite if this event is cancelled.
 *
 * This event is fired when:
 * <ul>
 * <li>Fire spreads onto another block</li>
 * <li>Lava causes a block to catch fire</li>
 * <li>A flint and steel or fire charge is used on a block</li>
 * <li>A fireball causes a block to catch fire</li>
 * <li>Lightning causes a block to catch fire</li>
 * <li>A dispenser with a flint and steel or fire charge catches a block on fire</li>
 * <li>An explosion causes a block to catch fire (i.e. respawn anchors)</li>
 * <li>A flame arrow is used to ignite an unlit campfire</li>
 * <li>A flint and steel or fire charge is used to ignite an unlit campfire</li>
 * </ul>
 */
public class BlockIgniteEvent extends BlockEvent implements Cancellable {

    private Block sourceBlock;
    private Entity sourceEntity;
    private final Cause cause;
    private boolean cancelled;

    public BlockIgniteEvent(Block block, Block source, Cause cause) {
        super(block);
        this.sourceBlock = source;
        this.cause = cause;
    }

    public BlockIgniteEvent(Block block, Entity source, Cause cause) {
        super(block);
        this.sourceEntity = source;
        this.cause = cause;
    }

    @NotNull
    public Optional<Block> getSourceBlock() {
        return Optional.ofNullable(this.sourceBlock);
    }

    @NotNull
    public Optional<Entity> getSourceEntity() {
        return Optional.ofNullable(this.sourceEntity);
    }

    @NotNull
    public Cause getCause() {
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
     * Represents the source of the fire
     * which ignited this block.
     */
    public enum Cause {

        LAVA,
        FLINT_AND_STEEL,
        FIRE_CHARGE,
        FIREBALL,
        EXPLOSION,
        LIGHTNING,
        DISPENSER,
        END_CRYSTAL,
        FLAME_ARROW,
        FIRE_SPREAD
    }
}

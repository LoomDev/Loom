package org.loomdev.api.entity.passive;

import org.loomdev.api.village.Trader;

/**
 * Represents a trading entity.
 * Includes {@link Villager} and {@link WanderingTrader}
 */
public interface AbstractTrader extends PassiveEntity, Trader {
    // TODO inventory
}

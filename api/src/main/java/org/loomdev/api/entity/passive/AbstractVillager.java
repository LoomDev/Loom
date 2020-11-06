package org.loomdev.api.entity.passive;

import org.loomdev.api.village.Merchant;

/**
 * Represents a trading entity.
 * Includes {@link Villager} and {@link WanderingTrader}
 */
public interface AbstractVillager extends AgeableMob, Merchant {
    // TODO inventory
}

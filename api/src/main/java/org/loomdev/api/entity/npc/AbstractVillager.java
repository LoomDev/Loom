package org.loomdev.api.entity.npc;

import org.loomdev.api.entity.animal.AgeableMob;
import org.loomdev.api.village.Merchant;

/**
 * Represents a trading entity.
 * Includes {@link Villager} and {@link WanderingTrader}.
 */
public interface AbstractVillager extends AgeableMob, Merchant {
    // TODO inventory
}

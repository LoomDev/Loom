package org.loomdev.api.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TranslatableComponent;

/**
 * The different GameMode's of Minecraft
 *
 * <p>Documentation source: https://minecraft.gamepedia.com/Gameplay#Game_modes</p>
 */
public enum GameMode {

    /**
     * In this mode, players have to gather all their materials to build, craft items and tools and gain experience points.
     * There is a health, hunger, and armor bar, an inventory, and also a oxygen bar when underwater.
     * If a player runs out of hearts (health), the player dies and returns to the spawn point.
     * Upon death, the player emits smoke, loses all experience and items, and a death message.
     * Maximum reach is 5 blocks.
     */
    SURVIVAL,

    /**
     * The player has access to an infinite amount of almost all blocks and items available, and can destroy them instantly.
     * Players are invulnerable, unless they fall into the void and do not have health, armor, or hunger, and can fly.
     * The player has access to items not available in Survival mode, e.g. spawn eggs.
     * The player cannot see command blocks if they look through the creative GUI- they need to spawn command blocks with the /give or /setblock commands.
     * Max reach is 6 blocks.
     */
    CREATIVE,

    /**
     * Players can interact with objects such as levers and buttons, and can interact with mobs. However,
     * they can break blocks only with tools having a CanDestroy data tag, and place blocks only if the block
     * they are holding has a CanPlaceOn data tag, making this mode good for adventure maps.
     * Max reach is 5 blocks.
     */
    ADVENTURE,

    /**
     * When in spectator mode, players can clip through blocks, enter the perspective of other entities by
     * left-clicking on them, and are invisible to all players and mobs except for other spectators.
     * The player can't interact with blocks, entities or their inventory.
     * When in third-person mode,they look like a transparent, floating head with no body.
     * The player can use the scroll wheel to adjust the speed at which they are flying, unlike flying in Creative mode.
     */
    SPECTATOR;

    public TranslatableComponent getTranslatableName() {
        return Component.translatable("gameMode." + name().toLowerCase());
    }
}

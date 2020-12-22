package org.loomdev.api.entity.boss;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.bossbar.BossBar;

/**
 * Represents a boss entity.
 * Includes {@link org.loomdev.api.entity.boss.enderdragon.EnderDragon} and {@link Wither}
 */
public interface Boss {
    @NotNull BossBar getBossBar();
}

package org.loomdev.api.entity.boss.enderdragon;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.bossbar.BossBar;
import org.loomdev.api.world.Location;

public interface DragonFight {

    @NotNull BossBar getBossBar();

    @NotNull EnderDragon getDragon();

    Location getEndPortalLocation();

    boolean hasBeenPreviouslyKilled();

    void respawnDragon();

    void resetCrystals();

    void generateEndPortal(boolean withPortals);

    @NotNull SpawnState getSpawnState();

    void setSpawnState(@NotNull SpawnState spawnState);

    enum SpawnState {
        NONE,
        START,
        PREPARING_TO_SUMMON_PILLARS,
        SUMMONING_PILLARS,
        SUMMONING_DRAGON,
        END
    }
}

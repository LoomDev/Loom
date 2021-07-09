package org.loomdev.loom.entity.boss.enderdragon;

import net.minecraft.world.level.dimension.end.DragonRespawnAnimation;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.boss.enderdragon.DragonFight;
import org.loomdev.api.entity.boss.enderdragon.EnderDragon;
import org.loomdev.api.world.Location;
import org.loomdev.loom.bossbar.BossBarImpl;

public class DragonFightImpl implements DragonFight {

    private final EnderDragonImpl enderDragon;
    private final EndDragonFight fight;
    private final BossBarImpl bossBar;

    public DragonFightImpl(EnderDragonImpl enderDragon, EndDragonFight fight) {
        this.enderDragon = enderDragon;
        this.fight = fight;
        this.bossBar = new BossBarImpl(fight.dragonEvent);
    }

    @Override
    @NotNull
    public BossBarImpl getBossBar() {
        return bossBar;
    }

    @Override
    @NotNull
    public EnderDragon getDragon() {
        return enderDragon;
    }

    @Override
    public Location getEndPortalLocation() {
        var bp = fight.portalLocation;
        return new Location(fight.level.getLoomWorld(), bp.getX(), bp.getY(), bp.getZ());
    }

    @Override
    public boolean hasBeenPreviouslyKilled() {
        return fight.hasPreviouslyKilledDragon();
    }

    @Override
    public void respawnDragon() {
        fight.tryRespawn();
    }

    @Override
    public void resetCrystals() {
        fight.resetSpikeCrystals();
    }

    @Override
    public void generateEndPortal(boolean withPortals) {
        fight.spawnExitPortal(withPortals);
    }

    @Override
    @NotNull
    public SpawnState getSpawnState() {
        return fight.respawnStage == null ? SpawnState.NONE : SpawnState.valueOf(fight.respawnStage.name());
    }

    @Override
    public void setSpawnState(@NotNull SpawnState spawnState) {
        fight.setRespawnStage(spawnState == SpawnState.NONE ? null : DragonRespawnAnimation.valueOf(spawnState.name()));
    }
}

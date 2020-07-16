package org.loomdev.loom.entity.boss.dragon;

import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.entity.boss.dragon.EnderDragonSpawnState;
import net.minecraft.util.math.BlockPos;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.boss.dragon.DragonFight;
import org.loomdev.api.entity.boss.dragon.EnderDragon;
import org.loomdev.api.world.Location;

public class DragonFightImpl implements DragonFight {

    private final EnderDragonImpl enderDragon;
    private final EnderDragonFight fight;

    public DragonFightImpl(EnderDragonImpl enderDragon, EnderDragonFight fight) {
        this.enderDragon = enderDragon;
        this.fight = fight;
    }

    @Override
    public EnderDragon getDragon() {
        return this.enderDragon;
    }

    @Override
    public Location getEndPortalLocation() {
        BlockPos bp = this.fight.exitPortalLocation;
        return new Location(null, bp.getX(), bp.getY(), bp.getZ()); // TODO world
    }

    @Override
    public boolean hasBeenPreviouslyKilled() {
        return this.fight.hasPreviouslyKilled();
    }

    @Override
    public void respawnDragon() {
        this.fight.respawnDragon();
    }

    @Override
    public void resetCrystals() {
        this.fight.resetEndCrystals();
    }

    @Override
    public void generateEndPortal(boolean withPortals) {
        this.fight.generateEndPortal(withPortals);
    }

    @Override
    public @NonNull SpawnState getSpawnState() {
        return this.fight.dragonSpawnState == null ? SpawnState.NONE : SpawnState.valueOf(this.fight.dragonSpawnState.name());
    }

    @Override
    public void setSpawnState(@NonNull SpawnState spawnState) {
        this.fight.setSpawnState(spawnState == SpawnState.NONE ? null : EnderDragonSpawnState.valueOf(spawnState.name()));
    }
}

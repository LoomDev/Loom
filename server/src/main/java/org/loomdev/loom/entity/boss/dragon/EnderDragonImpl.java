package org.loomdev.loom.entity.boss.dragon;

import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.bossbar.BossBar;
import org.loomdev.api.entity.ComplexEntityPart;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.boss.dragon.DragonFight;
import org.loomdev.api.entity.boss.dragon.EnderDragon;
import org.loomdev.loom.entity.mob.MobEntityImpl;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class EnderDragonImpl extends MobEntityImpl implements EnderDragon {

    private DragonFightImpl dragonFight;

    public EnderDragonImpl(EnderDragonEntity entity) {
        super(entity);

        if (entity.getFight() != null) {
            this.dragonFight = new DragonFightImpl(this, entity.getFight());
        }
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ENDER_DRAGON;
    }

    @Override
    public @NotNull EnderDragonEntity getMinecraftEntity() {
        return (EnderDragonEntity) super.getMinecraftEntity();
    }

    @Override
    public int getTicksSinceDeath() {
        return getMinecraftEntity().ticksSinceDeath;
    }

    @Override
    public void setTicksSinceDeath(int ticks) {
        getMinecraftEntity().ticksSinceDeath = ticks;
    }

    @Override
    public Optional<DragonFight> getFight() {
        if (this.dragonFight == null && getMinecraftEntity().getFight() != null) {
            this.dragonFight = new DragonFightImpl(this, getMinecraftEntity().getFight());
        }
        return Optional.ofNullable(this.dragonFight);
    }

    @Override
    public @NotNull Phase getPhase() {
        return Phase.getByName(getMinecraftEntity().getPhaseManager().getCurrent().getType().name);
    }

    @Override
    public void setPhase(@NotNull Phase phase) {
        PhaseType<? extends net.minecraft.entity.boss.dragon.phase.Phase> mcPhase = null;
        switch (phase) {
            case HOLDING_PATTERN:
                mcPhase = PhaseType.HOLDING_PATTERN;
                break;
            case STRAFE_PLAYER:
                mcPhase = PhaseType.STRAFE_PLAYER;
                break;
            case LANDING_APPROACH:
                mcPhase = PhaseType.LANDING_APPROACH;
                break;
            case LANDING:
                mcPhase = PhaseType.LANDING;
                break;
            case TAKEOFF:
                mcPhase = PhaseType.TAKEOFF;
                break;
            case SITTING_FLAMING:
                mcPhase = PhaseType.SITTING_FLAMING;
                break;
            case SITTING_SCANNING:
                mcPhase = PhaseType.SITTING_SCANNING;
                break;
            case SITTING_ATTACKING:
                mcPhase = PhaseType.SITTING_ATTACKING;
                break;
            case CHARGING_PLAYER:
                mcPhase = PhaseType.CHARGING_PLAYER;
                break;
            case DYING:
                mcPhase = PhaseType.DYING;
                break;
            case HOVER:
                mcPhase = PhaseType.HOVER;
                break;
        }
        getMinecraftEntity().getPhaseManager().setPhase(mcPhase);
    }

    @Override
    public @NotNull Set<ComplexEntityPart> getParts() {
        return Arrays.stream(getMinecraftEntity().getBodyParts())
                .map(part -> (EnderDragonPartImpl) part.getLoomEntity())
                .collect(Collectors.toSet());
    }

    @Override
    public @NotNull BossBar getBossBar() {
        return this.dragonFight.getBossBar();
    }
}

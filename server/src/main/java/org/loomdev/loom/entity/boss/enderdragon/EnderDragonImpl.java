package org.loomdev.loom.entity.boss.enderdragon;

import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.bossbar.BossBar;
import org.loomdev.api.entity.ComplexEntityPart;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.boss.enderdragon.DragonFight;
import org.loomdev.api.entity.boss.enderdragon.EnderDragon;
import org.loomdev.loom.entity.monster.MobEntityImpl;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class EnderDragonImpl extends MobEntityImpl implements EnderDragon {

    private DragonFightImpl dragonFight;

    public EnderDragonImpl(net.minecraft.world.entity.boss.enderdragon.EnderDragon entity) {
        super(entity);

        if (entity.getDragonFight() != null) {
            this.dragonFight = new DragonFightImpl(this, entity.getDragonFight());
        }
    }

    @Override
    @NotNull
    public EntityType<EnderDragon> getType() {
        return EntityType.ENDER_DRAGON;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.boss.enderdragon.EnderDragon getMinecraftEntity() {
        return (net.minecraft.world.entity.boss.enderdragon.EnderDragon) super.getMinecraftEntity();
    }

    @Override
    public int getTicksSinceDeath() {
        return getMinecraftEntity().tickCount;
    }

    @Override
    public void setTicksSinceDeath(int ticks) {
        getMinecraftEntity().tickCount = ticks;
    }

    @Override
    public Optional<DragonFight> getFight() {
        if (this.dragonFight == null && getMinecraftEntity().getDragonFight() != null) {
            this.dragonFight = new DragonFightImpl(this, getMinecraftEntity().getDragonFight());
        }
        return Optional.ofNullable(this.dragonFight);
    }

    @Override
    @NotNull
    public Phase getPhase() {
        return Phase.getByName(getMinecraftEntity().getPhaseManager().getCurrentPhase().getPhase().name);
    }

    @Override
    public void setPhase(@NotNull Phase phase) {
        EnderDragonPhase<?> mcPhase = null;
        switch (phase) {
            case HOLDING_PATTERN:
                mcPhase = EnderDragonPhase.HOLDING_PATTERN;
                break;
            case STRAFE_PLAYER:
                mcPhase = EnderDragonPhase.STRAFE_PLAYER;
                break;
            case LANDING_APPROACH:
                mcPhase = EnderDragonPhase.LANDING_APPROACH;
                break;
            case LANDING:
                mcPhase = EnderDragonPhase.LANDING;
                break;
            case TAKEOFF:
                mcPhase = EnderDragonPhase.TAKEOFF;
                break;
            case SITTING_FLAMING:
                mcPhase = EnderDragonPhase.SITTING_FLAMING;
                break;
            case SITTING_SCANNING:
                mcPhase = EnderDragonPhase.SITTING_SCANNING;
                break;
            case SITTING_ATTACKING:
                mcPhase = EnderDragonPhase.SITTING_ATTACKING;
                break;
            case CHARGING_PLAYER:
                mcPhase = EnderDragonPhase.CHARGING_PLAYER;
                break;
            case DYING:
                mcPhase = EnderDragonPhase.DYING;
                break;
            case HOVER:
                mcPhase = EnderDragonPhase.HOVERING;
                break;
        }
        getMinecraftEntity().getPhaseManager().setPhase(mcPhase);
    }

    @Override
    @NotNull
    public Set<ComplexEntityPart> getParts() {
        return Arrays.stream(getMinecraftEntity().getSubEntities())
                .map(part -> (EnderDragonPartImpl) part.getLoomEntity())
                .collect(Collectors.toSet());
    }

    @Override
    @NotNull
    public BossBar getBossBar() {
        return this.dragonFight.getBossBar();
    }
}

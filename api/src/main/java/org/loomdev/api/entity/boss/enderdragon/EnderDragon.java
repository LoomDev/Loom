package org.loomdev.api.entity.boss.enderdragon;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.ComplexEntity;
import org.loomdev.api.entity.ComplexEntityPart;
import org.loomdev.api.entity.boss.Boss;
import org.loomdev.api.entity.monster.MobEntity;
import org.loomdev.api.entity.monster.Enemy;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public interface EnderDragon extends MobEntity, Enemy, ComplexEntity, Boss {

    int getTicksSinceDeath();

    void setTicksSinceDeath(int ticks);

    Optional<DragonFight> getFight();

    @NotNull Phase getPhase();

    void setPhase(@NotNull Phase phase);

    default @NotNull Set<EnderDragonPart> getDragonParts() {
        return getParts().stream()
                .map(cep -> (EnderDragonPart)cep)
                .collect(Collectors.toSet());
    }

    @Override
    @NotNull Set<ComplexEntityPart> getParts();

    enum Phase {
        HOLDING_PATTERN("HoldingPattern"),
        STRAFE_PLAYER("StrafePlayer"),
        LANDING_APPROACH("LandingApproach"),
        LANDING("Landing"),
        TAKEOFF("Takeoff"),
        SITTING_FLAMING("SittingFlaming"),
        SITTING_SCANNING("SittingScanning"),
        SITTING_ATTACKING("SittingAttacking"),
        CHARGING_PLAYER("ChargingPlayer"),
        DYING("Dying"),
        HOVER("Hover");

        private final static Map<String, Phase> mapByName = new HashMap<>();
        private final String name;

        Phase(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static Phase getByName(String name) {
            return mapByName.get(name);
        }

        static {
            for(Phase phase : values()) {
                mapByName.put(phase.getName(), phase);
            }
        }
    }
}

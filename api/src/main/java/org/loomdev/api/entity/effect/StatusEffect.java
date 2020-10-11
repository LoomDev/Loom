package org.loomdev.api.entity.effect;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StatusEffect {

    private final Type type;
    private final int duration;
    private final int amplifier;
    private final boolean ambient;
    private final boolean showParticles;
    private final boolean showIcon;

    private StatusEffect(@NotNull Type type, int duration, int amplifier, boolean ambient, boolean showParticles, boolean showIcon) {
        this.type = type;
        this.duration = duration;
        this.amplifier = amplifier;
        this.ambient = ambient;
        this.showParticles = showParticles;
        this.showIcon = showIcon;
    }

    public StatusEffect of(@NotNull StatusEffect effect) {
        return new StatusEffect(
                effect.type,
                effect.duration,
                effect.amplifier,
                effect.ambient,
                effect.showParticles,
                effect.showIcon
        );
    }

    public @NotNull Type getType() {
        return type;
    }

    public int getDuration() {
        return duration;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public boolean isAmbient() {
        return ambient;
    }

    public boolean isShowParticles() {
        return showParticles;
    }

    public boolean isShowIcon() {
        return showIcon;
    }

    public void apply(@NotNull LivingEntity entity) {
        entity.addStatusEffect(this);
    }

    public static Builder builder(@NotNull Type type) {
        return new Builder(type);
    }

    public static final class Builder {

        private final @NotNull Type type;
        private int duration = 30;
        private int amplifier = 1;
        private boolean ambient = false;
        private boolean showParticles = true;
        private boolean showIcon = true;

        public Builder(@NotNull Type type) {
            this.type = type;
        }

        public Builder duration(int duration) {
            this.duration = duration;
            return this;
        }

        public Builder amplifier(int amplifier) {
            this.amplifier = amplifier;
            return this;
        }

        public Builder ambient(boolean ambient) {
            this.ambient = ambient;
            return this;
        }

        public Builder showParticles(boolean showParticles) {
            this.showParticles = showParticles;
            return this;
        }

        public Builder showIcon(boolean showIcon) {
            this.showIcon = showIcon;
            return this;
        }

        public StatusEffect build() {
            return new StatusEffect(
                    this.type,
                    this.duration,
                    this.amplifier,
                    this.ambient,
                    this.showParticles,
                    this.showIcon
            );
        }

        public StatusEffect apply(@NotNull LivingEntity entity) {
            StatusEffect statusEffect = build();
            entity.addStatusEffect(statusEffect);
            return statusEffect;
        }
    }

    /**
     * Status effect types that can be used in a status effect.
     */
    public enum Type {
        SPEED(1),
        SLOWNESS(2),
        HASTE(3),
        MINING_FATIGUE(4),
        STRENGTH(5),
        INSTANT_HEALTH(6),
        INSTANT_DAMAGE(7),
        JUMP_BOOST(8),
        NAUSEA(9),
        REGENERATION(10),
        RESISTANCE(11),
        FIRE_RESISTANCE(12),
        WATER_BREATHING(13),
        INVISIBILITY(14),
        BLINDNESS(15),
        NIGHT_VISION(16),
        HUNGER(17),
        WEAKNESS(18),
        POISON(19),
        WITHER(20),
        HEALTH_BOOST(21),
        ABSORPTION(22),
        SATURATION(23),
        GLOWING(24),
        LEVITATION(25),
        LUCK(26),
        UNLUCK(27),
        SLOW_FALLING(28),
        CONDUIT_POWER(29),
        DOLPHINS_GRACE(30),
        BAD_OMEN(31),
        HERO_OF_THE_VILLAGE(32);

        static Map<Integer, Type> lookupByRawId = new HashMap<>();
        private final int rawId;

        Type(int rawId) {
            this.rawId = rawId;
        }

        public int rawId() {
            return rawId;
        }

        public static @Nullable Type getByRawId(int rawId) {
            return lookupByRawId.get(rawId);
        }

        static {
            for (Type type : values()) {
                Type.lookupByRawId.put(type.rawId, type);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusEffect that = (StatusEffect) o;
        return duration == that.duration &&
                amplifier == that.amplifier &&
                ambient == that.ambient &&
                showParticles == that.showParticles &&
                showIcon == that.showIcon &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, duration, amplifier, ambient, showParticles, showIcon);
    }

    @Override
    public String toString() {
        return "StatusEffect{" +
                "type=" + type +
                ", duration=" + duration +
                ", amplifier=" + amplifier +
                ", ambient=" + ambient +
                ", showParticles=" + showParticles +
                ", showIcon=" + showIcon +
                '}';
    }
}

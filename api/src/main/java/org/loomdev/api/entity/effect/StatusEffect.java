package org.loomdev.api.entity.effect;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StatusEffect {

    private final StatusEffectType type;
    private final int duration;
    private final int amplifier;
    private final boolean ambient;
    private final boolean showParticles;
    private final boolean showIcon;

    private StatusEffect(@NotNull StatusEffectType type, int duration, int amplifier, boolean ambient, boolean showParticles, boolean showIcon) {
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

    public @NotNull StatusEffectType getType() {
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

    public static Builder builder(@NotNull StatusEffectType type) {
        return new Builder(type);
    }

    public static final class Builder {

        private final @NotNull StatusEffectType type;
        private int duration = 30;
        private int amplifier = 1;
        private boolean ambient = false;
        private boolean showParticles = true;
        private boolean showIcon = true;

        public Builder(@NotNull StatusEffectType type) {
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

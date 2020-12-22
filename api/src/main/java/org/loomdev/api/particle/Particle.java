package org.loomdev.api.particle;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.particle.data.ParticleData;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Represents a particle.
 */
public class Particle {

    private final @NotNull ParticleType type;
    private final @Nullable ParticleData data;
    private final int amount;
    private final double offsetX, offsetY, offsetZ;
    private final int extraData;

    private Particle(@NotNull ParticleType type, @Nullable ParticleData data, int amount, double offsetX, double offsetY, double offsetZ, int extraData) {
        this.type = type;
        this.data = data;
        this.amount = amount;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.extraData = extraData;
    }

    public @NotNull ParticleType getType() {
        return type;
    }

    public @NotNull Optional<ParticleData> getData() {
        return Optional.ofNullable(data);
    }

    public int getAmount() {
        return amount;
    }

    public double getOffsetX() {
        return offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public double getOffsetZ() {
        return offsetZ;
    }

    public int getExtraData() {
        return extraData;
    }

    public static Builder builder(@NotNull ParticleType type) {
        return new Builder(type);
    }

    public static final class Builder {

        private final @NotNull ParticleType type;
        private int amount = 1;
        private double offsetX = 0;
        private double offsetY = 0;
        private double offsetZ = 0;
        private @Nullable ParticleData data;
        private int extraData = 0;

        public Builder(@NotNull ParticleType type) {
            this.type = type;
        }

        public Builder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder offset(double offsetX, double offsetY, double offsetZ) {
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            this.offsetZ = offsetZ;
            return this;
        }

        public Builder data(@NotNull ParticleData data) {
            this.data = data;
            return this;
        }

        public Builder extraData(int extraData) {
            this.extraData = extraData;
            return this;
        }

        public Particle build() {
            return new Particle(
                    this.type,
                    this.data,
                    this.amount,
                    this.offsetX,
                    this.offsetY,
                    this.offsetZ,
                    this.extraData
            );
        }
    }
}

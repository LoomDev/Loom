package org.loomdev.api.sound;

import org.jetbrains.annotations.NotNull;

public class Sound {

    private final @NotNull SoundEvent effect;
    private final @NotNull SoundCategory category;
    private final float volume, pitch;

    private Sound(@NotNull SoundEvent effect, @NotNull SoundCategory category, float volume, float pitch) {
        this.effect = effect;
        this.category = category;
        this.volume = volume;
        this.pitch = pitch;
    }

    public @NotNull SoundEvent getSoundEffect() {
        return effect;
    }

    public SoundCategory getSoundCategory() {
        return category;
    }

    public float getVolume() {
        return volume;
    }

    public float getPitch() {
        return pitch;
    }

    public static Builder builder(@NotNull SoundEvent type) {
        return new Builder(type);
    }

    public static final class Builder {
        private final @NotNull SoundEvent type;
        private @NotNull SoundCategory category = SoundCategory.MASTER;
        private float volume = 1.0f;
        private float pitch = 1.0f;

        public Builder(@NotNull SoundEvent type) {
            this.type = type;
        }

        public Builder category(@NotNull SoundCategory category) {
            this.category = category;
            return this;
        }

        public Builder volume(float volume) {
            this.volume = volume;
            return this;
        }

        public Builder pitch(float pitch) {
            this.pitch = pitch;
            return this;
        }

        public Sound build() {
            return new Sound(
                    type,
                    category,
                    volume,
                    pitch
            );
        }
    }
}
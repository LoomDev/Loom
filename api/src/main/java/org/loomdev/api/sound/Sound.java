package org.loomdev.api.sound;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Sound {

    private final @NotNull SoundEvent type;
    private final @NotNull Category category;
    private final float volume, pitch;

    private Sound(@NotNull SoundEvent type, @NotNull Category category, float volume, float pitch) {
        this.type = type;
        this.category = category;
        this.volume = volume;
        this.pitch = pitch;
    }

    public @NotNull SoundEvent getType() {
        return type;
    }

    public @NotNull Category getCategory() {
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
        private @NotNull Category category = Category.MASTER;
        private float volume = 1.0f;
        private float pitch = 1.0f;

        public Builder(@NotNull SoundEvent type) {
            this.type = type;
        }

        public Builder category(@NotNull Category category) {
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

    public enum Category {

        MASTER("master"),
        MUSIC("music"),
        RECORDS("record"),
        WEATHER("weather"),
        BLOCKS("block"),
        HOSTILE("hostile"),
        NEUTRAL("neutral"),
        PLAYERS("player"),
        AMBIENT("ambient"),
        VOICE("voice");

        private static final Map<String, Category> mapByName = Arrays.stream(values()).collect(Collectors.toMap(Category::getName, Function.identity()));
        private final String name;

        Category(String s) {
            this.name = s;
        }

        public String getName() {
            return this.name;
        }

        public static Category getByName(String name) {
            return mapByName.get(name);
        }
    }
}
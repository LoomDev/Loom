package org.loomdev.api.sound;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum SoundCategories {

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

    private static final Map<String, SoundCategories> mapByName = Arrays.stream(values()).collect(Collectors.toMap(SoundCategories::getName, Function.identity()));
    private final String name;

    SoundCategories(String s) {
        this.name = s;
    }

    public String getName() {
        return this.name;
    }

    public static SoundCategories getByName(String name) {
        return mapByName.get(name);
    }
}

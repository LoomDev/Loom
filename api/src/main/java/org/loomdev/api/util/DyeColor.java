package org.loomdev.api.util;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

// TODO create test to check these values agains the minecraft DyeColor enum
public enum DyeColor {

    WHITE(0, "white", 16383998, 15790320, 16777215),
    ORANGE(1, "orange", 16351261, 15435844, 16738335),
    MAGENTA(2, "magenta", 13061821, 12801229, 16711935),
    LIGHT_BLUE(3, "light_blue", 3847130, 6719955, 10141901),
    YELLOW(4, "yellow", 16701501, 14602026, 16776960),
    LIME(5, "lime", 8439583, 4312372, 12582656),
    PINK(6, "pink", 15961002, 14188952, 16738740),
    GRAY(7, "gray", 4673362, 4408131, 8421504),
    LIGHT_GRAY(8, "light_gray", 10329495, 11250603, 13882323),
    CYAN(9, "cyan", 1481884,  2651799, 65535),
    PURPLE(10, "purple", 8991416, 8073150, 10494192),
    BLUE(11, "blue", 3949738, 2437522, 255),
    BROWN(12, "brown", 8606770, 5320730, 9127187),
    GREEN(13, "green", 6192150, 3887386, 65280),
    RED(14, "red", 11546150, 11743532, 16711680),
    BLACK(15, "black", 1908001, 1973019, 0);

    private static final Map<String, DyeColor> NAME_COLOR_MAP = Arrays.stream(values())
            .collect(Collectors.toMap(DyeColor::getName, x -> x));
    private static final Map<Integer, DyeColor> ID_COLOR_MAP = Arrays.stream(values())
            .collect(Collectors.toMap(DyeColor::getId, x -> x));

    private final int id;
    private final String name;
    private final int color;
    private final int fireworkColor;
    private final int signColor;

    DyeColor(int id, String name, int color, int fireworkColor, int signColor) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.fireworkColor = fireworkColor;
        this.signColor = signColor;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Color asColor() {
        return Color.fromRgb(this.color);
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getColor() {
        return this.color;
    }

    public int getFireworkColor() {
        return this.fireworkColor;
    }

    public int getSignColor() {
        return this.signColor;
    }

    public static DyeColor getById(int id) {
        return ID_COLOR_MAP.get(id);
    }

    public static DyeColor getByName(String name) {
        return NAME_COLOR_MAP.get(name.toLowerCase());
    }
}

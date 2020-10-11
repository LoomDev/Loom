package org.loomdev.api.util;

import net.kyori.adventure.text.format.TextColor;

public class ChatColor {

    public static final char COLOR_CHAR = '\u00A7';
    public static final String ALL_CODES = "0123456789AaBbCcDdEeFfKkLlMmNnOoRrXx";

    public static TextColor DARK_RED = TextColor.fromHexString("#AA0000");
    public static TextColor RED = TextColor.fromHexString("#FF5555");
    public static TextColor GOLD = TextColor.fromHexString("#FFAA00");
    public static TextColor YELLOW = TextColor.fromHexString("#FFFF55");
    public static TextColor DARK_GREEN = TextColor.fromHexString("#00AA00");
    public static TextColor GREEN = TextColor.fromHexString("#55FF55");
    public static TextColor AQUA = TextColor.fromHexString("#55FFFF");
    public static TextColor DARK_AQUA = TextColor.fromHexString("#00AAAA");
    public static TextColor DARK_BLUE = TextColor.fromHexString("#0000AA");
    public static TextColor BLUE = TextColor.fromHexString("#5555FF");
    public static TextColor LIGHT_PURPLE = TextColor.fromHexString("#FF55FF");
    public static TextColor DARK_PURPLE = TextColor.fromHexString("#AA00AA");
    public static TextColor WHITE = TextColor.fromHexString("#FFFFFF");
    public static TextColor GRAY = TextColor.fromHexString("#AAAAAA");
    public static TextColor DARK_GRAY = TextColor.fromHexString("#555555");
    public static TextColor BLACK = TextColor.fromHexString("#000000");

    public static String translate(char colorChar, String text) {
        char[] b = text.toCharArray();

        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == colorChar && ALL_CODES.indexOf(b[i + 1]) > -1) {
                b[i] = ChatColor.COLOR_CHAR;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }
        return new String(b);
    }
}

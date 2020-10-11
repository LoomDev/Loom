package org.loomdev.api.util;

import com.google.common.base.Preconditions;
import net.kyori.adventure.text.format.TextColor;

public class Color {
    private static final int BIT_MASK = 0xff;

    public static final Color RED = new Color(Byte.MAX_VALUE, 0, 0);
    public static final Color GREEN = new Color(0, Byte.MAX_VALUE, 0);
    public static final Color BLUE = new Color(0, 0, Byte.MAX_VALUE);

    public static Color fromRgb(int r, int g, int b) {
        return new Color(r, g, b);
    }

    public static Color fromRgb(int rgb) {
        return fromRgb(rgb >> 16 & BIT_MASK, rgb >> 8 & BIT_MASK, rgb >> 0 & BIT_MASK);
    }

    private byte red;
    private byte green;
    private byte blue;

    private Color(int red, int green, int blue) {
        Preconditions.checkArgument(red >= 0, "Red is not between 0-255: ", red);
        Preconditions.checkArgument(green >= 0, "Green is not between 0-255: ", green);
        Preconditions.checkArgument(blue >= 0, "Blue is not between 0-255: ", blue);
        this.red = (byte) red;
        this.green = (byte) green;
        this.blue = (byte) blue;
    }

    public int getRed() {
        return BIT_MASK & red;
    }

    public void setRed(int red) {
        this.red = (byte) red;
    }

    public int getGreen() {
        return BIT_MASK & green;
    }

    public void setGreen(int green) {
        this.green = (byte) green;
    }

    public int getBlue() {
        return BIT_MASK & blue;
    }

    public void setBlue(int blue) {
        this.blue = (byte) blue;
    }

    public int asRgb() {
        return getRed() << 16 | getGreen() << 8 | getBlue() << 0;
    }

    public String asHex() {
        return String.format("#%06X", asRgb());
    }

    public TextColor asTextColor() {
        return TextColor.of(getRed(), getGreen(), getBlue());
    }

    @Override
    public String toString() {
        return "Color{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }

}

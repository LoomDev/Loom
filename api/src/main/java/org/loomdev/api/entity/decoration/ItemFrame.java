package org.loomdev.api.entity.decoration;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.item.ItemStack;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public interface ItemFrame extends HangingEntity {

    @NotNull
    ItemStack getHeldItem();

    void setHeldItem(@NotNull ItemStack item);

    boolean isVisible();

    void setVisible(boolean flag);

    boolean isFixed();

    void setFixed(boolean flag);

    float getItemDropChance();

    void setItemDropChance(float dropChance);

    @NotNull Rotation getRotation();

    void setRotation(@NotNull Rotation rotation);

    enum Rotation {
        R0(0),
        R45(45),
        R90(90),
        R135(135),
        R180(180),
        R225(225),
        R270(270),
        R315(315);

        private static final Map<Integer, Rotation> INT_ROT_MAP = Arrays.stream(values()).collect(Collectors.toMap(Enum::ordinal, e -> e));
        private final int degrees;

        Rotation(int degrees) {
            this.degrees = degrees;
        }

        public int getDegrees() {
            return degrees;
        }

        public static Rotation getByInt(int value) {
            return INT_ROT_MAP.get(value % 8);
        }
    }
}

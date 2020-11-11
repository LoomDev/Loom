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

    boolean isFixed();

    void setFixed(boolean flag);

    float getItemDropChance();

    void setItemDropChance(float dropChance);

    @NotNull
    Rotation getRotation();

    void setRotation(@NotNull Rotation rotation);

    enum Rotation {
        DEGREES_0(0),
        DEGREES_45(45),
        DEGREES_90(90),
        DEGREES_135(135),
        DEGREES_180(180),
        DEGREES_225(225),
        DEGREES_270(270),
        DEGREES_315(315);

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

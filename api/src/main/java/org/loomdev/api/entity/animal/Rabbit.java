package org.loomdev.api.entity.animal;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public interface Rabbit extends Animal {

    /**
     * Get the {@link Variant} of the Mooshroom.
     *
     * @return The {@link Variant} of the Mooshroom.
     */
    @NotNull Variant getVariant();

    /**
     * Set the {@link Variant} of the Mooshroom.
     *
     * @param variant The new {@link Variant} of the Mooshroom.
     */
    void setVariant(@NotNull Variant variant);

    enum Variant {
        WHITE(1),
        BLACK(2),
        BLACK_AND_WHITE(3),
        GOLD(4),
        /**
         * Mixture between gray and brown with a white belly?
         */
        SALT_AND_PEPPER(5),
        BROWN(6),
        KILLER_BUNNY(99);

        private static final Map<Integer, Variant> ID_VARIANT_MAP = Arrays.stream(values())
                .collect(Collectors.toMap(Variant::getMcId, x -> x));
        int mcId;

        Variant(int mcId) {
            this.mcId = mcId;
        }

        public int getMcId() {
            return this.mcId;
        }

        public static Variant getByMcId(int mcId) {
            return ID_VARIANT_MAP.getOrDefault(mcId, Variant.WHITE);
        }

    }

}

package org.loomdev.api.entity.animal;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a Panda entity.
 */
public interface Panda extends Animal {

    /**
     * Gets the main {@link Gene} of the Panda.
     *
     * @return The main {@link Gene}
     */
    @NotNull Gene getMainGene();

    /**
     * Sets the main {@link Gene} of the Panda.
     *
     * @param gene The new main {@link Gene}
     */
    void setMainGene(@NotNull Gene gene);

    /**
     * Gets the hidden {@link Gene} of the Panda.
     *
     * @return The hidden {@link Gene}
     */
    @NotNull Gene getHiddenGene();

    /**
     * Sets the hidden {@link Gene} of the Panda.
     *
     * @param gene The new hidden {@link Gene}
     */
    void setHiddenGene(@NotNull Gene gene);

    /**
     * Represents a Gene of a {@link Panda}.
     */
    enum Gene {
        NORMAL(false),
        LAZY(false),
        WORRIED(false),
        PLAYFUL(false),
        BROWN(true),
        WEAK(true),
        AGGRESSIVE(false);

        private final boolean recessive;

        Gene(boolean recessive) {
            this.recessive = recessive;
        }

        /**
         * Gets whether the gene is recessive.
         *
         * @return True if the gene is recessive, otherwise False.
         */
        public boolean isRecessive() {
            return recessive;
        }

        /**
         * Gets the product of two genes.
         *
         * @param gene1 The first gene.
         * @param gene2 The second gene.
         * @return The product of the gene combination.
         */
        // TODO add an automated test to check if the outcome is still the same as the mc code.
        public static Gene getProduct(Gene gene1, Gene gene2) {
            return gene1.isRecessive() ? (gene1 == gene2 ? gene1 : Gene.NORMAL) : gene1;
        }
    }

}

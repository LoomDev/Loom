package org.loomdev.api.block.enums;

/**
 * Represents a note's octave.
 */
public enum Octave {

    OCTAVE_1(1),
    OCTAVE_2(2);

    private final int number;

    Octave(int number) {
        this.number = number;
    }

    /**
     * Gets the octave's number
     *
     * @return The number.
     */
    public int getNumber() {
        return number;
    }
}

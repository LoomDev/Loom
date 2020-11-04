package org.loomdev.api.block.enums;

public enum Octave {

    OCTAVE_1(1),
    OCTAVE_2(2);

    private final int number;

    Octave(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}

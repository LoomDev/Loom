package org.loomdev.api.block.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum Note {

    F_SHARP0(Octave.OCTAVE_1, "F#0", 0),
    G1(Octave.OCTAVE_1, "G1", 1),
    G_SHARP1(Octave.OCTAVE_1, "G#1", 2),
    A1(Octave.OCTAVE_1, "A1", 3),
    A_SHARP1(Octave.OCTAVE_1, "A#1", 4),
    B1(Octave.OCTAVE_1, "B1", 5),
    C1(Octave.OCTAVE_1, "C1", 6),
    C_SHARP1(Octave.OCTAVE_1, "C#1", 7),
    D1(Octave.OCTAVE_1, "D1", 8),
    D_SHARP1(Octave.OCTAVE_1, "D#1", 9),
    E1(Octave.OCTAVE_1, "E1", 10),
    F1(Octave.OCTAVE_1, "F1", 11),
    F_SHARP1(Octave.OCTAVE_1, "F#1", 12),

    G2(Octave.OCTAVE_2, "G2", 13),
    G_SHARP2(Octave.OCTAVE_2, "G#2", 14),
    A2(Octave.OCTAVE_2, "A2", 15),
    A_SHARP2(Octave.OCTAVE_2, "A#2", 16),
    B2(Octave.OCTAVE_2, "B2", 17),
    C2(Octave.OCTAVE_2, "C2", 18),
    C_SHARP2(Octave.OCTAVE_2, "C#2", 19),
    D2(Octave.OCTAVE_2, "D2", 20),
    D_SHARP2(Octave.OCTAVE_2, "D#2", 21),
    E2(Octave.OCTAVE_2, "E2", 22),
    F2(Octave.OCTAVE_2, "F2", 23),
    F_SHARP2(Octave.OCTAVE_2, "F#2", 24);

    private static final Map<String, Note> NAME_NOTE_MAP = Arrays.stream(values()).collect(Collectors.toMap(Note::getName, note -> note));
    private static final Map<Integer, Note> USES_NOTE_MAP = Arrays.stream(values()).collect(Collectors.toMap(Note::getIndex, note -> note));
    private final Octave octave;
    private final String name;
    private final int index;

    Note(Octave octave, String name, int index) {
        this.octave = octave;
        this.name = name;
        this.index = index;
    }

    public Octave getOctave() {
        return octave;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public static Note getByName(String name) {
        return NAME_NOTE_MAP.get(name);
    }

    public static Note getByUses(int uses) {
        return USES_NOTE_MAP.get(uses);
    }
}

package org.loomdev.api.block.enums;

import org.loomdev.api.sound.Sound;

public enum Instrument {

    HARP(Sound.Type.BLOCK_NOTE_BLOCK_HARP),
    BASEDRUM(Sound.Type.BLOCK_NOTE_BLOCK_BASEDRUM),
    SNARE(Sound.Type.BLOCK_NOTE_BLOCK_SNARE),
    HAT(Sound.Type.BLOCK_NOTE_BLOCK_HAT),
    BASS(Sound.Type.BLOCK_NOTE_BLOCK_BASS),
    FLUTE(Sound.Type.BLOCK_NOTE_BLOCK_FLUTE),
    BELL(Sound.Type.BLOCK_NOTE_BLOCK_BELL),
    GUITAR(Sound.Type.BLOCK_NOTE_BLOCK_GUITAR),
    CHIME(Sound.Type.BLOCK_NOTE_BLOCK_CHIME),
    XYLOPHONE(Sound.Type.BLOCK_NOTE_BLOCK_XYLOPHONE),
    IRON_XYLOPHONE(Sound.Type.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE),
    COW_BELL(Sound.Type.BLOCK_NOTE_BLOCK_COW_BELL),
    DIDGERIDOO(Sound.Type.BLOCK_NOTE_BLOCK_DIDGERIDOO),
    BIT(Sound.Type.BLOCK_NOTE_BLOCK_BIT),
    BANJO(Sound.Type.BLOCK_NOTE_BLOCK_BANJO),
    PLING(Sound.Type.BLOCK_NOTE_BLOCK_PLING);

    private final Sound.Type sound;

    Instrument(Sound.Type sound) {
        this.sound = sound;
    }

    public Sound.Type getSoundT() {
        return sound;
    }

    public static Instrument getByName(String name) {
        return valueOf(name.toUpperCase());
    }
}

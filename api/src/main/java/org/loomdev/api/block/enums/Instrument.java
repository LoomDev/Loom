package org.loomdev.api.block.enums;

import org.loomdev.api.sound.SoundEvent;

/**
 * Represents a note block instrument.
 */
public enum Instrument {

    HARP(SoundEvent.BLOCK_NOTE_BLOCK_HARP),
    BASEDRUM(SoundEvent.BLOCK_NOTE_BLOCK_BASEDRUM),
    SNARE(SoundEvent.BLOCK_NOTE_BLOCK_SNARE),
    HAT(SoundEvent.BLOCK_NOTE_BLOCK_HAT),
    BASS(SoundEvent.BLOCK_NOTE_BLOCK_BASS),
    FLUTE(SoundEvent.BLOCK_NOTE_BLOCK_FLUTE),
    BELL(SoundEvent.BLOCK_NOTE_BLOCK_BELL),
    GUITAR(SoundEvent.BLOCK_NOTE_BLOCK_GUITAR),
    CHIME(SoundEvent.BLOCK_NOTE_BLOCK_CHIME),
    XYLOPHONE(SoundEvent.BLOCK_NOTE_BLOCK_XYLOPHONE),
    IRON_XYLOPHONE(SoundEvent.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE),
    COW_BELL(SoundEvent.BLOCK_NOTE_BLOCK_COW_BELL),
    DIDGERIDOO(SoundEvent.BLOCK_NOTE_BLOCK_DIDGERIDOO),
    BIT(SoundEvent.BLOCK_NOTE_BLOCK_BIT),
    BANJO(SoundEvent.BLOCK_NOTE_BLOCK_BANJO),
    PLING(SoundEvent.BLOCK_NOTE_BLOCK_PLING);

    private final SoundEvent sound;

    Instrument(SoundEvent sound) {
        this.sound = sound;
    }

    /**
     * Gets the sound event for the instrument.
     *
     * @return The sound event.
     */
    public SoundEvent getSound() {
        return sound;
    }

    /**
     * Gets an instrument from its name.
     *
     * @param name The name.
     * @return The instrument.
     */
    public static Instrument getByName(String name) {
        return valueOf(name.toUpperCase());
    }
}

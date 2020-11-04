package org.loomdev.api.event.block.note;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.block.enums.Instrument;
import org.loomdev.api.block.enums.Note;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.block.BlockEvent;

/**
 * Fired when a note block is triggered and plays its set instrument and note.
 * The note will not play if this event is cancelled.
 */
public class NoteBlockPlayEvent extends BlockEvent implements Cancellable {

    private Instrument instrument;
    private Note note;
    private float pitch;
    private boolean cancelled;

    public NoteBlockPlayEvent(Block block, Instrument instrument, Note note, float pitch) {
        super(block);
        this.instrument = instrument;
        this.note = note;
        this.pitch = pitch;
    }

    @NotNull
    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    @NotNull
    public Note getNote() {
        return note;
    }

    public void setNote(@NotNull Note note) {
        this.note = note;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}

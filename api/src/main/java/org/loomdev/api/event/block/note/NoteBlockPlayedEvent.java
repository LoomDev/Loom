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
public class NoteBlockPlayedEvent extends BlockEvent implements Cancellable {

    private Instrument instrument;
    private Note note;
    private float pitch;
    private boolean cancelled;

    public NoteBlockPlayedEvent(@NotNull Block block, @NotNull Instrument instrument, @NotNull Note note, float pitch) {
        super(block);
        this.instrument = instrument;
        this.note = note;
        this.pitch = pitch;
    }

    public @NotNull Instrument getInstrument() {
        return this.instrument;
    }

    public void setInstrument(@NotNull Instrument instrument) {
        this.instrument = instrument;
    }

    public @NotNull Note getNote() {
        return this.note;
    }

    public void setNote(@NotNull Note note) {
        this.note = note;
    }

    public float getPitch() {
        return this.pitch;
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

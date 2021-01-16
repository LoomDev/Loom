package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.enums.Instrument;
import org.loomdev.api.block.enums.Note;
import org.loomdev.api.event.Cancelable;

public interface NoteBlockEvent extends BlockEvent {

    interface Play extends NoteBlockEvent, Cancelable {

        @NotNull
        Instrument getInstrument();

        void setInstrument(@NotNull Instrument instrument);

        @NotNull
        Note getNote();

        void setNote(@NotNull Note note);

        float getPitch();

        void setPitch(float pitch);
    }
}

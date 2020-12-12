package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.block.enums.Instrument;
import org.loomdev.api.block.enums.Note;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.Event;
import org.loomdev.api.util.Hand;

// TODO pass NoteBlock to these events once we have it implemented
public class NoteBlockEvent extends Event {

    private final BlockPointer pointer;

    public NoteBlockEvent(BlockPointer pointer) {
        this.pointer = pointer;
    }

    @NotNull
    public BlockPointer getPointer() {
        return pointer;
    }

    public static class Play extends NoteBlockEvent {

        private Instrument instrument;
        private Note note;
        private float pitch;

        public Play(BlockPointer pointer, Instrument instrument, Note note, float pitch) {
            super(pointer);
            this.instrument = instrument;
            this.note = note;
            this.pitch = pitch;
        }

        @NotNull
        public Instrument getInstrument() {
            return instrument;
        }

        public void setInstrument(@NotNull Instrument instrument) {
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
        public boolean isCancelable() {
            return true;
        }
    }

    public static class ChangeNote extends NoteBlockEvent {

        private final Player player;
        private final Hand hand;

        public ChangeNote(BlockPointer pointer, Player player, Hand hand) {
            super(pointer);
            this.player = player;
            this.hand = hand;
        }

        @NotNull
        public Player getPlayer() {
            return player;
        }

        @NotNull
        public Hand getHand() {
            return hand;
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }
}

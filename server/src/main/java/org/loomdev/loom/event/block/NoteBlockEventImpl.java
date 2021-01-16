package org.loomdev.loom.event.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.enums.Instrument;
import org.loomdev.api.block.enums.Note;
import org.loomdev.api.event.block.NoteBlockEvent;

public class NoteBlockEventImpl extends BlockEventImpl implements NoteBlockEvent {

    public NoteBlockEventImpl(Level level, BlockPos blockPos) {
        super(level, blockPos);
    }

    public static class PlayImpl extends NoteBlockEventImpl implements NoteBlockEvent.Play {

        private NoteBlockInstrument instrument;
        private int note;
        private float pitch;

        public PlayImpl(Level level, BlockPos blockPos, NoteBlockInstrument instrument, int note, float pitch) {
            super(level, blockPos);
        }

        @Override
        @NotNull
        public Instrument getInstrument() {
            return Instrument.getByName(instrument.getSerializedName());
        }

        @Override
        public void setInstrument(@NotNull Instrument instrument) {
            this.instrument = NoteBlockInstrument.valueOf(instrument.name());
        }

        @Override
        @NotNull
        public Note getNote() {
            return Note.getByUses(note);
        }

        @Override
        public void setNote(@NotNull Note note) {
            this.note = note.getIndex();
        }

        @Override
        public float getPitch() {
            return pitch;
        }

        @Override
        public void setPitch(float pitch) {
            this.pitch = pitch;
        }

        public NoteBlockInstrument getMinecraftInstrument() {
            return instrument;
        }

        public int getNoteIndex() {
            return note;
        }
    }
}

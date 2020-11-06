package org.loomdev.api.sound;

import net.minecraft.core.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;

public class SoundEventTests {

    @Test
    public void checkSoundEventsAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(SoundEvent.class, Registry.SOUND_EVENT);
    }
}

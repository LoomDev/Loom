package org.loomdev.loom.sound;

import org.loomdev.api.sound.SoundEvent;
import org.loomdev.loom.util.registry.GenericWrapped;

public class SoundEventImpl extends GenericWrapped implements SoundEvent {
    public SoundEventImpl(String key) {
        super(key);
    }
}

package org.loomdev.api.tag;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.api.world.event.GameEventType;

import java.util.Optional;

public interface GameEventTag extends Tag<GameEventType> {

    // region :: GameEventTags
    GameEventTag VIBRATIONS = getById(NamespacedKey.minecraft("vibrations")).get();
    GameEventTag IGNORE_VIBRATIONS_STEPPING_CAREFULLY = getById(NamespacedKey.minecraft("ignore_vibrations_stepping_carefully")).get();
    // endregion :: GameEventTags

    static Optional<GameEventTag> getById(@NotNull NamespacedKey key) {
        return Loom.getRegistry().getTag(GameEventType.class, key);
    }
}

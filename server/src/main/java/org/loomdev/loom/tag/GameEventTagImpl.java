package org.loomdev.loom.tag;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.GameEventTags;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.tag.GameEventTag;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.api.world.event.GameEventType;
import org.loomdev.loom.util.registry.GenericWrapped;
import org.loomdev.loom.world.event.GameEventTypeImpl;

import java.util.Random;
import java.util.stream.Stream;

public class GameEventTagImpl extends GenericWrapped implements GameEventTag {

    private final net.minecraft.tags.Tag<GameEvent> tag;

    public GameEventTagImpl(NamespacedKey key) {
        super(key);
        this.tag = GameEventTags.HELPER.getAllTags().getTag(new ResourceLocation(key.getNamespace(), key.getKey()));

        if (this.tag == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean contains(@NotNull GameEventType type) {
        return tag.contains(((GameEventTypeImpl) type).getMinecraftEvent());
    }

    @Override
    @NotNull
    public Stream<GameEventType> getValues() {
        return tag.getValues().stream()
                .map(block -> GameEventType.getById(getEventKey(block)));
    }

    @Override
    @NotNull
    public GameEventType getRandom(@NotNull Random random) {
        return GameEventType.getById(getEventKey(tag.getRandomElement(random)));
    }

    @NotNull
    private String getEventKey(GameEvent event) {
        return Registry.GAME_EVENT.getKey(event).toString();
    }
}

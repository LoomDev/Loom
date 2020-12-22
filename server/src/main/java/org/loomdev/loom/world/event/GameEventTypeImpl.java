package org.loomdev.loom.world.event;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.world.event.GameEventType;
import org.loomdev.loom.util.registry.GenericWrapped;

public class GameEventTypeImpl extends GenericWrapped implements GameEventType {

    private final GameEvent event;

    public GameEventTypeImpl(String key) {
        super(key);
        this.event = Registry.GAME_EVENT.get(new ResourceLocation(key));
    }

    @NotNull
    public GameEvent getMinecraftEvent() {
        return event;
    }

    @Override
    public int getNotificationRadius() {
        return getMinecraftEvent().getNotificationRadius();
    }
}

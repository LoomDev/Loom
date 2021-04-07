package org.loomdev.api.world.event;

import net.minecraft.core.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;
import org.loomdev.api.helpers.RegistryTestCase;

public class GameEventTypeTests extends RegistryTestCase {

    @Test
    public void checkItemTypesAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(GameEventType.class, Registry.GAME_EVENT);
    }
}

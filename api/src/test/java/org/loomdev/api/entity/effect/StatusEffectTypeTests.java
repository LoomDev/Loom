package org.loomdev.api.entity.effect;

import net.minecraft.util.registry.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;

public class StatusEffectTypeTests {

    @Test
    public void checkStatusEffectTypesAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(StatusEffectType.class, Registry.STATUS_EFFECT);
    }

}

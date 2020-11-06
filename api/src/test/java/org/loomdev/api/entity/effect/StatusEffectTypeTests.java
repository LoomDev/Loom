package org.loomdev.api.entity.effect;

import net.minecraft.core.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;

public class StatusEffectTypeTests {

    @Test
    public void checkStatusEffectTypesAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(StatusEffectType.class, Registry.MOB_EFFECT);
    }
}

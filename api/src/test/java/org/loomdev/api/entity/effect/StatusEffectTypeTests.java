package org.loomdev.api.entity.effect;

import net.minecraft.core.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;
import org.loomdev.api.helpers.RegistryTestCase;

public class StatusEffectTypeTests extends RegistryTestCase {

    @Test
    public void checkStatusEffectTypesAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(StatusEffectType.class, Registry.MOB_EFFECT);
    }
}

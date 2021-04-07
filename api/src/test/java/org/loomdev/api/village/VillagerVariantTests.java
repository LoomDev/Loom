package org.loomdev.api.village;

import net.minecraft.core.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;
import org.loomdev.api.helpers.RegistryTestCase;

public class VillagerVariantTests extends RegistryTestCase {

    @Test
    public void checkVillagerVariantAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(VillagerVariant.class, Registry.VILLAGER_TYPE);
    }
}

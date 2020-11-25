package org.loomdev.api.village;

import net.minecraft.core.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;

public class VillagerVariantTests {

    @Test
    public void checkVillagerVariantAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(VillagerVariant.class, Registry.VILLAGER_TYPE);
    }
}

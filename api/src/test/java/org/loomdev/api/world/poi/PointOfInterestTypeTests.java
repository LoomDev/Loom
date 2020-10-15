package org.loomdev.api.world.poi;

import net.minecraft.util.registry.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;
import org.loomdev.api.village.VillagerProfession;

public class PointOfInterestTypeTests {
    @Test
    public void checkItemTypesAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(PointOfInterestType.class, Registry.POINT_OF_INTEREST_TYPE);
    }
}

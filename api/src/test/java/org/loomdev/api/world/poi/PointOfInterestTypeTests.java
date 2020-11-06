package org.loomdev.api.world.poi;

import net.minecraft.core.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;

public class PointOfInterestTypeTests {

    @Test
    public void checkItemTypesAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(PointOfInterestType.class, Registry.POINT_OF_INTEREST_TYPE);
    }
}

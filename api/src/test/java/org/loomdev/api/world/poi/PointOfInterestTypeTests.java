package org.loomdev.api.world.poi;

import net.minecraft.core.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;
import org.loomdev.api.helpers.RegistryTestCase;

public class PointOfInterestTypeTests extends RegistryTestCase {

    @Test
    public void checkPointOfInterestTypeAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(PointOfInterestType.class, Registry.POINT_OF_INTEREST_TYPE);
    }
}

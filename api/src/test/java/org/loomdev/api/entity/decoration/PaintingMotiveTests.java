package org.loomdev.api.entity.decoration;

import net.minecraft.core.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;
import org.loomdev.api.helpers.RegistryTestCase;

public class PaintingMotiveTests extends RegistryTestCase {

    @Test
    public void checkMotivesAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(Painting.Motive.class, Registry.MOTIVE);
    }
}

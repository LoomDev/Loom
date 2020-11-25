package org.loomdev.api.entity.decoration;

import net.minecraft.core.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;

public class PaintingMotiveTests {
    @Test
    public void checkMotivesAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(Painting.Motive.class, Registry.MOTIVE);
    }
}

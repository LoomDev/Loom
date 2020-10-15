package org.loomdev.api.entity.decoration;

import net.minecraft.util.registry.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;

public class PaintingMotiveTests {
    @Test
    public void checkItemTypesAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(Painting.Motive.class, Registry.PAINTING_MOTIVE);
    }
}

package org.loomdev.api.container;

import net.minecraft.core.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.helpers.LoomAssert;

public class ContainerTypeTests {

    @Test
    public void checkBlockTypesAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(ContainerType.class, Registry.MENU);
    }
}

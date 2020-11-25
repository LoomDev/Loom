package org.loomdev.api.block;

import net.minecraft.core.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;

public class BlockTypeTests {

    @Test
    public void checkBlockTypesAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(BlockType.class, Registry.BLOCK);
    }
}

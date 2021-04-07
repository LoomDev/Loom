package org.loomdev.api.block;

import net.minecraft.core.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;
import org.loomdev.api.helpers.RegistryTestCase;

public class BlockTypeTests extends RegistryTestCase {

    @Test
    public void checkBlockTypesAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(BlockType.class, Registry.BLOCK);
    }
}

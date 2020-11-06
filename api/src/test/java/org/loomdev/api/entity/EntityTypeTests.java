package org.loomdev.api.entity;

import net.minecraft.core.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;

public class EntityTypeTests {

    @Test
    public void checkItemTypesAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(EntityType.class, Registry.ENTITY_TYPE);
    }
}

package org.loomdev.api.entity;

import net.minecraft.core.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;
import org.loomdev.api.helpers.RegistryTestCase;

public class EntityTypeTests extends RegistryTestCase {

    @Test
    public void checkEntityTypesAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(EntityType.class, Registry.ENTITY_TYPE);
    }
}

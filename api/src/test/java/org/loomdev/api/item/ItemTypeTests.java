package org.loomdev.api.item;

import net.minecraft.core.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;
import org.loomdev.api.helpers.RegistryTestCase;

public class ItemTypeTests extends RegistryTestCase {

    @Test
    public void checkItemTypesAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(ItemType.class, Registry.ITEM);
    }
}

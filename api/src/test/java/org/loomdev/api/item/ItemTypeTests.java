package org.loomdev.api.item;

import net.minecraft.util.registry.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;

public class ItemTypeTests {

    @Test
    public void checkItemTypesAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(ItemType.class, Registry.ITEM);
    }

}

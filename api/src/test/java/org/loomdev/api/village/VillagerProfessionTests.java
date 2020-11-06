package org.loomdev.api.village;

import net.minecraft.core.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;

public class VillagerProfessionTests {

    @Test
    public void checkItemTypesAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(VillagerProfession.class, Registry.VILLAGER_PROFESSION);
    }
}

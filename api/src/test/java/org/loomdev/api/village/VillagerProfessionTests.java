package org.loomdev.api.village;

import net.minecraft.core.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;
import org.loomdev.api.helpers.RegistryTestCase;

public class VillagerProfessionTests extends RegistryTestCase {

    @Test
    public void checkVillagerProfessionAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(VillagerProfession.class, Registry.VILLAGER_PROFESSION);
    }
}

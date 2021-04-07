package org.loomdev.api.item;

import net.minecraft.core.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;
import org.loomdev.api.helpers.RegistryTestCase;

public class EnchantmentTests extends RegistryTestCase {

    @Test
    public void checkEnchantmentsAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(Enchantment.class, Registry.ENCHANTMENT);
    }
}

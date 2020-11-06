package org.loomdev.api.item;

import net.minecraft.core.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;

public class EnchantmentTests {

    @Test
    public void checkEnchantmentsAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(Enchantment.class, Registry.ENCHANTMENT);
    }
}

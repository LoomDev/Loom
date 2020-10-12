package org.loomdev.api.item;

import net.minecraft.util.registry.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;

public class EnchantmentTests {

    @Test
    public void checkEnchantmentsAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(Enchantment.class, Registry.ENCHANTMENT);
    }

}

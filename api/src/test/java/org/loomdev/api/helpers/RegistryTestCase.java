package org.loomdev.api.helpers;

import net.minecraft.SharedConstants;
import net.minecraft.server.Bootstrap;
import org.junit.jupiter.api.BeforeAll;

public class RegistryTestCase {

    @BeforeAll
    public static void setupRegistries() {
        SharedConstants.CHECK_DATA_FIXER_SCHEMA = false;
        Bootstrap.bootStrap();
    }
}

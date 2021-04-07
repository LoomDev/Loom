package org.loomdev.api.particle;

import net.minecraft.core.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;
import org.loomdev.api.helpers.RegistryTestCase;

class ParticleTypeTests extends RegistryTestCase {

    @Test
    public void checkParticleTypesAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(ParticleType.class, Registry.PARTICLE_TYPE);
    }
}
package org.loomdev.api.particle;

import net.minecraft.util.registry.Registry;
import org.junit.jupiter.api.Test;
import org.loomdev.api.helpers.LoomAssert;

class ParticleTypeTests {

    @Test
    public void checkParticleTypesAgainstMC() {
        LoomAssert.fieldsAgainstMCRegistry(ParticleType.class, Registry.PARTICLE_TYPE);
    }

}
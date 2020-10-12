package org.loomdev.loom.particle;

import org.loomdev.api.particle.ParticleType;
import org.loomdev.loom.util.registry.GenericWrapped;

public class ParticleTypeImpl extends GenericWrapped implements ParticleType {
    public ParticleTypeImpl(String key) {
        super(key);
    }
}

package org.loomdev.loom.transformer;

import net.minecraft.core.particles.ParticleOptions;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.particle.Particle;

public final class ParticleTransformer implements Transformer<ParticleOptions, Particle> {

    protected ParticleTransformer() {
    }

    @Override
    @NotNull
    public ParticleOptions toMinecraft(@NotNull Particle particle) {
        throw new UnsupportedOperationException(); // TODO
    }

    @Override
    @NotNull
    public Particle toLoom(@NotNull ParticleOptions particleOptions) {
        throw new UnsupportedOperationException();
    }
}

package org.loomdev.loom.util.transformer;

import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.particle.Particle;
import org.loomdev.api.particle.data.DustData;
import org.loomdev.api.util.Color;

public class ParticleTransformer {

    private ParticleTransformer() {
        throw new UnsupportedOperationException("ParticleTransformer shouldn't be initialized.");
    }

    public static @NotNull ParticleEffect toMinecraft(@NotNull Particle particle) {
        ParticleType<?> mcType = Registry.PARTICLE_TYPE.get(new Identifier(particle.getType().getKey().toString()));

        if (particle.getType() == org.loomdev.api.particle.ParticleType.DUST) {
            DustData data = (DustData) particle.getData().orElse(new DustData(Color.RED, 1));

            return new DustParticleEffect(
                    data.getColor().getRed() / 255f,
                    data.getColor().getGreen() / 255f,
                    data.getColor().getBlue() / 255f,
                    data.getSize()
            );
        }

        return (DefaultParticleType) mcType;
    }

    // TODO to loom
}

package org.loomdev.loom.util.transformer;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.particle.Particle;
import org.loomdev.api.particle.data.DustData;
import org.loomdev.api.util.Color;

public class ParticleTransformer {

    private ParticleTransformer() {
    }

    @NotNull
    public static ParticleOptions toMinecraft(@NotNull Particle particle) {
        ParticleType<?> mcType = Registry.PARTICLE_TYPE.get(new ResourceLocation(particle.getType().getKey().toString()));

        if (particle.getType() == org.loomdev.api.particle.ParticleType.DUST) {
            DustData data = (DustData) particle.getData().orElse(new DustData(Color.RED, 1));

            /*return new DustParticleEffect(
                    data.getColor().getRed() / 255f,
                    data.getColor().getGreen() / 255f,
                    data.getColor().getBlue() / 255f,
                    data.getSize()
            );*/
            return null; // TODO
        }

        return (SimpleParticleType) mcType;
    }

    // TODO to loom
}

package org.loomdev.loom.world.biome;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.village.VillagerType;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.Loom;
import org.loomdev.api.village.VillagerVariant;
import org.loomdev.api.world.biome.BiomeType;
import org.loomdev.loom.util.registry.GenericWrapped;

import java.util.Optional;

public class BiomeTypeImpl extends GenericWrapped implements BiomeType {

    private final RegistryKey<Biome> mcBiomeKey;

    public BiomeTypeImpl(String key) {
        super(key);
        this.mcBiomeKey = RegistryKey.of(Registry.BIOME_KEY, new Identifier(key));
    }

    @Override
    public @Nullable VillagerVariant getVillagerVariant() {
        return Loom.getRegistry().getWrapped(
                VillagerVariant.class,
                Registry.VILLAGER_TYPE.getId(VillagerType.forBiome(Optional.of(this.mcBiomeKey))).toString()
        );
    }
}

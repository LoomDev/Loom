package org.loomdev.loom.world.biome;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.village.VillagerType;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.Loom;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.api.village.VillagerVariant;
import org.loomdev.api.world.biome.BiomeType;

import java.util.Optional;

public class BiomeTypeImpl implements BiomeType {

    private final RegistryKey<Biome> mcBiomeKey;
    private final NamespacedKey namespacedKey;

    public BiomeTypeImpl(String key) {
        this.mcBiomeKey = RegistryKey.of(Registry.BIOME_KEY, new Identifier(key));
        this.namespacedKey = NamespacedKey.of(key);
    }

    @Override
    public @Nullable VillagerVariant getVillagerVariant() {
        return Loom.getRegistry().getWrapped(
                VillagerVariant.class,
                Registry.VILLAGER_TYPE.getId(VillagerType.forBiome(Optional.of(this.mcBiomeKey))).toString()
        );
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return this.namespacedKey;
    }
}

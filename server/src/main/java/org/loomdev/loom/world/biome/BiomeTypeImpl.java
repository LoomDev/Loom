package org.loomdev.loom.world.biome;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.village.VillagerVariant;
import org.loomdev.api.world.biome.BiomeType;
import org.loomdev.loom.util.registry.GenericWrapped;

import java.util.Optional;

public class BiomeTypeImpl extends GenericWrapped implements BiomeType {

    private final ResourceKey<Biome> mcBiomeKey;

    public BiomeTypeImpl(String key) {
        super(key);
        this.mcBiomeKey = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(key));
    }

    @Override
    public @Nullable VillagerVariant getVillagerVariant() {
        return org.loomdev.api.util.registry.Registry.get().getWrapped(
                VillagerVariant.class,
                Registry.VILLAGER_TYPE.getKey(VillagerType.byBiome(Optional.of(this.mcBiomeKey))).toString()
        );
    }
}

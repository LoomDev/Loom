package org.loomdev.loom.village;

import com.google.common.collect.ImmutableSet;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.Loom;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.item.ItemType;
import org.loomdev.api.sound.Sound;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.api.village.VillagerProfession;
import org.loomdev.api.world.poi.PointOfInterestType;

import java.util.stream.Collectors;

public class VillagerProfessionImpl implements VillagerProfession {

    private net.minecraft.village.VillagerProfession mcVillagerProfession;
    private final NamespacedKey namespacedKey;

    public VillagerProfessionImpl(String key) {
        this.mcVillagerProfession = Registry.VILLAGER_PROFESSION.get(new Identifier(key));
        this.namespacedKey = NamespacedKey.of(key);
    }

    @Override
    @NotNull
    public PointOfInterestType getWorkStation() {
        return Loom.getRegistry().getWrapped(
                PointOfInterestType.class,
                Registry.POINT_OF_INTEREST_TYPE.getId(this.mcVillagerProfession.getWorkStation()).toString()
        );
    }

    @Override
    public ImmutableSet<ItemType> getGatherableItems() {
        return ImmutableSet.copyOf(this.mcVillagerProfession.getGatherableItems().stream()
                .map(item -> ItemType.getById(Registry.ITEM.getId(item).toString()))
                .collect(Collectors.toSet()));
    }

    @Override
    public ImmutableSet<BlockType> getSecondaryJobSites() {
        return ImmutableSet.copyOf(this.mcVillagerProfession.getSecondaryJobSites().stream()
                .map(block -> BlockType.getById(Registry.BLOCK.getId(block).toString()))
                .collect(Collectors.toSet()));
    }

    @Override
    @Nullable
    public Sound.Type getWorkSound() {
        return Sound.Type.getByRawId(Registry.SOUND_EVENT.getRawId(this.mcVillagerProfession.getWorkSound())).get();
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return this.namespacedKey;
    }

}

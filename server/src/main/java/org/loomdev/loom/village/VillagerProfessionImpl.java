package org.loomdev.loom.village;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.item.ItemType;
import org.loomdev.api.sound.SoundEvent;
import org.loomdev.api.village.VillagerProfession;
import org.loomdev.api.world.poi.PointOfInterestType;
import org.loomdev.loom.util.registry.GenericWrapped;

import java.util.stream.Collectors;

public class VillagerProfessionImpl extends GenericWrapped implements VillagerProfession {

    private final net.minecraft.world.entity.npc.VillagerProfession mcVillagerProfession;

    public VillagerProfessionImpl(String key) {
        super(key);
        this.mcVillagerProfession = Registry.VILLAGER_PROFESSION.get(new ResourceLocation(key));
    }

    @Override
    @NotNull
    public PointOfInterestType getWorkStation() {
        return org.loomdev.api.util.registry.Registry.get().getWrapped(
                PointOfInterestType.class,
                Registry.POINT_OF_INTEREST_TYPE.getKey(this.mcVillagerProfession.getJobPoiType()).toString()
        );
    }

    @Override
    public ImmutableSet<ItemType> getGatherableItems() {
        return ImmutableSet.copyOf(this.mcVillagerProfession.getRequestedItems().stream()
                .map(item -> ItemType.getById(Registry.ITEM.getKey(item).toString()))
                .collect(Collectors.toSet()));
    }

    @Override
    public ImmutableSet<BlockType> getSecondaryJobSites() {
        return ImmutableSet.copyOf(this.mcVillagerProfession.getSecondaryPoi().stream()
                .map(block -> BlockType.getById(Registry.BLOCK.getKey(block).toString()))
                .collect(Collectors.toSet()));
    }

    @Override
    @Nullable
    public SoundEvent getWorkSound() {
        return SoundEvent.getById(Registry.SOUND_EVENT.getKey(this.mcVillagerProfession.getWorkSound()).toString());
    }
}

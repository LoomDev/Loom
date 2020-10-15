package org.loomdev.loom.village;

import com.google.common.collect.ImmutableSet;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.Loom;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.item.ItemType;
import org.loomdev.api.sound.SoundEvent;
import org.loomdev.api.village.VillagerProfession;
import org.loomdev.api.world.poi.PointOfInterestType;
import org.loomdev.loom.util.registry.GenericWrapped;

import java.util.stream.Collectors;

public class VillagerProfessionImpl extends GenericWrapped implements VillagerProfession {

    private final net.minecraft.village.VillagerProfession mcVillagerProfession;

    public VillagerProfessionImpl(String key) {
        super(key);
        this.mcVillagerProfession = Registry.VILLAGER_PROFESSION.get(new Identifier(key));
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
    public SoundEvent getWorkSound() {
        return SoundEvent.getById(Registry.SOUND_EVENT.getId(this.mcVillagerProfession.getWorkSound()).toString());
    }

}

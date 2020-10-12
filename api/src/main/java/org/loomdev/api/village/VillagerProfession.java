package org.loomdev.api.village;

import com.google.common.collect.ImmutableSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.Loom;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.item.ItemType;
import org.loomdev.api.sound.Sound;
import org.loomdev.api.sound.SoundEvent;
import org.loomdev.api.util.registry.Keyed;
import org.loomdev.api.world.poi.PointOfInterestType;

/**
 * Represents the profession of a {@link org.loomdev.api.entity.passive.Villager}.
 */
public interface VillagerProfession extends Keyed {

    // region :: VillagerProfessions

    VillagerProfession NONE = getById("minecraft:none");
    VillagerProfession ARMORER = getById("minecraft:armorer");
    VillagerProfession BUTCHER = getById("minecraft:butcher");
    VillagerProfession CARTOGRAPHER = getById("minecraft:cartographer");
    VillagerProfession CLERIC = getById("minecraft:cleric");
    VillagerProfession FARMER = getById("minecraft:farmer");
    VillagerProfession FISHERMAN = getById("minecraft:fisherman");
    VillagerProfession FLETCHER = getById("minecraft:fletcher");
    VillagerProfession LEATHERWORKER = getById("minecraft:leatherworker");
    VillagerProfession LIBRARIAN = getById("minecraft:librarian");
    VillagerProfession MASON = getById("minecraft:mason");
    VillagerProfession NITWIT = getById("minecraft:nitwit");
    VillagerProfession SHEPHERD = getById("minecraft:shepherd");
    VillagerProfession TOOLSMITH = getById("minecraft:toolsmith");
    VillagerProfession WEAPONSMITH = getById("minecraft:weaponsmith");

    // endregion :: VillagerProfessions

    static VillagerProfession getById(String id) {
        return Loom.getRegistry().getWrapped(VillagerProfession.class, id);
    }

    @NotNull
    PointOfInterestType getWorkStation();

    ImmutableSet<ItemType> getGatherableItems();

    ImmutableSet<BlockType> getSecondaryJobSites();

    @Nullable
    SoundEvent getWorkSound();

}

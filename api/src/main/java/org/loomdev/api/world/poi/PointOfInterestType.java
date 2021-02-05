package org.loomdev.api.world.poi;

import com.google.common.collect.ImmutableSet;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.util.registry.Keyed;
import org.loomdev.api.util.registry.Registry;

public interface PointOfInterestType extends Keyed {

    // region :: PointOfInterestTypes

    PointOfInterestType UNEMPLOYED = getById("minecraft:unemployed");
    PointOfInterestType ARMORER = getById("minecraft:armorer");
    PointOfInterestType BUTCHER = getById("minecraft:butcher");
    PointOfInterestType CARTOGRAPHER = getById("minecraft:cartographer");
    PointOfInterestType CLERIC = getById("minecraft:cleric");
    PointOfInterestType FARMER = getById("minecraft:farmer");
    PointOfInterestType FISHERMAN = getById("minecraft:fisherman");
    PointOfInterestType FLETCHER = getById("minecraft:fletcher");
    PointOfInterestType LEATHERWORKER = getById("minecraft:leatherworker");
    PointOfInterestType LIBRARIAN = getById("minecraft:librarian");
    PointOfInterestType MASON = getById("minecraft:mason");
    PointOfInterestType NITWIT = getById("minecraft:nitwit");
    PointOfInterestType SHEPHERD = getById("minecraft:shepherd");
    PointOfInterestType TOOLSMITH = getById("minecraft:toolsmith");
    PointOfInterestType WEAPONSMITH = getById("minecraft:weaponsmith");
    PointOfInterestType HOME = getById("minecraft:home");
    PointOfInterestType MEETING = getById("minecraft:meeting");
    PointOfInterestType BEEHIVE = getById("minecraft:beehive");
    PointOfInterestType BEE_NEST = getById("minecraft:bee_nest");
    PointOfInterestType NETHER_PORTAL = getById("minecraft:nether_portal");
    PointOfInterestType LODESTONE = getById("minecraft:lodestone");
    PointOfInterestType LIGHTNING_ROD = getById("minecraft:lightning_rod");

    // endregion :: PointOfInterestTypes

    static PointOfInterestType getById(String id) {
        return Registry.get().getWrapped(PointOfInterestType.class, id);
    }

    ImmutableSet<BlockState> getBlockStates();

    int getTickCount();

    int getSearchDistance();

}

package org.loomdev.loom.world.poi;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.world.poi.PointOfInterestType;
import org.loomdev.loom.block.BlockStateImpl;
import org.loomdev.loom.util.registry.GenericWrapped;

import java.util.stream.Collectors;

public class PointOfInterestTypeImpl extends GenericWrapped implements PointOfInterestType {

    private final PoiType mcPoiType;

    public PointOfInterestTypeImpl(String key) {
        super(key);
        this.mcPoiType = Registry.POINT_OF_INTEREST_TYPE.get(new ResourceLocation(key));
    }

    @Override
    public ImmutableSet<BlockState> getBlockStates() {
        return ImmutableSet.copyOf(
                this.mcPoiType.matchingStates.stream()
                    .map(BlockStateImpl::new)
                    .collect(Collectors.toSet())
        );
    }

    @Override
    public int getTickCount() {
        return this.mcPoiType.getMaxTickets();
    }

    @Override
    public int getSearchDistance() {
        return this.mcPoiType.getValidRange();
    }
}

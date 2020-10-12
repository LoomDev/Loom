package org.loomdev.loom.world.poi;

import com.google.common.collect.ImmutableSet;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.world.poi.PointOfInterestType;
import org.loomdev.loom.block.BlockStateImpl;
import org.loomdev.loom.util.registry.GenericWrapped;

import java.util.stream.Collectors;

public class PointOfInterestTypeImpl extends GenericWrapped implements PointOfInterestType {

    private final net.minecraft.world.poi.PointOfInterestType mcPoiType;

    public PointOfInterestTypeImpl(String key) {
        super(key);
        this.mcPoiType = Registry.POINT_OF_INTEREST_TYPE.get(new Identifier(key));
    }

    @Override
    public ImmutableSet<BlockState> getBlockStates() {
        return ImmutableSet.copyOf(
                this.mcPoiType.blockStates.stream()
                    .map(BlockStateImpl::of)
                    .collect(Collectors.toSet())
        );
    }

    @Override
    public int getTickCount() {
        return this.mcPoiType.getTicketCount();
    }

    @Override
    public int getSearchDistance() {
        return this.mcPoiType.getSearchDistance();
    }
}

package org.loomdev.loom.world.poi;

import com.google.common.collect.ImmutableSet;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.api.world.poi.PointOfInterestType;
import org.loomdev.loom.block.BlockStateImpl;

import java.util.stream.Collectors;

public class PointOfInterestTypeImpl implements PointOfInterestType {

    private net.minecraft.world.poi.PointOfInterestType mcPoiType;
    private NamespacedKey namespacedKey;

    public PointOfInterestTypeImpl(String key) {
        this.mcPoiType = Registry.POINT_OF_INTEREST_TYPE.get(new Identifier(key));
        this.namespacedKey = NamespacedKey.of(key);
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

    @Override
    public @NotNull NamespacedKey getKey() {
        return this.namespacedKey;
    }
}

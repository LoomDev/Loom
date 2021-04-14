package org.loomdev.loom.entity.misc;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.misc.Marker;
import org.loomdev.loom.entity.EntityImpl;

public class MarkerImpl extends EntityImpl implements Marker {

    public MarkerImpl(net.minecraft.world.entity.Marker entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<? extends Marker> getType() {
        return EntityType.MARKER;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.Marker getMinecraftEntity() {
        return (net.minecraft.world.entity.Marker) super.getMinecraftEntity();
    }
}

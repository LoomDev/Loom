package org.loomdev.loom.entity.decoration;

import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.util.math.BlockPos;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.decoration.EndCrystal;
import org.loomdev.api.world.Location;
import org.loomdev.loom.entity.EntityImpl;

import java.util.Optional;

public class EndCrystalImpl extends EntityImpl implements EndCrystal {

    public EndCrystalImpl(EndCrystalEntity entity) {
        super(entity);
    }

    @Override
    public @NonNull EntityType getType() {
        return EntityType.END_CRYSTAL;
    }

    @Override
    public EndCrystalEntity getMinecraftEntity() {
        return (EndCrystalEntity) super.getMinecraftEntity();
    }

    @Override
    public @NonNull Optional<Location> getBeamTarget() {
        return Optional.ofNullable(getMinecraftEntity().getBeamTarget())
                .map(p -> new Location(null, p.getX(), p.getY(), p.getZ())); // TODO world
    }

    @Override
    public void setBeamTarget(@Nullable Location location) {
        getMinecraftEntity().setBeamTarget(new BlockPos(location.getX(), location.getY(), location.getZ()));
    }

    @Override
    public boolean isBottomShown() {
        return getMinecraftEntity().getShowBottom();
    }

    @Override
    public void setBottomShown(boolean flag) {
        getMinecraftEntity().setShowBottom(flag);
    }
}

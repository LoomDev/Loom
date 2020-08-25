package org.loomdev.loom.entity.decoration;

import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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
    public @NotNull EntityType getType() {
        return EntityType.END_CRYSTAL;
    }

    @Override
    public @NotNull EndCrystalEntity getMinecraftEntity() {
        return (EndCrystalEntity) super.getMinecraftEntity();
    }

    @Override
    public @NotNull Optional<Location> getBeamTarget() {
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

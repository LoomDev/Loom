package org.loomdev.loom.entity.boss.enderdragon;

import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.boss.enderdragon.EndCrystal;
import org.loomdev.api.world.Location;
import org.loomdev.loom.entity.EntityImpl;

public class EndCrystalImpl extends EntityImpl implements EndCrystal {

    public EndCrystalImpl(net.minecraft.world.entity.boss.enderdragon.EndCrystal entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<EndCrystal> getType() {
        return EntityType.END_CRYSTAL;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.boss.enderdragon.EndCrystal getMinecraftEntity() {
        return (net.minecraft.world.entity.boss.enderdragon.EndCrystal) super.getMinecraftEntity();
    }

    @Override
    @Nullable
    public Location getBeamTarget() {
        var target = getMinecraftEntity().getBeamTarget();
        if (target == null) return null;
        return new Location(null, target.getX(), target.getY(), target.getZ()); // TODO world
    }

    @Override
    public void setBeamTarget(@Nullable Location location) {
        getMinecraftEntity().setBeamTarget(new BlockPos(location.getX(), location.getY(), location.getZ()));
    }

    @Override
    public boolean isBottomShown() {
        return getMinecraftEntity().showsBottom();
    }

    @Override
    public void setBottomShown(boolean flag) {
        getMinecraftEntity().setShowBottom(flag);
    }
}

package org.loomdev.loom.entity.misc;

import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.misc.LightningBolt;
import org.loomdev.api.entity.player.Player;
import org.loomdev.loom.entity.EntityImpl;
import org.loomdev.loom.entity.player.PlayerImpl;

import java.util.Optional;

public class LightningBoltImpl extends EntityImpl implements LightningBolt {

    public LightningBoltImpl(net.minecraft.world.entity.LightningBolt entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType<LightningBolt> getType() {
        return EntityType.LIGHTNING_BOLT;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.LightningBolt getMinecraftEntity() {
        return (net.minecraft.world.entity.LightningBolt) super.getMinecraftEntity();
    }

    @Override
    public boolean isVisualOnly() {
        return getMinecraftEntity().visualOnly;
    }

    @Override
    public void setVisualOnly(boolean visualOnly) {
        getMinecraftEntity().visualOnly = visualOnly;
    }

    @Override
    @NotNull
    public Optional<Player> getCause() {
        return Optional.ofNullable(getMinecraftEntity().cause)
                .map(Entity::getLoomEntity)
                .map(Player.class::cast);
    }

    @Override
    public void setCause(@Nullable Player player) {
        if (player == null) {
            getMinecraftEntity().setCause(null);
        } else {
            getMinecraftEntity().setCause(((PlayerImpl) player).getMinecraftEntity());
        }
    }
}

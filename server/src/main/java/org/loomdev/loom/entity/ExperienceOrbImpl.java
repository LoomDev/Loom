package org.loomdev.loom.entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.ExperienceOrb;
import org.loomdev.api.entity.player.Player;
import org.loomdev.loom.entity.player.PlayerImpl;

public class ExperienceOrbImpl extends EntityImpl implements ExperienceOrb {

    public ExperienceOrbImpl(net.minecraft.world.entity.ExperienceOrb entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<ExperienceOrb> getType() {
        return EntityType.EXPERIENCE_ORB;
    }

    @NotNull
    @Override
    public net.minecraft.world.entity.ExperienceOrb getMinecraftEntity() {
        return (net.minecraft.world.entity.ExperienceOrb) super.getMinecraftEntity();
    }

    @Override
    @Nullable
    public Player getTarget() {
        var target = getMinecraftEntity().followingPlayer;
        if (target == null) return null;
        return (Player) target.getLoomEntity();
    }

    @Override
    public void setTarget(@Nullable Player player) {
        if (player == null) {
            getMinecraftEntity().followingPlayer = null;
            return;
        }
        getMinecraftEntity().followingPlayer = ((PlayerImpl) player).getMinecraftEntity();
    }

    @Override
    public int getAmount() {
        return getMinecraftEntity().value;
    }

    @Override
    public void setAmount(int amount) {
        getMinecraftEntity().value = amount;
    }

    @Override
    public int getHealth() {
        return getMinecraftEntity().health;
    }

    @Override
    public void setHealth(int health) {
        getMinecraftEntity().health = health;
    }

    @Override
    public int getOrbAge() {
        return getMinecraftEntity().age;
    }

    @Override
    public void setOrbAge(int age) {
        getMinecraftEntity().age = age;
    }
}

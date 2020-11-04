package org.loomdev.loom.entity;

import net.minecraft.entity.ExperienceOrbEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.ExperienceOrb;
import org.loomdev.api.entity.player.Player;
import org.loomdev.loom.entity.player.PlayerImpl;

public class ExperienceOrbImpl extends EntityImpl implements ExperienceOrb {

    public ExperienceOrbImpl(ExperienceOrbEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType<ExperienceOrb> getType() {
        return EntityType.EXPERIENCE_ORB;
    }

    @NotNull
    @Override
    public ExperienceOrbEntity getMinecraftEntity() {
        return (ExperienceOrbEntity) super.getMinecraftEntity();
    }

    @Override
    public Player getTarget() {
        return (Player) getMinecraftEntity().target.getLoomEntity();
    }

    @Override
    public void setTarget(Player player) {
        getMinecraftEntity().target = ((PlayerImpl) player).getMinecraftEntity();
    }

    @Override
    public int getAmount() {
        return getMinecraftEntity().getExperienceAmount();
    }

    @Override
    public void setAmount(int amount) {
        getMinecraftEntity().amount = amount;
    }

    @Override
    public int getPickupDelay() {
        return getMinecraftEntity().field_27009;
    }

    @Override
    public void setPickupDelay(int pickupDelay) {
        getMinecraftEntity().field_27009 = pickupDelay;
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
        return getMinecraftEntity().orbAge;
    }

    @Override
    public void setOrbAge(int orbAge) {
        getMinecraftEntity().orbAge = orbAge;
    }
}

package org.loomdev.loom.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.passive.Animal;
import org.loomdev.api.item.ItemStack;
import org.loomdev.loom.entity.AgeableMobImpl;
import org.loomdev.loom.item.ItemStackImpl;

import java.util.UUID;

public abstract class AnimalImpl extends AgeableMobImpl implements Animal {

    public AnimalImpl(net.minecraft.world.entity.animal.Animal entity) {
        super(entity);
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.Animal getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.Animal) super.getMinecraftEntity();
    }

    @Override
    public int getLoveTicks() {
        return getMinecraftEntity().getInLoveTime();
    }

    @Override
    public void setLoveTicks(int ticks) {
        getMinecraftEntity().setInLoveTime(ticks);
    }

    @Override
    public void resetLoveTicks() {
        getMinecraftEntity().resetLove();
    }

    @Override
    public boolean isInLove() {
        return getMinecraftEntity().isInLove();
    }

    @Override
    public UUID getBreedCause() {
        return getMinecraftEntity().loveCause; // TODO idfk
    }

    @Override
    public void setBreedCause(@NotNull UUID uuid) {
        getMinecraftEntity().loveCause = uuid;
    }

    @Override
    public boolean isBreedingItem(@NotNull ItemStack itemStack) {
        return getMinecraftEntity().isFood(((ItemStackImpl) itemStack).getMinecraftItemStack());
    }
}

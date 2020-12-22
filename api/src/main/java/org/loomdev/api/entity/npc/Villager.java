package org.loomdev.api.entity.npc;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.npc.AbstractVillager;
import org.loomdev.api.village.VillagerProfession;
import org.loomdev.api.village.VillagerVariant;

/**
 * Represent a Villager entity.
 */
public interface Villager extends AbstractVillager {

    /**
     * Gets the villager's variant.
     *
     * @return The variant.
     */
    @NotNull
    VillagerVariant getVariant();

    /**
     * Sets the villager's variant.
     *
     * @param variant The variant.
     */
    void setVariant(@NotNull VillagerVariant variant);

    /**
     * Gets the villager's profession.
     *
     * @return The profession.
     */
    @NotNull VillagerProfession getProfession();

    /**
     * Sets the villager's profession.
     *
     * @param profession The profession.
     */
    void setProfession(@NotNull VillagerProfession profession);

    int getLevel();

    void setLevel(int level);

    int getExperience();

    void setExperience(int experience);

    void restock();

    @Override
    boolean isSleeping();

    Component getDefaultName();

    // TODO gossip?

}

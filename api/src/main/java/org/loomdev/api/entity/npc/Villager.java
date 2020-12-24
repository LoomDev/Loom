package org.loomdev.api.entity.npc;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.npc.AbstractVillager;
import org.loomdev.api.village.VillagerProfession;
import org.loomdev.api.village.VillagerVariant;

/**
 * Represent a villager entity.
 */
public interface Villager extends AbstractVillager {

    @NotNull
    VillagerVariant getVariant();

    void setVariant(@NotNull VillagerVariant variant);

    @NotNull VillagerProfession getProfession();

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

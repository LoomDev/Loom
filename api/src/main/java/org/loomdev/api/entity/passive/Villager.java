package org.loomdev.api.entity.passive;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.village.VillagerProfession;
import org.loomdev.api.village.VillagerVariant;

/**
 * Represent a Villager entity.
 */
public interface Villager extends AbstractTrader {

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

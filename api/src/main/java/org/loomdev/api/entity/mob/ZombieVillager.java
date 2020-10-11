package org.loomdev.api.entity.mob;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.ConvertingEntity;
import org.loomdev.api.village.VillagerProfession;
import org.loomdev.api.village.VillagerVariant;

import java.util.UUID;

/**
 * Represents a villager that used to be a villager.
 */
public interface ZombieVillager extends Zombie, ConvertingEntity {

    @NotNull
    VillagerVariant getVillagerVariant();

    void setVillagerVariant(@NotNull VillagerVariant variant);

    @NotNull VillagerProfession getVillagerProfession();

    void setVillagerProfession(@NotNull VillagerProfession profession);

    int getVillagerLevel();

    void setVillagerLevel(int level);

    int getExperience();

    void setExperience(int experience);

    @Nullable
    UUID getConverterUniqueId();


}

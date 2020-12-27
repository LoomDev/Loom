package org.loomdev.api.entity.monster.zombie;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.ConvertableEntity;
import org.loomdev.api.village.VillagerProfession;
import org.loomdev.api.village.VillagerVariant;

import java.util.Optional;
import java.util.UUID;

/**
 * Represents a villager that was turned into a zombie.
 */
public interface ZombieVillager extends Zombie, ConvertableEntity {

    @NotNull
    VillagerVariant getVillagerVariant();

    void setVillagerVariant(@NotNull VillagerVariant variant);

    @NotNull VillagerProfession getVillagerProfession();

    void setVillagerProfession(@NotNull VillagerProfession profession);

    int getVillagerLevel();

    void setVillagerLevel(int level);

    int getExperience();

    void setExperience(int experience);

    @NotNull
    Optional<UUID> getConverterUniqueId();
}

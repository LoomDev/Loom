package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.VillagerData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.ZombieVillager;
import org.loomdev.api.village.VillagerProfession;
import org.loomdev.api.village.VillagerVariant;

import java.util.UUID;

public class ZombieVillagerImpl extends ZombieImpl implements ZombieVillager {

    public ZombieVillagerImpl(ZombieVillagerEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ZOMBIE_VILLAGER;
    }

    @Override
    public @NotNull ZombieVillagerEntity getMinecraftEntity() {
        return (ZombieVillagerEntity) super.getMinecraftEntity();
    }

    @Override
    public @NotNull VillagerVariant getVillagerVariant() {
        VillagerData data = getMinecraftEntity().getVillagerData();
        return VillagerVariant.getById("minecraft:" + data.getType().toString());
    }

    @Override
    public void setVillagerVariant(@NotNull VillagerVariant villagerVariant) {
        getMinecraftEntity().setVillagerData(
                getMinecraftEntity().getVillagerData()
                        .withType(Registry.VILLAGER_TYPE.get(new Identifier(villagerVariant.getKey().toString())))
        );
    }

    @Override
    public @NotNull VillagerProfession getVillagerProfession() {
        VillagerData data = getMinecraftEntity().getVillagerData();
        return VillagerProfession.getById("minecraft:" + data.getProfession().toString());
    }

    @Override
    public void setVillagerProfession(@NotNull VillagerProfession villagerProfession) {
        getMinecraftEntity().setVillagerData(
                getMinecraftEntity().getVillagerData()
                        .withProfession(Registry.VILLAGER_PROFESSION.get(new Identifier(villagerProfession.getKey().toString())))
        );
    }

    @Override
    public int getVillagerLevel() {
        return getMinecraftEntity().getVillagerData().getLevel();
    }

    @Override
    public void setVillagerLevel(int level) {
        getMinecraftEntity().setVillagerData(
                getMinecraftEntity().getVillagerData()
                        .withLevel(level)
        );
    }

    @Override
    public int getExperience() {
        return getMinecraftEntity().xp;
    }

    @Override
    public void setExperience(int experience) {
        getMinecraftEntity().setXp(experience);
    }

    @Override
    public @Nullable UUID getConverterUniqueId() {
        return getMinecraftEntity().converter;
    }
}

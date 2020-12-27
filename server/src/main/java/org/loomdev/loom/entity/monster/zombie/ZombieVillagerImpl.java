package org.loomdev.loom.entity.monster.zombie;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.monster.zombie.ZombieVillager;
import org.loomdev.api.village.VillagerProfession;
import org.loomdev.api.village.VillagerVariant;

import java.util.Optional;
import java.util.UUID;

public class ZombieVillagerImpl extends ZombieImpl implements ZombieVillager {

    public ZombieVillagerImpl(net.minecraft.world.entity.monster.ZombieVillager entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<ZombieVillager> getType() {
        return EntityType.ZOMBIE_VILLAGER;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.ZombieVillager getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.ZombieVillager) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public VillagerVariant getVillagerVariant() {
        var data = getMinecraftEntity().getVillagerData();
        return VillagerVariant.getById("minecraft:" + data.getType().toString());
    }

    @Override
    public void setVillagerVariant(@NotNull VillagerVariant villagerVariant) {
        var type = Registry.VILLAGER_TYPE.get(new ResourceLocation(villagerVariant.getKey().toString()));
        getMinecraftEntity().setVillagerData(getMinecraftEntity().getVillagerData().setType(type));
    }

    @Override
    @NotNull
    public VillagerProfession getVillagerProfession() {
        var data = getMinecraftEntity().getVillagerData();
        return VillagerProfession.getById("minecraft:" + data.getProfession().toString());
    }

    @Override
    public void setVillagerProfession(@NotNull VillagerProfession villagerProfession) {
        var profession = Registry.VILLAGER_PROFESSION.get(new ResourceLocation(villagerProfession.getKey().toString()));
        getMinecraftEntity().setVillagerData(getMinecraftEntity().getVillagerData().setProfession(profession));
    }

    @Override
    public int getVillagerLevel() {
        return getMinecraftEntity().getVillagerData().getLevel();
    }

    @Override
    public void setVillagerLevel(int level) {
        getMinecraftEntity().setVillagerData(getMinecraftEntity().getVillagerData().setLevel(level));
    }

    @Override
    public int getExperience() {
        return getMinecraftEntity().villagerXp;
    }

    @Override
    public void setExperience(int experience) {
        getMinecraftEntity().villagerXp = experience;
    }

    @Override
    @NotNull
    public Optional<UUID> getConverterUniqueId() {
        return Optional.ofNullable(getMinecraftEntity().conversionStarter);
    }
}

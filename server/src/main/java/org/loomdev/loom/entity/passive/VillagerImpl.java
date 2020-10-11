package org.loomdev.loom.entity.passive;

import net.kyori.adventure.text.Component;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.VillagerData;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Villager;
import org.loomdev.api.village.VillagerProfession;
import org.loomdev.api.village.VillagerVariant;
import org.loomdev.loom.util.transformer.TextTransformer;

public class VillagerImpl extends AbstractTraderImpl implements Villager {

    public VillagerImpl(VillagerEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType<Villager> getType() {
        return EntityType.VILLAGER;
    }

    @Override
    public @NotNull VillagerEntity getMinecraftEntity() {
        return (VillagerEntity) super.getMinecraftEntity();
    }

    @Override
    public @NotNull VillagerVariant getVariant() {
        VillagerData data = getMinecraftEntity().getVillagerData();
        return VillagerVariant.getById("minecraft:" + data.getType().toString());
    }

    @Override
    public void setVariant(@NotNull VillagerVariant villagerVariant) {
        getMinecraftEntity().setVillagerData(
                getMinecraftEntity().getVillagerData()
                        .withType(Registry.VILLAGER_TYPE.get(new Identifier(villagerVariant.getKey().toString())))
        );
    }

    @Override
    public @NotNull VillagerProfession getProfession() {
        VillagerData data = getMinecraftEntity().getVillagerData();
        return VillagerProfession.getById("minecraft:" + data.getProfession().toString());
    }

    @Override
    public void setProfession(@NotNull VillagerProfession villagerProfession) {
        getMinecraftEntity().setVillagerData(
                getMinecraftEntity().getVillagerData()
                        .withProfession(Registry.VILLAGER_PROFESSION.get(new Identifier(villagerProfession.getKey().toString())))
        );
    }

    @Override
    public int getLevel() {
        return getMinecraftEntity().getVillagerData().getLevel();
    }

    @Override
    public void setLevel(int level) {
        getMinecraftEntity().setVillagerData(
                getMinecraftEntity().getVillagerData()
                     .withLevel(level)
        );
    }

    @Override
    public void restock() {
        getMinecraftEntity().restock();
    }

    @Override
    public Component getDefaultName() {
        return TextTransformer.toLoom(getMinecraftEntity().getDefaultName());
    }
}

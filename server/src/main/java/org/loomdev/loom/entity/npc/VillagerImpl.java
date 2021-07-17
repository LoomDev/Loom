package org.loomdev.loom.entity.npc;

import net.kyori.adventure.text.Component;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.npc.Villager;
import org.loomdev.api.village.VillagerProfession;
import org.loomdev.api.village.VillagerVariant;
import org.loomdev.loom.transformer.Transformer;

public class VillagerImpl extends AbstractVillagerImpl implements Villager {

    public VillagerImpl(net.minecraft.world.entity.npc.Villager entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType<Villager> getType() {
        return EntityType.VILLAGER;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.npc.Villager getMinecraftEntity() {
        return (net.minecraft.world.entity.npc.Villager) super.getMinecraftEntity();
    }

    @Override
    public @NotNull VillagerVariant getVariant() {
        var data = getMinecraftEntity().getVillagerData();
        return VillagerVariant.getById("minecraft:" + data.getType().toString());
    }

    @Override
    public void setVariant(@NotNull VillagerVariant villagerVariant) {
        var type = Registry.VILLAGER_TYPE.get(new ResourceLocation(villagerVariant.getKey().toString()));
        getMinecraftEntity().setVillagerData(getMinecraftEntity().getVillagerData().setType(type));
    }

    @Override
    @NotNull
    public VillagerProfession getProfession() {
        var data = getMinecraftEntity().getVillagerData();
        return VillagerProfession.getById("minecraft:" + data.getProfession().toString());
    }

    @Override
    public void setProfession(@NotNull VillagerProfession villagerProfession) {
        var profession = Registry.VILLAGER_PROFESSION.get(Transformer.NAMESPACED_KEY.toMinecraft(villagerProfession.getKey()));
        getMinecraftEntity().setVillagerData(getMinecraftEntity().getVillagerData().setProfession(profession));
    }

    @Override
    public int getLevel() {
        return getMinecraftEntity().getVillagerData().getLevel();
    }

    @Override
    public void setLevel(int level) {
        getMinecraftEntity().setVillagerData(getMinecraftEntity().getVillagerData().setLevel(level));
    }

    @Override
    public void restock() {
        getMinecraftEntity().restock();
    }

    @Override
    public Component getDefaultName() {
        return Transformer.COMPONENT.toLoom(getMinecraftEntity().getTypeName());
    }
}

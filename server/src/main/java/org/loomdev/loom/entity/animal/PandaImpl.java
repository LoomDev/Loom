package org.loomdev.loom.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.animal.Panda;

public class PandaImpl extends AnimalImpl implements Panda {

    public PandaImpl(net.minecraft.world.entity.animal.Panda entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Panda> getType() {
        return EntityType.PANDA;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.Panda getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.Panda) super.getMinecraftEntity();
    }

    @Override
    public @NotNull Gene getMainGene() {
        return Gene.values()[getMinecraftEntity().getMainGene().getId()];
    }

    @Override
    public void setMainGene(@NotNull Gene gene) {
        getMinecraftEntity().setMainGene(net.minecraft.world.entity.animal.Panda.Gene.byId(gene.ordinal()));
    }

    @Override
    public @NotNull Gene getHiddenGene() {
        return Gene.values()[getMinecraftEntity().getHiddenGene().getId()];
    }

    @Override
    public void setHiddenGene(@NotNull Gene gene) {
        getMinecraftEntity().setHiddenGene(net.minecraft.world.entity.animal.Panda.Gene.byId(gene.ordinal()));
    }
}

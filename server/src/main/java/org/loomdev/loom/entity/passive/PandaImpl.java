package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.PandaEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Panda;

public class PandaImpl extends AnimalEntityImpl implements Panda {

    public PandaImpl(PandaEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PANDA;
    }

    @Override
    public @NotNull PandaEntity getMinecraftEntity() {
        return (PandaEntity) super.getMinecraftEntity();
    }

    @Override
    public @NotNull Gene getMainGene() {
        return Gene.values()[getMinecraftEntity().getMainGene().getId()];
    }

    @Override
    public void setMainGene(@NotNull Gene gene) {
        getMinecraftEntity().setMainGene(PandaEntity.Gene.byId(gene.ordinal()));
    }

    @Override
    public @NotNull Gene getHiddenGene() {
        return Gene.values()[getMinecraftEntity().getHiddenGene().getId()];
    }

    @Override
    public void setHiddenGene(@NotNull Gene gene) {
        getMinecraftEntity().setHiddenGene(PandaEntity.Gene.byId(gene.ordinal()));
    }
}

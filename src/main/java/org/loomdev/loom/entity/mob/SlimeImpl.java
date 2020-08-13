package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.SlimeEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Slime;

public class SlimeImpl extends MobEntityImpl implements Slime {

    public SlimeImpl(SlimeEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SLIME;
    }

    @Override
    public SlimeEntity getMinecraftEntity() {
        return (SlimeEntity) super.getMinecraftEntity();
    }

    @Override
    public int getSize() {
        return getMinecraftEntity().getSize();
    }

    @Override
    public void setSize(int size) {
        getMinecraftEntity().dataTracker.set(SlimeEntity.SLIME_SIZE, size);
    }
}

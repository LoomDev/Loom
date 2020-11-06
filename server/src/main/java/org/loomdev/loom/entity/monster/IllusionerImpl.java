package org.loomdev.loom.entity.monster;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Illusioner;

public class IllusionerImpl extends SpellcastingIllagerImpl implements Illusioner {

    public IllusionerImpl(net.minecraft.world.entity.monster.Illusioner entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ILLUSIONER;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.Illusioner getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Illusioner) super.getMinecraftEntity();
    }
}

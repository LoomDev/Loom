package org.loomdev.loom.entity.monster;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.monster.ElderGuardian;

public class ElderGuardianImpl extends GuardianImpl implements ElderGuardian {

    public ElderGuardianImpl(net.minecraft.world.entity.monster.ElderGuardian entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<ElderGuardian> getType() {
        return EntityType.ELDER_GUARDIAN;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.ElderGuardian getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.ElderGuardian) super.getMinecraftEntity();
    }
}

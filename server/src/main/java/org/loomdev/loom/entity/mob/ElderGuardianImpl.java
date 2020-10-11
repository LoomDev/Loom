package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.ElderGuardianEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.ElderGuardian;

public class ElderGuardianImpl extends GuardianImpl implements ElderGuardian {

    public ElderGuardianImpl(ElderGuardianEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ELDER_GUARDIAN;
    }

    @Override
    public @NotNull ElderGuardianEntity getMinecraftEntity() {
        return (ElderGuardianEntity) super.getMinecraftEntity();
    }
}

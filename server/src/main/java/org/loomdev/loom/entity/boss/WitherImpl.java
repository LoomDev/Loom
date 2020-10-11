package org.loomdev.loom.entity.boss;

import net.minecraft.entity.boss.WitherEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.bossbar.BossBar;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.boss.Wither;
import org.loomdev.loom.bossbar.BossBarImpl;
import org.loomdev.loom.entity.mob.HostileEntityImpl;

public class WitherImpl extends HostileEntityImpl implements Wither {

    public WitherImpl(WitherEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType<Wither> getType() {
        return EntityType.WITHER;
    }

    @Override
    public @NotNull WitherEntity getMinecraftEntity() {
        return (WitherEntity) super.getMinecraftEntity();
    }

    @Override
    public @NotNull BossBar getBossBar() {
        return BossBarImpl.ofMc(getMinecraftEntity().bossBar);
    }
}

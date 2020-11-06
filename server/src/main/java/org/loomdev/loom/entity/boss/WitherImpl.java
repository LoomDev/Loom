package org.loomdev.loom.entity.boss;

import net.minecraft.world.entity.boss.wither.WitherBoss;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.bossbar.BossBar;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.boss.Wither;
import org.loomdev.loom.bossbar.BossBarImpl;
import org.loomdev.loom.entity.monster.MonsterImpl;

public class WitherImpl extends MonsterImpl implements Wither {

    public WitherImpl(WitherBoss entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Wither> getType() {
        return EntityType.WITHER;
    }

    @Override
    @NotNull
    public WitherBoss getMinecraftEntity() {
        return (WitherBoss) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public BossBar getBossBar() {
        return BossBarImpl.of(getMinecraftEntity().bossEvent);
    }
}

package org.loomdev.loom.entity.player;

import net.minecraft.entity.player.PlayerEntity;
import org.loomdev.api.entity.player.Player;
import org.loomdev.loom.entity.LivingEntityImpl;

public class PlayerImpl extends LivingEntityImpl implements Player {
    public PlayerImpl(PlayerEntity entity) {
        super(entity);
    }
}

package org.loomdev.loom.entity.player;

import net.kyori.adventure.text.Component;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.MessageType;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import org.loomdev.api.entity.player.Player;
import org.loomdev.loom.entity.LivingEntityImpl;
import org.loomdev.loom.util.TextTransformer;

public class PlayerImpl extends LivingEntityImpl implements Player {
    public PlayerImpl(ServerPlayerEntity entity) {
        super(entity);
    }

    @Override
    public ServerPlayerEntity getMinecraftEntity() {
        return (ServerPlayerEntity) super.getMinecraftEntity();
    }

    @Override
    public void sendMessage(Component component) {
        Text message = TextTransformer.toMinecraft(component);
        getMinecraftEntity().networkHandler.sendPacket(new GameMessageS2CPacket(message, MessageType.CHAT, Util.NIL_UUID));
    }
}

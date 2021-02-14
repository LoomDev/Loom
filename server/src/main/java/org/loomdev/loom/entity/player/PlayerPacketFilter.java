package org.loomdev.loom.entity.player;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.server.level.ServerPlayer;

public final class PlayerPacketFilter {

    private PlayerPacketFilter() {
    }

    public static boolean shouldSend(ServerPlayer player, Packet<?> packet) {
        var loomPlayer = (PlayerImpl) player.getLoomEntity();

        return shouldSendWeatherPackets(loomPlayer, packet);
    }

    private static boolean shouldSendWeatherPackets(PlayerImpl loomPlayer, Packet<?> packet) {
        if (!(packet instanceof ClientboundGameEventPacket))
            return true;

        var gameEventPacket = (ClientboundGameEventPacket) packet;
        if (gameEventPacket.event != ClientboundGameEventPacket.START_RAINING
                && gameEventPacket.event != ClientboundGameEventPacket.STOP_RAINING
                && gameEventPacket.event != ClientboundGameEventPacket.RAIN_LEVEL_CHANGE
                && gameEventPacket.event != ClientboundGameEventPacket.THUNDER_LEVEL_CHANGE) {
            return true;
        }

        return loomPlayer.getWeather().isEmpty();
    }
}

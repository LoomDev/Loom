package org.loomdev.loom.entity.player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.minecraft.network.MessageType;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.player.Player;
import org.loomdev.loom.entity.LivingEntityImpl;
import org.loomdev.loom.math.MathHelp;
import org.loomdev.loom.util.transformer.TextTransformer;

public class PlayerImpl extends LivingEntityImpl implements Player {
    public PlayerImpl(ServerPlayerEntity entity) {
        super(entity);
    }

    @Override
    public ServerPlayerEntity getMinecraftEntity() {
        return (ServerPlayerEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean isSneaking() {
        return getMinecraftEntity().isSneaking();
    }

    @Override
    public void setSneaking(boolean flag) {
        getMinecraftEntity().setSneaking(flag);
    }

    @Override
    public boolean isSprinting() {
        return getMinecraftEntity().isSprinting();
    }

    @Override
    public void setSprinting(boolean flag) {
        getMinecraftEntity().setSprinting(flag);
    }

    @Override
    public float getWalkSpeed() {
        return getMinecraftEntity().abilities.walkSpeed * 2f;
    }

    @Override
    public void setWalkSpeed(float speed) {
        getMinecraftEntity().abilities.walkSpeed = MathHelp.clamp(speed, -1f, 1f);
        getMinecraftEntity().sendAbilitiesUpdate();
    }

    @Override
    public float getFlySpeed() {
        return getMinecraftEntity().abilities.flySpeed * 2f;
    }

    @Override
    public void setFlySpeed(float speed) {
        getMinecraftEntity().abilities.flySpeed = MathHelp.clamp(speed, -1f, 1f);
        getMinecraftEntity().sendAbilitiesUpdate();
    }

    @Override
    public void updateInventory() {
        getMinecraftEntity().openHandledScreen(getMinecraftEntity().currentScreenHandler);
    }

    @Override
    public void sendMessage(@NonNull String text) {
        this.sendMessage(TextComponent.of(text));
    }

    @Override
    public void sendMessage(@NonNull Component component) {
        Text message = TextTransformer.toMinecraft(component);
        getMinecraftEntity().networkHandler.sendPacket(new GameMessageS2CPacket(message, MessageType.CHAT, Util.NIL_UUID));
    }
}

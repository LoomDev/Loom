package org.loomdev.loom.entity.player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.minecraft.network.MessageType;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
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
    public void sendActionbar(String message) {
        sendActionbar(TextComponent.of(message));
    }

    @Override
    public void sendActionbar(Component message) {
        getMinecraftEntity().sendMessage(TextTransformer.toMinecraft(message), true);
    }

    @Override
    public void sendTitle(String title, String subtitle) {
        sendTitle(title, subtitle, 10, 70, 20);
    }

    @Override
    public void sendTitle(Component title, Component subtitle) {
        sendTitle(title, subtitle, 10, 70, 20);
    }

    @Override
    public void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        sendTitle(TextComponent.of(title), TextComponent.of(subtitle), fadeIn, stay, fadeOut);
    }

    @Override
    public void sendTitle(Component title, Component subtitle, int fadeIn, int stay, int fadeOut) {
        getMinecraftEntity().networkHandler.sendPacket(new TitleS2CPacket(TitleS2CPacket.Action.TIMES, null, fadeIn, stay, fadeOut));
        getMinecraftEntity().networkHandler.sendPacket(new TitleS2CPacket(TitleS2CPacket.Action.TITLE, TextTransformer.toMinecraft(title), 0, 0, 0));
        getMinecraftEntity().networkHandler.sendPacket(new TitleS2CPacket(TitleS2CPacket.Action.SUBTITLE, TextTransformer.toMinecraft(subtitle), 0, 0, 0));
    }

    @Override
    public void sendMessage(@NonNull String text) {
        this.sendMessage(TextComponent.of(text));
    }

    @Override
    public void sendMessage(@NonNull Component component) {
        getMinecraftEntity().sendMessage(TextTransformer.toMinecraft(component), false);
    }
}

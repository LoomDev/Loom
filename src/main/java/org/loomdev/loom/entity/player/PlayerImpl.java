package org.loomdev.loom.entity.player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.minecraft.network.packet.s2c.play.PlayerListHeaderS2CPacket;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.player.Player;
import org.loomdev.loom.entity.LivingEntityImpl;
import org.loomdev.loom.math.MathHelp;
import org.loomdev.loom.util.transformer.TextTransformer;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayerImpl extends LivingEntityImpl implements Player {

    private TextComponent playerListHeader = TextComponent.empty();
    private TextComponent playerListFooter = TextComponent.empty();

    private final Set<UUID> hiddenPlayers = new HashSet<>();

    public PlayerImpl(ServerPlayerEntity entity) {
        super(entity);
    }

    @Override
    public @NonNull EntityType getType() {
        return EntityType.PLAYER;
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
    public void sendActionbar(@NonNull String message) {
        sendActionbar(TextComponent.of(message));
    }

    @Override
    public void sendActionbar(@NonNull Component message) {
        getMinecraftEntity().sendMessage(TextTransformer.toMinecraft(message), true);
    }

    @Override
    public void sendMessage(@NonNull String text) {
        this.sendMessage(TextComponent.of(text));
    }

    @Override
    public void sendMessage(@NonNull Component component) {
        getMinecraftEntity().sendMessage(TextTransformer.toMinecraft(component), false);
    }

    @Override
    public void sendTitle(@NonNull String title, @NonNull String subtitle) {
        sendTitle(title, subtitle, 10, 70, 20);
    }

    @Override
    public void sendTitle(@NonNull Component title, @NonNull Component subtitle) {
        sendTitle(title, subtitle, 10, 70, 20);
    }

    @Override
    public void sendTitle(@NonNull String title, @NonNull String subtitle, int fadeIn, int stay, int fadeOut) {
        sendTitle(TextComponent.of(title), TextComponent.of(subtitle), fadeIn, stay, fadeOut);
    }

    @Override
    public void sendTitle(@NonNull Component title, @NonNull Component subtitle, int fadeIn, int stay, int fadeOut) {
        getMinecraftEntity().networkHandler.sendPacket(new TitleS2CPacket(TitleS2CPacket.Action.TIMES, null, fadeIn, stay, fadeOut));
        getMinecraftEntity().networkHandler.sendPacket(new TitleS2CPacket(TitleS2CPacket.Action.TITLE, TextTransformer.toMinecraft(title), 0, 0, 0));
        getMinecraftEntity().networkHandler.sendPacket(new TitleS2CPacket(TitleS2CPacket.Action.SUBTITLE, TextTransformer.toMinecraft(subtitle), 0, 0, 0));
    }

    @Override
    public void showPlayer(@NonNull Player player) {
        hiddenPlayers.remove(player.getUniqueId());
        // TODO register in entity tracker
    }

    @Override
    public void hidePlayer(@NonNull Player player) {
        hiddenPlayers.add(player.getUniqueId());
        // TODO unregister from entity tracker
    }

    @Override
    public boolean canSee(@NonNull Player player) {
        return !hiddenPlayers.contains(player.getUniqueId());
    }

    //@Override
    public void setPlayerListHeader(@NonNull TextComponent text) {
        this.playerListHeader = text;
        updatePlayerList();
    }

    //@Override
    public void setPlayerListFooter(@NonNull TextComponent text) {
        this.playerListFooter = text;
        updatePlayerList();
    }

    private void updatePlayerList() {
        PlayerListHeaderS2CPacket packet = new PlayerListHeaderS2CPacket();
        packet.header = TextTransformer.toMinecraft(this.playerListHeader);
        packet.footer = TextTransformer.toMinecraft(this.playerListFooter);
        getMinecraftEntity().networkHandler.sendPacket(packet);
    }
}

package org.loomdev.loom.entity.player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.minecraft.network.packet.s2c.play.PlayerListHeaderS2CPacket;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.damage.DamageSource;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.sound.Sound;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.Weather;
import org.loomdev.loom.entity.LivingEntityImpl;
import org.loomdev.loom.math.MathHelp;
import org.loomdev.loom.util.transformer.TextTransformer;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class PlayerImpl extends LivingEntityImpl implements Player {

    private Optional<TextComponent> tabListName = Optional.empty();
    private TextComponent tabListHeader = TextComponent.empty();
    private TextComponent tabListFooter = TextComponent.empty();

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
    public boolean isConnected() {
        return getMinecraftEntity().networkHandler != null;
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
    public int getViewDistance() {
        return getMinecraftEntity().viewDistance.orElse(Loom.getServer().getViewDistance());
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
    public void sendActionbar(@NonNull TextComponent message) {
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
    public void sendTitle(@NonNull TextComponent title, @NonNull TextComponent subtitle) {
        sendTitle(title, subtitle, 10, 70, 20);
    }

    @Override
    public void sendTitle(@NonNull String title, @NonNull String subtitle, int fadeIn, int stay, int fadeOut) {
        sendTitle(TextComponent.of(title), TextComponent.of(subtitle), fadeIn, stay, fadeOut);
    }

    @Override
    public void sendTitle(@NonNull TextComponent title, @NonNull TextComponent subtitle, int fadeIn, int stay, int fadeOut) {
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

    @Override
    public @NonNull Optional<InetSocketAddress> getAddress() {
        return Optional.ofNullable((InetSocketAddress) getMinecraftEntity().networkHandler.connection.getAddress());
    }

    @Override
    public int getProtocolVersion() {
        return Loom.getServer().getProtocolVersion(); // TODO
    }

    @Override
    public @NonNull Optional<TextComponent> getTabListName() {
        return Optional.of(this.tabListName.orElse(this.getDisplayName()));
    }

    @Override
    public void setTabListName(@NonNull TextComponent name) {
        this.tabListName = Optional.of(name);
    }

    @Override
    public @NonNull Optional<TextComponent> getTabListHeader() {
        return Optional.of(this.tabListHeader);
    }

    @Override
    public void setTabListHeader(@NonNull TextComponent header) {
        this.tabListHeader = header;
        updatePlayerList();
    }

    @Override
    public @NonNull Optional<TextComponent> getTabListFooter() {
        return Optional.empty();
    }

    @Override
    public void setTabListFooter(@NonNull TextComponent footer) {
        this.tabListFooter = footer;
        updatePlayerList();
    }

    @Override
    public @NonNull Optional<Location> getBedLocation() {
        return Optional.empty();
    }

    @Override
    public void setBedLocation(@NonNull Location location) {

    }

    @Override
    public @NonNull Optional<Location> getCompassTarget() {
        return Optional.empty();
    }

    @Override
    public void setCompassTarget(@NonNull Location location) {

    }

    @Override
    public @NonNull Optional<Entity> getSpectatorTarget() {
        return Optional.empty();
    }

    @Override
    public void setSpectatorTarget(@NonNull Entity entity) {

    }

    @Override
    public @NonNull Optional<Weather> getWeather() {
        return Optional.empty();
    }

    @Override
    public void setWeather(@NonNull Weather weather) {

    }

    @Override
    public void resetWeather() {

    }

    @Override
    public void kick(@NonNull String message) {
        if (isConnected()) {
            getMinecraftEntity().networkHandler.disconnect(new LiteralText(message));
        }
    }

    @Override
    public void kick(@NonNull TextComponent message) {
        if (isConnected()) {
            getMinecraftEntity().networkHandler.disconnect(TextTransformer.toMinecraft(message));
        }
    }

    @Override
    public boolean isOp() {
        return getMinecraftEntity().getServer().getPlayerManager().isOperator(getMinecraftEntity().getGameProfile());
    }

    @Override
    public void playSound(@NotNull Sound sound, @NotNull Location location) {
        BlockPos pos = new BlockPos(location.getX(), location.getY(), location.getZ());
        getMinecraftEntity().getServerWorld().playSound(
                getMinecraftEntity(),
                pos,
                Registry.SOUND_EVENT.get(sound.getType().rawId()),
                SoundCategory.valueOf(sound.getCategory().name()),
                sound.getVolume(),
                sound.getPitch()
        );
    }

    private void updatePlayerList() {
        PlayerListHeaderS2CPacket packet = new PlayerListHeaderS2CPacket();
        packet.header = TextTransformer.toMinecraft(this.tabListHeader);
        packet.footer = TextTransformer.toMinecraft(this.tabListFooter);
        getMinecraftEntity().networkHandler.sendPacket(packet);
    }
}

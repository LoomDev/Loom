package org.loomdev.loom.entity.player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesPacket;
import net.minecraft.network.protocol.game.ClientboundSoundEntityPacket;
import net.minecraft.network.protocol.game.ClientboundTabListPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.GameType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.Loom;
import org.loomdev.api.bossbar.BossBar;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.math.MathHelper;
import org.loomdev.api.sound.Sound;
import org.loomdev.api.util.GameMode;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.Weather;
import org.loomdev.loom.entity.LivingEntityImpl;
import org.loomdev.loom.util.transformer.TextTransformer;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class PlayerImpl extends LivingEntityImpl implements Player {

    private long timeOffset = 0;
    private boolean relativeTime = true;
    private Weather weather;

    private Component tabListName;
    private Component tabListHeader;
    private Component tabListFooter;

    private final Set<UUID> hiddenPlayers = new HashSet<>();

    public PlayerImpl(ServerPlayer entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Player> getType() {
        return EntityType.PLAYER;
    }

    @Override
    @NotNull
    public ServerPlayer getMinecraftEntity() {
        return (ServerPlayer) super.getMinecraftEntity();
    }

    @Override
    public boolean isConnected() {
        return getMinecraftEntity().connection != null;
    }

    @Override
    public boolean isCrouching() {
        return getMinecraftEntity().isCrouching();
    }

    @Override
    public void setCrouching(boolean crouching) {
        getMinecraftEntity().setPose(Pose.CROUCHING);
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
        return getMinecraftEntity().abilities.walkingSpeed * 2f;
    }

    @Override
    public void setWalkSpeed(float speed) {
        getMinecraftEntity().abilities.walkingSpeed = MathHelper.clamp(speed, -1f, 1f);
        getMinecraftEntity().onUpdateAbilities();
    }

    @Override
    public float getFlySpeed() {
        return getMinecraftEntity().abilities.flyingSpeed * 2f;
    }

    @Override
    public void setFlySpeed(float speed) {
        getMinecraftEntity().abilities.flyingSpeed = MathHelper.clamp(speed, -1f, 1f);
        getMinecraftEntity().onUpdateAbilities();
    }

    @Override
    public int getViewDistance() {
        return getMinecraftEntity().viewDistance.orElse(Loom.getServer().getViewDistance());
    }

    @Override
    public void updateInventory() {
        getMinecraftEntity().refreshContainer(getMinecraftEntity().containerMenu);
    }

    @Override
    public void sendActionbar(@NotNull String message) {
        sendActionbar(TextComponent.of(message));
    }

    @Override
    public void sendActionbar(@NotNull Component message) {
        getMinecraftEntity().sendMessage(TextTransformer.toMinecraft(message), ChatType.GAME_INFO, Util.NIL_UUID);
    }

    @Override
    public void sendMessage(@NotNull String text) {
        this.sendMessage(TextComponent.of(text));
    }

    @Override
    public void sendMessage(@NotNull Component component) {
        getMinecraftEntity().sendMessage(TextTransformer.toMinecraft(component), Util.NIL_UUID);
    }

    @Override
    public void sendTitle(@NotNull String title, @NotNull String subtitle) {
        sendTitle(title, subtitle, 10, 70, 20);
    }

    @Override
    public void sendTitle(@NotNull Component title, @NotNull Component subtitle) {
        sendTitle(title, subtitle, 10, 70, 20);
    }

    @Override
    public void sendTitle(@NotNull String title, @NotNull String subtitle, int fadeIn, int stay, int fadeOut) {
        sendTitle(TextComponent.of(title), TextComponent.of(subtitle), fadeIn, stay, fadeOut);
    }

    @Override
    public void sendTitle(@NotNull Component title, @NotNull Component subtitle, int fadeIn, int stay, int fadeOut) {
        getMinecraftEntity().connection.send(new ClientboundSetTitlesPacket(ClientboundSetTitlesPacket.Type.TIMES, null, fadeIn, stay, fadeOut));
        getMinecraftEntity().connection.send(new ClientboundSetTitlesPacket(ClientboundSetTitlesPacket.Type.TITLE, TextTransformer.toMinecraft(title), 0, 0, 0));
        getMinecraftEntity().connection.send(new ClientboundSetTitlesPacket(ClientboundSetTitlesPacket.Type.SUBTITLE, TextTransformer.toMinecraft(subtitle), 0, 0, 0));
    }

    @Override
    public void showPlayer(@NotNull Player player) {
        hiddenPlayers.remove(player.getUniqueId());
        // TODO register in entity tracker
    }

    @Override
    public void hidePlayer(@NotNull Player player) {
        hiddenPlayers.add(player.getUniqueId());
        // TODO unregister from entity tracker
    }

    @Override
    public boolean canSee(@NotNull Player player) {
        return !hiddenPlayers.contains(player.getUniqueId());
    }

    @Override
    @Nullable
    public InetSocketAddress getRemoteAddress() {
        if (!isConnected()) return null;
        return (InetSocketAddress) getMinecraftEntity().connection.connection.getRemoteAddress();
    }

    @Override
    public int getProtocolVersion() {
        return Loom.getServer().getProtocolVersion(); // TODO ??
    }

    @Override
    @Nullable
    public Component getTabListName() {
        return tabListName;
    }

    @Override
    public void setTabListName(@NotNull Component name) {
        this.tabListName = name;
    }

    @Override
    @NotNull
    public Component getTabListHeader() {
        return tabListHeader;
    }

    @Override
    public void setTabListHeader(@NotNull Component header) {
        this.tabListHeader = header;
        updatePlayerList();
    }

    @Override
    @Nullable
    public Component getTabListFooter() {
        return tabListFooter;
    }

    @Override
    public void setTabListFooter(@NotNull Component footer) {
        this.tabListFooter = footer;
        updatePlayerList();
    }

    @Override
    @Nullable
    public Location getBedLocation() {
        return null; // TODO
    }

    @Override
    public void setBedLocation(@NotNull Location location) {

    }

    @Override
    public @NotNull Optional<Location> getCompassTarget() {
        return Optional.empty();
    }

    @Override
    public void setCompassTarget(@NotNull Location location) {

    }

    @Override
    public @NotNull Optional<Entity> getSpectatorTarget() {
        return Optional.empty();
    }

    @Override
    public void setSpectatorTarget(@NotNull Entity entity) {

    }

    @Override
    public long getTime() {
        if (relativeTime) {
            return getWorld().getAbsoluteTime() + timeOffset;
        } else {
            return getWorld().getAbsoluteTime() - (getWorld().getAbsoluteTime() % 24000) + timeOffset;
        }
    }

    @Override
    public void setTime(long time, boolean relative) {
        this.timeOffset = time;
        this.relativeTime = relative;
    }

    @Override
    public long getTimeOffset() {
        return timeOffset;
    }

    @Override
    public boolean isTimeRelative() {
        return relativeTime;
    }

    @Override
    public void syncTime() {
        setTime(0, true);
    }

    @Override
    public @NotNull Optional<Weather> getWeather() {
        return Optional.ofNullable(weather);
    }

    @Override
    public void setWeather(@NotNull Weather weather) {
        if (weather == Weather.PRECIPITATION) {
            getMinecraftEntity().connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.STOP_RAINING, 0));
        } else {
            getMinecraftEntity().connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.START_RAINING, 0));
        }
    }

    @Override
    public void resetWeather() {

    }

    public void kick(@NotNull Component message) {
        if (!isConnected()) return;
        getMinecraftEntity().connection.disconnect(TextTransformer.toMinecraft(message));
    }

    @Override
    public void ban(@NotNull Component component) {
        // TODO
    }

    @Override
    public boolean isOp() {
        return getMinecraftEntity().getServer().getPlayerList().isOp(getMinecraftEntity().getGameProfile());
    }

    @Override
    public void playSound(@NotNull Sound sound) {
        getMinecraftEntity().connection.send(new ClientboundSoundEntityPacket(
                Registry.SOUND_EVENT.get(new ResourceLocation(sound.getSoundEffect().getKey().toString())),
                SoundSource.getByName(sound.getSoundCategory().getName()),
                getMinecraftEntity(),
                sound.getVolume(),
                sound.getPitch()
        ));
    }

    @Override
    public void addBossBar(@NotNull BossBar bossBar) {
        bossBar.addPlayer(this);
    }

    @Override
    public void removeBossBar(@NotNull BossBar bossBar) {
        bossBar.removePlayer(this);
    }

    @Override
    @NotNull
    public GameMode getGameMode() {
        return GameMode.values()[getMinecraftEntity().gameMode.getGameModeForPlayer().getId()];
    }

    @Override
    public void setGameMode(@NotNull GameMode gameMode) {
        getMinecraftEntity().setGameMode(GameType.byId(gameMode.ordinal()));
    }

    private void updatePlayerList() {
        var packet = new ClientboundTabListPacket();
        packet.header = TextTransformer.toMinecraft(this.tabListHeader);
        packet.footer = TextTransformer.toMinecraft(this.tabListFooter);
        getMinecraftEntity().connection.send(packet);
    }
}

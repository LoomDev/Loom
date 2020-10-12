package org.loomdev.loom.entity.player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerListHeaderS2CPacket;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
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
    private Component tabListHeader = TextComponent.empty();
    private Component tabListFooter = TextComponent.empty();

    private final Set<UUID> hiddenPlayers = new HashSet<>();

    public PlayerImpl(ServerPlayerEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PLAYER;
    }

    @Override
    public @NotNull ServerPlayerEntity getMinecraftEntity() {
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
        getMinecraftEntity().abilities.walkSpeed = MathHelper.clamp(speed, -1f, 1f);
        getMinecraftEntity().sendAbilitiesUpdate();
    }

    @Override
    public float getFlySpeed() {
        return getMinecraftEntity().abilities.flySpeed * 2f;
    }

    @Override
    public void setFlySpeed(float speed) {
        getMinecraftEntity().abilities.flySpeed = MathHelper.clamp(speed, -1f, 1f);
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
    public void sendActionbar(@NotNull String message) {
        sendActionbar(TextComponent.of(message));
    }

    @Override
    public void sendActionbar(@NotNull TextComponent message) {
        getMinecraftEntity().sendMessage(TextTransformer.toMinecraft(message), true);
    }

    @Override
    public void sendMessage(@NotNull String text) {
        this.sendMessage(TextComponent.of(text));
    }

    @Override
    public void sendMessage(@NotNull Component component) {
        getMinecraftEntity().sendMessage(TextTransformer.toMinecraft(component), false);
    }

    @Override
    public void sendTitle(@NotNull String title, @NotNull String subtitle) {
        sendTitle(title, subtitle, 10, 70, 20);
    }

    @Override
    public void sendTitle(@NotNull TextComponent title, @NotNull TextComponent subtitle) {
        sendTitle(title, subtitle, 10, 70, 20);
    }

    @Override
    public void sendTitle(@NotNull String title, @NotNull String subtitle, int fadeIn, int stay, int fadeOut) {
        sendTitle(TextComponent.of(title), TextComponent.of(subtitle), fadeIn, stay, fadeOut);
    }

    @Override
    public void sendTitle(@NotNull TextComponent title, @NotNull TextComponent subtitle, int fadeIn, int stay, int fadeOut) {
        getMinecraftEntity().networkHandler.sendPacket(new TitleS2CPacket(TitleS2CPacket.Action.TIMES, null, fadeIn, stay, fadeOut));
        getMinecraftEntity().networkHandler.sendPacket(new TitleS2CPacket(TitleS2CPacket.Action.TITLE, TextTransformer.toMinecraft(title), 0, 0, 0));
        getMinecraftEntity().networkHandler.sendPacket(new TitleS2CPacket(TitleS2CPacket.Action.SUBTITLE, TextTransformer.toMinecraft(subtitle), 0, 0, 0));
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
    public @NotNull Optional<InetSocketAddress> getAddress() {
        return Optional.ofNullable((InetSocketAddress) getMinecraftEntity().networkHandler.connection.getAddress());
    }

    @Override
    public int getProtocolVersion() {
        return Loom.getServer().getProtocolVersion();
    }

    @Override
    public @NotNull Component getTabListName() {
        return Optional.ofNullable(this.tabListName).orElse(this.getDisplayName());
    }

    @Override
    public void setTabListName(@NotNull Component name) {
        this.tabListName = name;
    }

    @Override
    public @NotNull Optional<Component> getTabListHeader() {
        return Optional.of(this.tabListHeader);
    }

    @Override
    public void setTabListHeader(@NotNull Component header) {
        this.tabListHeader = header;
        updatePlayerList();
    }

    @Override
    public @NotNull Optional<Component> getTabListFooter() {
        return Optional.empty();
    }

    @Override
    public void setTabListFooter(@NotNull Component footer) {
        this.tabListFooter = footer;
        updatePlayerList();
    }

    @Override
    public @NotNull Optional<Location> getBedLocation() {
        return Optional.empty();
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
            getMinecraftEntity().networkHandler.sendPacket(new GameStateChangeS2CPacket(GameStateChangeS2CPacket.RAIN_STOPPED, 0));
        } else {
            getMinecraftEntity().networkHandler.sendPacket(new GameStateChangeS2CPacket(GameStateChangeS2CPacket.RAIN_STARTED, 0));
        }
    }

    @Override
    public void resetWeather() {

    }

    public void kick(@NotNull Component message) {
        if (isConnected()) {
            getMinecraftEntity().networkHandler.disconnect(TextTransformer.toMinecraft(message));
        }
    }

    @Override
    public void ban(@NotNull Component component) {
        // TODO
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
                Registry.SOUND_EVENT.get(new Identifier(sound.getType().getKey().toString())),
                SoundCategory.valueOf(sound.getCategory().name()),
                sound.getVolume(),
                sound.getPitch()
        );
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
    public @NotNull GameMode getGameMode() {
        return GameMode.values()[getMinecraftEntity().interactionManager.getGameMode().getId()];
    }

    @Override
    public void setGameMode(@NotNull GameMode gameMode) {
        getMinecraftEntity().setGameMode(net.minecraft.world.GameMode.byId(gameMode.ordinal()));
    }

    private void updatePlayerList() {
        PlayerListHeaderS2CPacket packet = new PlayerListHeaderS2CPacket();
        packet.header = TextTransformer.toMinecraft(this.tabListHeader);
        packet.footer = TextTransformer.toMinecraft(this.tabListFooter);
        getMinecraftEntity().networkHandler.sendPacket(packet);
    }
}

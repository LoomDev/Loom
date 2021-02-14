package org.loomdev.loom.entity.player;

import net.kyori.adventure.text.Component;
import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.protocol.game.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.GameType;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.math.MathHelper;
import org.loomdev.api.sound.Sound;
import org.loomdev.api.util.GameMode;
import org.loomdev.api.util.Weather;
import org.loomdev.loom.entity.LivingEntityImpl;
import org.loomdev.loom.server.ServerImpl;
import org.loomdev.loom.util.transformer.TextTransformer;
import org.loomdev.loom.world.WorldImpl;

import java.net.InetSocketAddress;
import java.util.Optional;

public class PlayerImpl extends LivingEntityImpl implements Player {

    private Component tabListName;
    private Component tabListHeader;
    private Component tabListFooter;
    private Weather weather;

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
        return getMinecraftEntity().viewDistance.orElse(ServerImpl.getInstance().getViewDistance());
    }

    @Override
    public void updateInventory() {
        getMinecraftEntity().refreshContainer(getMinecraftEntity().containerMenu);
    }

    @Override
    public void sendActionBar(@NotNull String message) {
        sendActionBar(Component.text(message));
    }

    @Override
    public void sendActionBar(@NotNull Component message) {
        getMinecraftEntity().sendMessage(TextTransformer.toMinecraft(message), ChatType.GAME_INFO, Util.NIL_UUID);
    }

    @Override
    public void sendMessage(@NotNull String text) {
        this.sendMessage(Component.text(text));
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
        sendTitle(Component.text(title), Component.text(subtitle), fadeIn, stay, fadeOut);
    }

    @Override
    public void sendTitle(@NotNull Component title, @NotNull Component subtitle, int fadeIn, int stay, int fadeOut) {
        getMinecraftEntity().connection.send(new ClientboundSetTitlesPacket(ClientboundSetTitlesPacket.Type.TIMES, null, fadeIn, stay, fadeOut));
        getMinecraftEntity().connection.send(new ClientboundSetTitlesPacket(ClientboundSetTitlesPacket.Type.TITLE, TextTransformer.toMinecraft(title), 0, 0, 0));
        getMinecraftEntity().connection.send(new ClientboundSetTitlesPacket(ClientboundSetTitlesPacket.Type.SUBTITLE, TextTransformer.toMinecraft(subtitle), 0, 0, 0));
    }

    @Override
    @NotNull
    public Optional<InetSocketAddress> getRemoteAddress() {
        if (!isConnected()) {
            return Optional.empty();
        }

        return Optional.of(getMinecraftEntity().connection.connection.getRemoteAddress())
                .map(InetSocketAddress.class::cast);
    }

    @Override
    public int getProtocolVersion() {
        return ServerImpl.getInstance().getProtocolVersion(); // TODO ??
    }

    @Override
    @NotNull
    public Optional<Component> getTabListName() {
        return Optional.ofNullable(tabListName);
    }

    @Override
    public void setTabListName(@NotNull Component name) {
        tabListName = name;
        ClientboundPlayerInfoPacket updatePacket = new ClientboundPlayerInfoPacket(
                ClientboundPlayerInfoPacket.Action.UPDATE_DISPLAY_NAME,
                getMinecraftEntity());
        for (ServerPlayer player : getMinecraftEntity().level.getServer().getPlayerList().getPlayers()) {
            player.connection.send(updatePacket);
        }
    }

    @Override
    @NotNull
    public Optional<Component> getTabListHeader() {
        return Optional.ofNullable(tabListHeader);
    }

    @Override
    public void setTabListHeader(@NotNull Component header) {
        this.tabListHeader = header;
        updateTabList();
    }

    @Override
    @NotNull
    public Optional<Component> getTabListFooter() {
        return Optional.ofNullable(tabListHeader);
    }

    @Override
    public void setTabListFooter(@NotNull Component footer) {
        tabListFooter = footer;
        updateTabList();
    }

    private void updateTabList() {
        getMinecraftEntity().connection.send(new ClientboundTabListPacket(
                TextTransformer.toMinecraft(tabListHeader),
                TextTransformer.toMinecraft(tabListFooter)
        ));
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
    @NotNull
    public GameMode getGameMode() {
        return GameMode.values()[getMinecraftEntity().gameMode.getGameModeForPlayer().getId()];
    }

    @Override
    public void setGameMode(@NotNull GameMode gameMode) {
        getMinecraftEntity().setGameMode(GameType.byId(gameMode.ordinal()));
    }

    @Override
    public @NotNull Optional<Weather> getWeather() {
        return Optional.ofNullable(weather);
    }

    @Override
    public void setWeather(@NotNull Weather weather) {
        this.weather = weather;
        this.updateWeather();
    }

    @Override
    public void resetWeather() {
        this.weather = null;

        var world = getWorld();
        var level = ((WorldImpl) world).getMinecraftWorld();

        switch (world.getWeather()) {
            case CLEAR:
                getMinecraftEntity().connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.STOP_RAINING, 0f));
                getMinecraftEntity().connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.THUNDER_LEVEL_CHANGE, 0f));
                getMinecraftEntity().connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.RAIN_LEVEL_CHANGE, 0f));
                break;
            case THUNDER:
                getMinecraftEntity().connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.THUNDER_LEVEL_CHANGE, level.getThunderLevel(1f)));
            case RAIN:
                getMinecraftEntity().connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.START_RAINING, 0f));
                getMinecraftEntity().connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.RAIN_LEVEL_CHANGE, level.getRainLevel(1f)));
                break;
        }
    }

    private void updateWeather() {
        if (this.weather == null)
            return;

        switch (this.weather) {
            case CLEAR:
                getMinecraftEntity().connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.STOP_RAINING, 0f));
                getMinecraftEntity().connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.THUNDER_LEVEL_CHANGE, 0f));
                getMinecraftEntity().connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.RAIN_LEVEL_CHANGE, 0f));
                break;
            case THUNDER:
                getMinecraftEntity().connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.THUNDER_LEVEL_CHANGE, .5f)); // TODO? random level (between 0-1)?
            case RAIN:
                getMinecraftEntity().connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.START_RAINING, 0f));
                getMinecraftEntity().connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.RAIN_LEVEL_CHANGE, .5f)); // TODO? random level (between 0-1)?
                break;
        }
    }
}

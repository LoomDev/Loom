package org.loomdev.loom.bossbar;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.world.BossEvent;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.bossbar.BossBar;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.math.MathHelper;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.util.transformer.TextTransformer;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

public class BossBarImpl implements BossBar {

    private final ServerBossEvent mcBar;

    public BossBarImpl(@NotNull ServerBossEvent mcBar) {
        this.mcBar = mcBar;
    }

    @NotNull
    public ServerBossEvent getMinecraftBossBar() {
        return mcBar;
    }

    @Override
    @NotNull
    public UUID getUUID() {
        return mcBar.getId();
    }

    @Override
    @NotNull
    public Component getText() {
        return TextTransformer.toLoom(mcBar.getName());
    }

    @Override
    public void setText(@NotNull String text) {
        setText(TextComponent.of(text));
    }

    @Override
    public void setText(@NotNull Component text) {
        mcBar.setName(TextTransformer.toMinecraft(text));
    }

    @Override
    public float getPercent() {
        return mcBar.getProgress();
    }

    @Override
    public void setPercent(float percent) {
        mcBar.setProgress(percent);
    }

    @Override
    @NotNull
    public Color getColor() {
        return Color.valueOf(mcBar.getColor().name());
    }

    @Override
    public void setColor(@NotNull Color color) {
        mcBar.setColor(BossEvent.BossBarColor.valueOf(color.name()));
    }

    @Override
    @NotNull
    public Style getStyle() {
        return Style.valueOf(mcBar.getOverlay().name());
    }

    @Override
    public void setStyle(@NotNull Style style) {
        mcBar.setOverlay(BossEvent.BossBarOverlay.valueOf(style.name()));
    }

    @Override
    public boolean isVisible() {
        return mcBar.isVisible();
    }

    @Override
    public void setVisible(boolean visible) {
        mcBar.setVisible(visible);
    }

    @Override
    public boolean isDarkenScreen() {
        return mcBar.shouldDarkenScreen();
    }

    @Override
    public void setDarkenScreen(boolean darkenScreen) {
        mcBar.setDarkenScreen(darkenScreen);
    }

    @Override
    public boolean isThickenFog() {
        return mcBar.shouldCreateWorldFog();
    }

    @Override
    public void setThickenFog(boolean thickenFog) {
        mcBar.setCreateWorldFog(thickenFog);
    }

    @Override
    public boolean hasDragonMusic() { // TODO rename API method
        return mcBar.shouldPlayBossMusic();
    }

    @Override
    public void setDragonMusic(boolean dragonMusic) {
        mcBar.setPlayBossMusic(dragonMusic);
    }

    @Override
    @NotNull
    public Collection<? extends Player> getPlayers() {
        return mcBar.getPlayers()
                .stream()
                .map(e -> (PlayerImpl) e.getLoomEntity())
                .collect(Collectors.toList());
    }

    @Override
    public void addPlayer(@NotNull Player player) {
        mcBar.addPlayer(((PlayerImpl) player).getMinecraftEntity());
    }

    @Override
    public void removePlayer(@NotNull Player player) {
        mcBar.removePlayer(((PlayerImpl) player).getMinecraftEntity());
    }

    @Override
    public void removeAll() {
        getPlayers().forEach(this::removePlayer);
    }

    public static BossBar of(ServerBossEvent mcBossBar) {
        return new BossBarImpl(mcBossBar);
    }

    public static class BuilderImpl implements BossBar.Builder {

        private BossBar bossbar;

        public BuilderImpl() {
            this.bossbar = new BossBarImpl(new ServerBossEvent(
                    net.minecraft.network.chat.TextComponent.EMPTY,
                    BossEvent.BossBarColor.PURPLE,
                    BossEvent.BossBarOverlay.PROGRESS
            ));
        }

        @Override
        public BossBar.Builder text(@NotNull String text) {
            return text(Component.text(text));
        }

        @Override
        public BossBar.Builder text(@NotNull Component text) {
            bossbar.setText(text);
            return this;
        }

        @Override
        public BossBar.Builder percent(float percent) {
            bossbar.setPercent(MathHelper.clamp(percent, 0f, 1f));
            return this;
        }

        @Override
        public BossBar.Builder color(@NotNull Color color) {
            bossbar.setColor(color);
            return this;
        }

        @Override
        public BossBar.Builder style(@NotNull Style style) {
            bossbar.setStyle(style);
            return this;
        }

        @Override
        public BossBar.Builder visible(boolean visible) {
            bossbar.setVisible(visible);
            return this;
        }

        @Override
        public BossBar.Builder darkenScreen(boolean darkenSky) {
            bossbar.setDarkenScreen(darkenSky);
            return this;
        }

        @Override
        public BossBar.Builder thickenFog(boolean thickenFog) {
            bossbar.setThickenFog(thickenFog);
            return this;
        }

        @Override
        public BossBar.Builder dragonMusic(boolean dragonMusic) {
            bossbar.setDragonMusic(dragonMusic);
            return this;
        }

        @Override
        public BossBar.Builder addPlayer(@NotNull Player... players) {
            for (Player player : players) {
                bossbar.addPlayer(player);
            }
            return this;
        }

        @Override
        public BossBar.Builder removePlayer(@NotNull Player... players) {
            for (Player player : players) {
                bossbar.removePlayer(player);
            }
            return this;
        }

        @Override
        public BossBar.Builder clearPlayers() {
            bossbar.removeAll();
            return this;
        }

        @Override
        public BossBar.Builder from(BossBar bossbar) {
            this.bossbar = bossbar;
            return this;
        }

        @Override
        public BossBar build() {
            return bossbar;
        }
    }
}

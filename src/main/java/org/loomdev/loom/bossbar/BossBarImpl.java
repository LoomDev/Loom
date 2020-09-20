package org.loomdev.loom.bossbar;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.text.LiteralText;
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

    private final ServerBossBar mcBar;

    public BossBarImpl(@NotNull ServerBossBar mcBar) {
        this.mcBar = mcBar;
    }

    public @NotNull ServerBossBar getMinecraftBossBar() {
        return mcBar;
    }

    @Override
    public @NotNull UUID getUUID() {
        return mcBar.getUuid();
    }

    @Override
    public @NotNull Component getText() {
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
        return mcBar.getPercent();
    }

    @Override
    public void setPercent(float percent) {
        mcBar.setPercent(percent);
    }

    @Override
    public @NotNull Color getColor() {
        return Color.valueOf(mcBar.getColor().name());
    }

    @Override
    public void setColor(@NotNull Color color) {
        mcBar.setColor(net.minecraft.entity.boss.BossBar.Color.valueOf(color.name()));
    }

    @Override
    public @NotNull Style getStyle() {
        return Style.valueOf(mcBar.getOverlay().name());
    }

    @Override
    public void setStyle(@NotNull Style style) {
        mcBar.setOverlay(net.minecraft.entity.boss.BossBar.Style.valueOf(style.name()));
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
    public boolean isDarkenSky() {
        return mcBar.getDarkenSky();
    }

    @Override
    public void setDarkenSky(boolean darkenSky) {
        mcBar.setDarkenSky(darkenSky);
    }

    @Override
    public boolean isThickenFog() {
        return mcBar.getThickenFog();
    }

    @Override
    public void setThickenFog(boolean thickenFog) {
        mcBar.setThickenFog(thickenFog);
    }

    @Override
    public boolean hasDragonMusic() {
        return mcBar.hasDragonMusic();
    }

    @Override
    public void setDragonMusic(boolean dragonMusic) {
        mcBar.setDragonMusic(dragonMusic);
    }

    @Override
    public @NotNull Collection<? extends Player> getPlayers() {
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

    public static BossBar ofMc(ServerBossBar mcBossBar) {
        return new BossBarImpl(mcBossBar);
    }

    public static class BuilderImpl implements BossBar.Builder {

        private BossBar bossbar;

        public BuilderImpl() {
            this.bossbar = new BossBarImpl(new ServerBossBar(
                    LiteralText.EMPTY,
                    net.minecraft.entity.boss.BossBar.Color.PURPLE,
                    net.minecraft.entity.boss.BossBar.Style.PROGRESS
            ));
        }

        @Override
        public BossBar.Builder text(@NotNull String text) {
            return text(TextComponent.of(text));
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
        public BossBar.Builder darkenSky(boolean darkenSky) {
            bossbar.setDarkenSky(darkenSky);
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

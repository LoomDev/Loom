package org.loomdev.loom.bossbar;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.minecraft.entity.boss.ServerBossBar;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.bossbar.BossBar;
import org.loomdev.api.entity.player.Player;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.util.transformer.TextTransformer;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

public class BossBarImpl implements BossBar {

    private final ServerBossBar mcBar;

    public BossBarImpl(Component text) {
        this(text, Color.PURPLE, Style.PROGRESS);
    }

    public BossBarImpl(Component text, Color color, Style style) {
        this(new ServerBossBar(
                TextTransformer.toMinecraft(text),
                net.minecraft.entity.boss.BossBar.Color.valueOf(color.name()),
                net.minecraft.entity.boss.BossBar.Style.valueOf(style.name())
        ));
    }

    public BossBarImpl(ServerBossBar bar) {
        this.mcBar = bar;
    }

    @Override
    public @NotNull UUID getUUID() {
        return this.mcBar.getUuid();
    }

    @Override
    public @NotNull Component getText() {
        return TextTransformer.toLoom(this.mcBar.getName());
    }

    @Override
    public void setText(@NotNull String text) {
        this.setText(TextComponent.of(text));
    }

    @Override
    public void setText(@NotNull Component text) {
        this.mcBar.setName(TextTransformer.toMinecraft(text));
    }

    @Override
    public float getPercent() {
        return this.mcBar.getPercent();
    }

    @Override
    public void setPercent(float percent) {
        this.mcBar.setPercent(percent);
    }

    @Override
    public @NotNull Color getColor() {
        return Color.valueOf(this.mcBar.getColor().name());
    }

    @Override
    public void setColor(@NotNull Color color) {
        this.mcBar.setColor(net.minecraft.entity.boss.BossBar.Color.valueOf(color.name()));
    }

    @Override
    public @NotNull Style getStyle() {
        return Style.valueOf(this.mcBar.getOverlay().name());
    }

    @Override
    public void setStyle(@NotNull Style style) {
        this.mcBar.setOverlay(net.minecraft.entity.boss.BossBar.Style.valueOf(style.name()));
    }

    @Override
    public boolean isVisible() {
        return this.mcBar.isVisible();
    }

    @Override
    public void setVisible(boolean visible) {
        this.mcBar.setVisible(visible);
    }

    @Override
    public boolean isDarkenSky() {
        return this.mcBar.getDarkenSky();
    }

    @Override
    public void setDarkenSky(boolean darkenSky) {
        this.mcBar.setDarkenSky(darkenSky);
    }

    @Override
    public boolean isThickenFog() {
        return this.mcBar.getThickenFog();
    }

    @Override
    public void setThickenFog(boolean thickenFog) {
        this.mcBar.setThickenFog(thickenFog);
    }

    @Override
    public boolean hasDragonMusic() {
        return this.mcBar.hasDragonMusic();
    }

    @Override
    public void setDragonMusic(boolean dragonMusic) {
        this.mcBar.setDragonMusic(dragonMusic);
    }

    @Override
    public @NotNull Collection<? extends Player> getPlayers() {
        return this.mcBar.getPlayers()
                .stream()
                .map(e -> (PlayerImpl) e.getLoomEntity())
                .collect(Collectors.toList());
    }

    @Override
    public void addPlayer(@NotNull Player player) {
        this.mcBar.addPlayer(((PlayerImpl) player).getMinecraftEntity());
    }

    @Override
    public void removePlayer(@NotNull Player player) {
        this.mcBar.removePlayer(((PlayerImpl) player).getMinecraftEntity());
    }

    @Override
    public void removeAll() {
        this.getPlayers().forEach(this::removePlayer);
    }
}

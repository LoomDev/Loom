package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.TropicalFishEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.TropicalFish;
import org.loomdev.api.util.DyeColor;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class TropicalFishImpl extends SchoolingFishImpl implements TropicalFish {

    public TropicalFishImpl(TropicalFishEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.TROPICAL_FISH;
    }

    @Override
    public @NotNull TropicalFishEntity getMinecraftEntity() {
        return (TropicalFishEntity) super.getMinecraftEntity();
    }

    @Override
    public @NotNull Pattern getPattern() {
        return getPattern(getMinecraftEntity().getVariant());
    }

    @Override
    public void setPattern(@NotNull Pattern pattern) {
        getMinecraftEntity().setVariant(getData(getPatternColor(), getBaseColor(), pattern));
    }

    @Override
    public @NotNull DyeColor getBaseColor() {
        return getBaseColor(getMinecraftEntity().getVariant());
    }

    @Override
    public void setBaseColor(@NotNull DyeColor dyeColor) {
        getMinecraftEntity().setVariant(getData(getPatternColor(), dyeColor, getPattern()));
    }

    @Override
    public @NotNull DyeColor getPatternColor() {
        return getPatternColor(getMinecraftEntity().getVariant());
    }

    @Override
    public void setPatternColor(@NotNull DyeColor dyeColor) {
        getMinecraftEntity().setVariant(getData(dyeColor, getBaseColor(), getPattern()));
    }

    private static @NotNull DyeColor getBaseColor(int data) {
        return DyeColor.getById((byte) ((data >> 16) & 0xFF));
    }

    private static @NotNull DyeColor getPatternColor(int data) {
        return DyeColor.getById((byte) ((data >> 24) & 0xFF));
    }

    private static @NotNull Pattern getPattern(int data) {
        return VALUE_PATTERN_MAP.get(data & 0xFFFF);
    }

    private static int getData(DyeColor patternColor, DyeColor bodyColor, Pattern type) {
        TropicalFishEntity.Variety variety = TropicalFishEntity.Variety.values()[type.ordinal()];
        return patternColor.getId() << 24 | bodyColor.getId() << 16 | variety.getPattern() << 8 | variety.getShape();
    }

    private static final Map<Integer, Pattern> VALUE_PATTERN_MAP = Arrays.stream(TropicalFishEntity.Variety.values())
            .collect(Collectors.toMap(x -> (x.getShape() | x.getPattern() << 8 ), x -> Pattern.values()[x.ordinal()]));
}

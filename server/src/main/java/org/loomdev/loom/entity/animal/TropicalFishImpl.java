package org.loomdev.loom.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.TropicalFish;
import org.loomdev.api.util.DyeColor;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class TropicalFishImpl extends SchoolingFishImpl implements TropicalFish {

    public TropicalFishImpl(net.minecraft.world.entity.animal.TropicalFish entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<TropicalFish> getType() {
        return EntityType.TROPICAL_FISH;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.TropicalFish getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.TropicalFish) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public Pattern getPattern() {
        return getPattern(getMinecraftEntity().getVariant());
    }

    @Override
    public void setPattern(@NotNull Pattern pattern) {
        getMinecraftEntity().setVariant(getData(getPatternColor(), getBaseColor(), pattern));
    }

    @Override
    @NotNull
    public DyeColor getBaseColor() {
        return getBaseColor(getMinecraftEntity().getVariant());
    }

    @Override
    public void setBaseColor(@NotNull DyeColor dyeColor) {
        getMinecraftEntity().setVariant(getData(getPatternColor(), dyeColor, getPattern()));
    }

    @Override
    @NotNull
    public DyeColor getPatternColor() {
        return getPatternColor(getMinecraftEntity().getVariant());
    }

    @Override
    public void setPatternColor(@NotNull DyeColor dyeColor) {
        getMinecraftEntity().setVariant(getData(dyeColor, getBaseColor(), getPattern()));
    }

    @NotNull
    private static DyeColor getBaseColor(int data) {
        return DyeColor.getById((byte) ((data >> 16) & 0xFF));
    }

    @NotNull
    private static DyeColor getPatternColor(int data) {
        return DyeColor.getById((byte) ((data >> 24) & 0xFF));
    }

    @NotNull
    private static Pattern getPattern(int data) {
        return VALUE_PATTERN_MAP.get(data & 0xFFFF);
    }

    private static int getData(DyeColor patternColor, DyeColor bodyColor, Pattern type) {
        var variety = net.minecraft.world.entity.animal.TropicalFish.Pattern.values()[type.ordinal()];
        return patternColor.getId() << 24 | bodyColor.getId() << 16 | variety.getIndex() << 8 | variety.getBase(); // TODO i likely fucked this up
    }

    private static final Map<Integer, Pattern> VALUE_PATTERN_MAP = Arrays.stream(net.minecraft.world.entity.animal.TropicalFish.Pattern.values())
            .collect(Collectors.toMap(x -> (x.getBase() | x.getIndex() << 8 ), x -> Pattern.values()[x.ordinal()]));
}

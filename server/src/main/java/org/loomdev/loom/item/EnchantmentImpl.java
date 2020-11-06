package org.loomdev.loom.item;

import net.kyori.adventure.text.Component;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.item.Enchantment;
import org.loomdev.api.item.ItemStack;
import org.loomdev.loom.util.registry.GenericWrapped;
import org.loomdev.loom.util.transformer.TextTransformer;

public final class EnchantmentImpl extends GenericWrapped implements Enchantment {

    private final net.minecraft.world.item.enchantment.Enchantment mcEnchant;

    public EnchantmentImpl(String key) {
        super(key);
        this.mcEnchant = Registry.ENCHANTMENT.get(new ResourceLocation(key));
    }

    @Override
    public @NotNull Component getName(int level) {
        return TextTransformer.toLoom(mcEnchant.getFullname(level));
    }

    @Override
    public int getMinLevel() {
        return this.mcEnchant.getMinLevel();
    }

    @Override
    public int getMaxLevel() {
        return mcEnchant.getMaxLevel();
    }

    @Override
    public boolean isAcceptableItem(@NotNull ItemStack itemStack) {
        return mcEnchant.canEnchant(((ItemStackImpl) itemStack).getMinecraftItemStack());
    }

    @Override
    public boolean canCombineWith(@NotNull Enchantment enchantment) {
        return mcEnchant.isCompatibleWith(Registry.ENCHANTMENT.get(new ResourceLocation(enchantment.getKey().toString())));
    }

    @Override
    public boolean isCurse() {
        return mcEnchant.isCurse();
    }

    @Override
    public boolean isTraded() {
        return mcEnchant.isTradeable();
    }
}

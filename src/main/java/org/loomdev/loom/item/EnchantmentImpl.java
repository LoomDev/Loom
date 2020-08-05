package org.loomdev.loom.item;

import net.kyori.adventure.text.Component;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.item.Enchantment;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.loom.util.transformer.TextTransformer;

public final class EnchantmentImpl implements Enchantment {

    private final net.minecraft.enchantment.Enchantment mcEnchant;
    private final NamespacedKey namespacedKey;

    public EnchantmentImpl(net.minecraft.enchantment.Enchantment mcEnchant, String id) {
        this.mcEnchant = mcEnchant;
        this.namespacedKey = NamespacedKey.of(id);
    }

    @Override
    public @NotNull Component getName(int level) {
        return TextTransformer.toLoom(mcEnchant.getName(level));
    }

    @Override
    public int getMinLevel() {
        return this.mcEnchant.getMinLevel();
    }

    @Override
    public int getMaxLevel() {
        return this.mcEnchant.getMaxLevel();
    }

    @Override
    public boolean isAcceptableItem(@NotNull ItemStack itemStack) {
        return this.mcEnchant.isAcceptableItem(((ItemStackImpl) itemStack).getMinecraftItemStack());
    }

    @Override
    public boolean canCombineWith(@NotNull Enchantment enchantment) {
        return this.mcEnchant.canCombine(Registry.ENCHANTMENT.get(new Identifier(enchantment.getKey().toString())));
    }

    @Override
    public boolean isCurse() {
        return this.mcEnchant.isCursed();
    }

    @Override
    public boolean isTraded() {
        return this.mcEnchant.isAvailableForEnchantedBookOffer();
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return this.namespacedKey;
    }
}

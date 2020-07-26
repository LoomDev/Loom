package org.loomdev.loom.item.property.data;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.item.Enchantment;
import org.loomdev.api.item.property.data.EnchantmentData;

import java.util.Map;

public class EnchantmentDataImpl implements EnchantmentData {

    private Map<Enchantment, Integer> enchantments;

    public EnchantmentDataImpl(Map<Enchantment, Integer> enchantments) {
        this.enchantments = enchantments;
    }

    @Override
    public @NotNull Map<Enchantment, Integer> getEnchantments() {
        return this.enchantments;
    }

    @Override
    public void setEnchantments(@NotNull Map<Enchantment, Integer> enchantments) {
        this.enchantments = enchantments;
    }

    @Override
    public int getEnchantmentLevel(Enchantment enchantment) {
        return this.enchantments.getOrDefault(enchantment, 0);
    }

    @Override
    public void addEnchantment(@NotNull Enchantment enchantment, Integer level) {
        this.enchantments.put(enchantment, level);
    }

    @Override
    public void removeEnchantment(@NotNull Enchantment enchantment) {
        this.enchantments.remove(enchantment);
    }

    @Override
    public void clearEnchantments() {
        this.enchantments.clear();
    }

    @Override
    public boolean hasEnchantment(@NotNull Enchantment enchantment) {
        return this.enchantments.containsKey(enchantment);
    }
}

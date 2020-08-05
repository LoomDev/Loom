package org.loomdev.loom.item.property;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.loomdev.api.Loom;
import org.loomdev.api.item.Enchantment;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.property.ItemProperty;
import org.loomdev.api.item.property.data.EnchantmentData;
import org.loomdev.loom.item.ItemStackImpl;
import org.loomdev.loom.item.property.data.EnchantmentDataImpl;

import java.util.HashMap;
import java.util.Map;

public class EnchantmentsItemProperty implements ItemProperty<EnchantmentData> {

    @Override
    public EnchantmentData get(ItemStack itemStack) {
        net.minecraft.item.ItemStack mcStack = ((ItemStackImpl) itemStack).getMinecraftItemStack();
        ListTag listTag = mcStack.getEnchantments();
        Map<Enchantment, Integer> enchants = new HashMap<>();
        listTag.forEach(tag -> {
            CompoundTag ct = (CompoundTag) tag;
            Enchantment enchantment = Loom.getRegistry().getWrapped(Enchantment.class, ct.getString("id"));
            int level = ct.getShort("lvl");
            enchants.put(enchantment, level);
        });
        return new EnchantmentDataImpl(enchants);
    }

    @Override
    public void apply(ItemStack itemStack, EnchantmentData enchantmentData) {
        net.minecraft.item.ItemStack mcStack = ((ItemStackImpl) itemStack).getMinecraftItemStack();
        CompoundTag compoundTag = mcStack.getOrCreateTag();
        compoundTag.remove("Enchantments"); // remove all

        for (Map.Entry<Enchantment, Integer> enchantments : enchantmentData.getEnchantments().entrySet()) {
            net.minecraft.enchantment.Enchantment mcEnchantment = Registry.ENCHANTMENT.get(new Identifier(enchantments.getKey().getKey().getKey()));
            mcStack.addEnchantment(mcEnchantment, enchantments.getValue());
        }
    }

    @Override
    public boolean canApplyTo(ItemStack itemStack) {
        return true;
    }
}

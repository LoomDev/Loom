package org.loomdev.loom.item.property;

import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.item.Enchantment;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.ItemType;
import org.loomdev.api.item.property.ItemProperty;
import org.loomdev.api.item.property.data.EnchantmentData;
import org.loomdev.loom.item.ItemStackImpl;
import org.loomdev.loom.item.property.data.EnchantmentDataImpl;
import org.loomdev.loom.transformer.Transformer;

import java.util.HashMap;
import java.util.Map;

public class EnchantmentsItemProperty implements ItemProperty<EnchantmentData> {

    @Override
    public EnchantmentData get(@NotNull ItemStack itemStack) {
        var mcStack = ((ItemStackImpl) itemStack).getMinecraftItemStack();
        ListTag listTag = mcStack.getEnchantmentTags();
        if (itemStack.getType() == ItemType.ENCHANTED_BOOK) {
            listTag = EnchantedBookItem.getEnchantments(mcStack);
        }

        Map<Enchantment, Integer> enchants = new HashMap<>();
        listTag.forEach(tag -> {
            CompoundTag ct = (CompoundTag) tag;
            Enchantment enchantment = org.loomdev.api.util.registry.Registry.get().getWrapped(Enchantment.class, ct.getString("id"));
            int level = ct.getShort("lvl");
            enchants.put(enchantment, level);
        });
        return new EnchantmentDataImpl(enchants);
    }

    @Override
    public void apply(@NotNull ItemStack itemStack, @NotNull EnchantmentData enchantmentData) {
        var mcStack = ((ItemStackImpl) itemStack).getMinecraftItemStack();
        if (itemStack.getType() == ItemType.ENCHANTED_BOOK) {
            mcStack.removeTagKey("StoredEnchantments");
        } else {
            mcStack.removeTagKey("Enchantments");
        }

        for (Map.Entry<Enchantment, Integer> enchantments : enchantmentData.getEnchantments().entrySet()) {
            var mcEnchantment = Registry.ENCHANTMENT.get(Transformer.NAMESPACED_KEY.toMinecraft(enchantments.getKey().getKey()));

            if (itemStack.getType() == ItemType.ENCHANTED_BOOK) {
                EnchantedBookItem.addEnchantment(mcStack, new EnchantmentInstance(mcEnchantment, enchantments.getValue()));
            } else {
                mcStack.enchant(mcEnchantment, enchantments.getValue());
            }
        }
    }

    @Override
    public boolean canApplyTo(@NotNull ItemStack itemStack) {
        return true;
    }
}

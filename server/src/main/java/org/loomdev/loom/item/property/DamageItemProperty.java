package org.loomdev.loom.item.property;

import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.property.ItemProperty;
import org.loomdev.api.item.property.data.DamageData;
import org.loomdev.loom.item.ItemStackImpl;
import org.loomdev.loom.item.property.data.DamageDataImpl;

public class DamageItemProperty implements ItemProperty<DamageData> {

    @Override
    public DamageData get(@NotNull ItemStack itemStack) {
        net.minecraft.item.ItemStack mcStack = ((ItemStackImpl) itemStack).getMinecraftItemStack();
        CompoundTag tag = mcStack.getTag();
        boolean unbreakable = tag != null && tag.getBoolean("Unbreakable"); // Tag should never be null.
        return new DamageDataImpl(mcStack.getDamage(), mcStack.getMaxDamage(), unbreakable);
    }

    @Override
    public void apply(@NotNull ItemStack itemStack, @NotNull DamageData damageData) {
        net.minecraft.item.ItemStack mcStack = ((ItemStackImpl) itemStack).getMinecraftItemStack();
        CompoundTag compoundTag = mcStack.getOrCreateTag();

        mcStack.setDamage(damageData.getDamage());
        if (damageData.isUnbreakable()) {
            mcStack.getOrCreateTag().putBoolean("Unbreakable", true);
        } else if (compoundTag.contains("Unbreakable")) {
            compoundTag.remove("Unbreakable");
        }
    }

    @Override
    public boolean canApplyTo(@NotNull ItemStack itemStack) {
        return ((ItemStackImpl) itemStack).getMinecraftItemStack().getItem().isDamageable();
    }

}

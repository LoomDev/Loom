package org.loomdev.loom.item.property;

import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.property.ItemProperty;
import org.loomdev.api.item.property.data.DamageData;
import org.loomdev.loom.item.ItemStackImpl;
import org.loomdev.loom.item.property.data.DamageDataImpl;

public class DamageItemProperty implements ItemProperty<DamageData> {

    private static final String TAG_DAMAGE = "Damage";
    private static final String TAG_UNBREAKABLE = "Unbreakable";

    @Override
    public DamageData get(@NotNull ItemStack itemStack) {
        var mcStack = ((ItemStackImpl) itemStack).getMinecraftItemStack();
        CompoundTag tag = mcStack.getTag();
        boolean unbreakable = tag != null && tag.getBoolean(TAG_UNBREAKABLE); // Tag should never be null.
        return new DamageDataImpl(mcStack.getDamageValue(), mcStack.getMaxDamage(), unbreakable);
    }

    @Override
    public void apply(@NotNull ItemStack itemStack, @NotNull DamageData damageData) {
        var mcStack = ((ItemStackImpl) itemStack).getMinecraftItemStack();
        CompoundTag compoundTag = mcStack.getOrCreateTag();


        if (damageData.getDamage() > 0) {
            mcStack.setDamageValue(damageData.getDamage());
        } else {
            mcStack.removeTagKey(TAG_DAMAGE);
        }

        if (damageData.isUnbreakable()) {
            mcStack.getOrCreateTag().putBoolean(TAG_UNBREAKABLE, true);
        } else {
            mcStack.removeTagKey(TAG_UNBREAKABLE);
        }
    }

    @Override
    public boolean canApplyTo(@NotNull ItemStack itemStack) {
        return ((ItemStackImpl) itemStack).getMinecraftItemStack().getItem().canBeDepleted();
    }

}

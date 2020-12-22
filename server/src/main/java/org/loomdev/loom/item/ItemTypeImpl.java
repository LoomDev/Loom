package org.loomdev.loom.item;

import net.kyori.adventure.text.Component;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.ItemType;
import org.loomdev.loom.util.registry.GenericWrapped;
import org.loomdev.loom.util.transformer.TextTransformer;

public class ItemTypeImpl extends GenericWrapped implements ItemType {

    private final Item mcItem;

    public ItemTypeImpl(String key) {
        super(key);
        this.mcItem = Registry.ITEM.get(new ResourceLocation(key));
    }

    @NotNull
    public Item getMinecraftItem() {
        return mcItem;
    }

    @Override
    public int getMaxStackSize() {
        return mcItem.getMaxStackSize();
    }

    @Override
    public int getMaxDamage() {
        return mcItem.getMaxDamage();
    }

    @Override
    public boolean isDamageable() {
        return mcItem.canBeDepleted();
    }

    @Override
    @NotNull
    public String getTranslationKey() {
        return mcItem.getDescriptionId();
    }

    @Override
    @NotNull
    public String getTranslationKey(ItemStack itemStack) {
        return mcItem.getDescriptionId(((ItemStackImpl) itemStack).getMinecraftItemStack());
    }

    @Override
    public Component getName(ItemStack itemStack) {
        return TextTransformer.toLoom(this.mcItem.getName(((ItemStackImpl) itemStack).getMinecraftItemStack()));
    }

    @Override
    public ItemType getRecipeRemainder() {
        var resourceLocation = Registry.ITEM.getKey(mcItem.getCraftingRemainingItem());
        return Loom.getRegistry().getWrapped(ItemType.class, resourceLocation.toString());
    }

    @Override
    public boolean hasGlint(ItemStack itemStack) {
        return mcItem.isFoil(((ItemStackImpl) itemStack).getMinecraftItemStack());
    }

    @Override
    public boolean isEnchantable(ItemStack itemStack) {
        return mcItem.isEnchantable(((ItemStackImpl) itemStack).getMinecraftItemStack());
    }

    @Override
    public boolean isFood() {
        return mcItem.isEdible();
    }

    @Override
    public boolean isFireproof() {
        return mcItem.isFireResistant();
    }
}

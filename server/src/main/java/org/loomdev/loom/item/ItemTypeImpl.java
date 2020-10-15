package org.loomdev.loom.item;

import net.kyori.adventure.text.Component;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.ItemType;
import org.loomdev.loom.util.registry.GenericWrapped;
import org.loomdev.loom.util.transformer.TextTransformer;

public class ItemTypeImpl extends GenericWrapped implements ItemType {

    private final net.minecraft.item.Item mcItem;

    public ItemTypeImpl(String key) {
        super(key);
        this.mcItem = Registry.ITEM.get(new Identifier(key));
    }

    @Override
    public int getMaxStackSize() {
        return this.mcItem.getMaxCount();
    }

    @Override
    public int getMaxDamage() {
        return this.mcItem.getMaxDamage();
    }

    @Override
    public boolean isDamageable() {
        return this.mcItem.isDamageable();
    }

    @Override
    public @NotNull String getTranslationKey() {
        return this.mcItem.getTranslationKey();
    }

    @Override
    public @NotNull String getTranslationKey(ItemStack itemStack) {
        return this.mcItem.getTranslationKey(((ItemStackImpl) itemStack).getMinecraftItemStack());
    }

    @Override
    public Component getName(ItemStack itemStack) {
        return TextTransformer.toLoom(this.mcItem.getName(((ItemStackImpl) itemStack).getMinecraftItemStack()));
    }

    @Override
    public ItemType getRecipeRemainder() {
        Identifier identifier = Registry.ITEM.getId(this.mcItem.getRecipeRemainder());
        return Loom.getRegistry().getWrapped(ItemType.class, identifier.toString());
    }

    @Override
    public boolean hasGlint(ItemStack itemStack) {
        return this.mcItem.hasGlint(((ItemStackImpl) itemStack).getMinecraftItemStack());
    }

    @Override
    public boolean isEnchantable(ItemStack itemStack) {
        return this.mcItem.isEnchantable(((ItemStackImpl) itemStack).getMinecraftItemStack());
    }

    @Override
    public boolean isFood() {
        return this.mcItem.isFood();
    }

    @Override
    public boolean isFireproof() {
        return this.mcItem.isFireproof();
    }
}

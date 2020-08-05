package org.loomdev.loom.item.property;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.property.ItemProperty;
import org.loomdev.api.item.property.data.NameData;
import org.loomdev.loom.item.ItemStackImpl;
import org.loomdev.loom.item.property.data.NameDataImpl;
import org.loomdev.loom.util.transformer.TextTransformer;

public class NameItemProperty implements ItemProperty<NameData> {

    @Override
    public NameData get(@NotNull ItemStack itemStack) {
        net.minecraft.item.ItemStack mcStack = ((ItemStackImpl) itemStack).getMinecraftItemStack();
        Component itemName = TextTransformer.toLoom(mcStack.getItem().getName(mcStack));
        Component customName = null;
        Component hoverText = TextTransformer.toLoom(mcStack.toHoverableText());

        CompoundTag displayTag = mcStack.getSubTag("display");
        if (displayTag != null && displayTag.contains("Name", 8)) {
            customName = GsonComponentSerializer.gson().deserialize(displayTag.getString("Name"));
        }

        return new NameDataImpl(itemName, customName, hoverText);
    }

    @Override
    public void apply(@NotNull ItemStack itemStack, @NotNull NameData nameData) {
        net.minecraft.item.ItemStack mcStack = ((ItemStackImpl) itemStack).getMinecraftItemStack();

        if (nameData.hasCustomName()) {
            mcStack.setCustomName(TextTransformer.toMinecraft(nameData.getName()));
        } else {
            mcStack.removeCustomName();
        }
    }

}

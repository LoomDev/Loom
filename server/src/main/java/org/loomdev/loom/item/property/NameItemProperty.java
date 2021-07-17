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
import org.loomdev.loom.transformer.Transformer;

public class NameItemProperty implements ItemProperty<NameData> {

    private static final String TAG_DISPLAY = "display";
    private static final String TAG_NAME = "Name";

    @Override
    public NameData get(@NotNull ItemStack itemStack) {
        var mcStack = ((ItemStackImpl) itemStack).getMinecraftItemStack();
        Component itemName = Transformer.COMPONENT.toLoom(mcStack.getItem().getName(mcStack));
        Component customName = null;
        Component hoverText = Transformer.COMPONENT.toLoom(mcStack.getHoverName());

        CompoundTag displayTag = mcStack.getTagElement(TAG_DISPLAY);
        if (displayTag != null && displayTag.contains(TAG_NAME, 8)) {
            customName = GsonComponentSerializer.gson().deserialize(displayTag.getString(TAG_NAME));
        }

        return new NameDataImpl(itemName, customName, hoverText);
    }

    @Override
    public void apply(@NotNull ItemStack itemStack, @NotNull NameData nameData) {
        var mcStack = ((ItemStackImpl) itemStack).getMinecraftItemStack();

        if (nameData.hasCustomName()) {
            mcStack.setHoverName(Transformer.COMPONENT.toMinecraft(nameData.getName()));
        } else {
            mcStack.resetHoverName();
        }
    }

}

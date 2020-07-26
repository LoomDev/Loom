package org.loomdev.loom.item.property;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.property.ItemProperty;
import org.loomdev.api.item.property.data.LoreData;
import org.loomdev.loom.item.ItemStackImpl;
import org.loomdev.loom.item.property.data.LoreDataImpl;

import java.util.ArrayList;
import java.util.List;

public class LoreItemProperty implements ItemProperty<LoreData> {

    @Override
    public LoreData get(@NotNull ItemStack itemStack) {
        net.minecraft.item.ItemStack mcStack = ((ItemStackImpl) itemStack).getMinecraftItemStack();

        CompoundTag compoundtag = mcStack.getSubTag("display");

        if (compoundtag != null && compoundtag.contains("Lore")) {
            ListTag listTag = compoundtag.getList("Lore", 8); // 8 == StringTag as type
            List<Component> lore = new ArrayList<>();

            for (int index = 0; index < listTag.size(); index++) {
                String line = listTag.getString(index);
                try {
                    lore.add(GsonComponentSerializer.gson().deserialize(line));
                } catch (Exception ex) {
                    // Ignore (stripped like Vanilla)
                }
            }

            return new LoreDataImpl(lore);
        }

        return new LoreDataImpl();
    }

    @Override
    public void apply(@NotNull ItemStack itemStack, @NotNull LoreData loreData) {
        net.minecraft.item.ItemStack mcStack = ((ItemStackImpl) itemStack).getMinecraftItemStack();

        CompoundTag compoundTag = mcStack.getOrCreateSubTag("display");
        if (loreData.getLore().isEmpty()) {
            compoundTag.remove("Lore");
        } else {
            ListTag listTag = new ListTag();
            for (Component component : loreData.getLore()) {
                listTag.add(StringTag.of(GsonComponentSerializer.gson().serialize(component)));
            }

            compoundTag.put("Lore", listTag);
        }
    }

}

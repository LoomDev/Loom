package org.loomdev.loom.transformer;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.minecraft.network.chat.TextComponent;
import org.jetbrains.annotations.NotNull;

public final class ComponentTransformer implements Transformer<net.minecraft.network.chat.Component, Component> {

    protected ComponentTransformer() {
    }

    @Override
    @NotNull
    public net.minecraft.network.chat.Component toMinecraft(@NotNull Component component) {
        var json = GsonComponentSerializer.gson().serialize(component);
        var minecraft = net.minecraft.network.chat.Component.Serializer.fromJson(json);

        return minecraft == null
                ? TextComponent.EMPTY
                : minecraft;
    }

    @Override
    @NotNull
    public Component toLoom(@NotNull net.minecraft.network.chat.Component minecraftObject) {
        var json = net.minecraft.network.chat.Component.Serializer.toJson(minecraftObject);
        return GsonComponentSerializer.gson().deserialize(json);
    }
}

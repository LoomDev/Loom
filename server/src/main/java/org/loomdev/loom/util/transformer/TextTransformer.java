package org.loomdev.loom.util.transformer;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.jetbrains.annotations.NotNull;

public final class TextTransformer {

    private TextTransformer() {
        throw new UnsupportedOperationException("TextTransformer shouldn't be initialized.");
    }

    public static net.minecraft.network.chat.Component toMinecraft(@NotNull Component component) {
        return net.minecraft.network.chat.Component.Serializer.fromJson(GsonComponentSerializer.gson().serialize(component));
    }

    public static Component toLoom(@NotNull net.minecraft.network.chat.Component text) {
        return GsonComponentSerializer.gson().deserialize(net.minecraft.network.chat.Component.Serializer.toJson(text));
    }
}

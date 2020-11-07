package org.loomdev.loom.util.transformer;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.minecraft.network.chat.TextComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class TextTransformer {

    private TextTransformer() {
        throw new UnsupportedOperationException("TextTransformer shouldn't be initialized.");
    }

    @NotNull
    public static net.minecraft.network.chat.Component toMinecraft(@Nullable Component component) {
        if (component == null) {
            return TextComponent.EMPTY;
        }

        var serialized = net.minecraft.network.chat.Component.Serializer.fromJson(GsonComponentSerializer.gson().serialize(component));
        if (serialized != null) {
            return serialized;
        }

        return TextComponent.EMPTY;
    }

    @NotNull
    public static Component toLoom(@NotNull net.minecraft.network.chat.Component text) {
        return GsonComponentSerializer.gson().deserialize(net.minecraft.network.chat.Component.Serializer.toJson(text));
    }
}

package org.loomdev.loom.util.transformer;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public final class TextTransformer {

    private TextTransformer() {
        throw new UnsupportedOperationException("TextTransformer shouldn't be initialized.");
    }

    public static Text toMinecraft(@NotNull Component component) {
        return Text.Serializer.fromJson(GsonComponentSerializer.gson().serialize(component));
    }

    public static Component toLoom(@NotNull Text text) {
        return GsonComponentSerializer.gson().deserialize(Text.Serializer.toJson(text));
    }
}

package org.loomdev.loom.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.minecraft.text.Text;
import org.checkerframework.checker.nullness.qual.NonNull;

public final class TextTransformer {
    private TextTransformer() { throw new UnsupportedOperationException("TextTransformer shouldn't be initialized."); }

    public static Text toMinecraft(@NonNull Component component) {
        return Text.Serializer.fromJson(GsonComponentSerializer.gson().serialize(component));
    }
    public static Component toKyori(@NonNull Text text) {
        return GsonComponentSerializer.gson().deserialize(Text.Serializer.toJson(text));
    }
}

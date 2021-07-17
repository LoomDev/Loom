package org.loomdev.loom.transformer;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.util.NamespacedKey;

public final class NamespacedKeyTransformer implements Transformer<ResourceLocation, NamespacedKey> {

    protected NamespacedKeyTransformer() {
    }

    @Override
    @NotNull
    public ResourceLocation toMinecraft(@NotNull NamespacedKey key) {
        return new ResourceLocation(key.getNamespace(), key.getKey());
    }

    @Override
    @NotNull
    public NamespacedKey toLoom(@NotNull ResourceLocation location) {
        return NamespacedKey.of(location.getNamespace(), location.getPath());
    }
}

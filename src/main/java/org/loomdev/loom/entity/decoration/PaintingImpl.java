package org.loomdev.loom.entity.decoration;

import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.decoration.Painting;
import org.loomdev.api.util.NamespacedKey;

public class PaintingImpl extends DecorationEntityImpl implements Painting {

    public PaintingImpl(PaintingEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PAINTING;
    }

    @Override
    public @NotNull PaintingEntity getMinecraftEntity() {
        return (PaintingEntity) super.getMinecraftEntity();
    }

    @Override
    public @NotNull Motive getMotive() {
        return Loom.getRegistry().getWrapped(Motive.class, Registry.PAINTING_MOTIVE.getId(getMinecraftEntity().motive).toString());
    }

    @Override
    public void setMotive(@NotNull Motive motive) {
        getMinecraftEntity().motive = Registry.PAINTING_MOTIVE.get(new Identifier(motive.getKey().toString()));
    }

    public static class MotiveImpl implements Motive {

        private PaintingMotive mcMotive;
        private NamespacedKey key;

        public MotiveImpl(PaintingMotive mcMotive, String key) {
            this.mcMotive = mcMotive;
            this.key = NamespacedKey.of(key);
        }

        @Override
        public int getWith() {
            return this.mcMotive.getWidth();
        }

        @Override
        public int getHeight() {
            return this.mcMotive.getHeight();
        }

        @Override
        public @NotNull NamespacedKey getKey() {
            return this.key;
        }
    }
}

package org.loomdev.loom.entity.decoration;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.decoration.Painting;
import org.loomdev.loom.util.registry.GenericWrapped;

public class PaintingImpl extends AbstractHangingEntityImpl implements Painting {

    public PaintingImpl(net.minecraft.world.entity.decoration.Painting entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Painting> getType() {
        return EntityType.PAINTING;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.decoration.Painting getMinecraftEntity() {
        return (net.minecraft.world.entity.decoration.Painting) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public Motive getMotive() {
        return org.loomdev.api.util.registry.Registry.get().getWrapped(Motive.class, Registry.MOTIVE.getKey(getMinecraftEntity().motive).toString());
    }

    @Override
    public void setMotive(@NotNull Motive motive) {
        getMinecraftEntity().motive = Registry.MOTIVE.get(new ResourceLocation(motive.getKey().toString()));
    }

    public static class MotiveImpl extends GenericWrapped implements Motive {

        private final net.minecraft.world.entity.decoration.Motive mcMotive;

        public MotiveImpl(String key) {
            super(key);
            this.mcMotive = Registry.MOTIVE.get(new ResourceLocation(key));
        }

        @Override
        public int getWidth() {
            return mcMotive.getWidth();
        }

        @Override
        public int getHeight() {
            return mcMotive.getHeight();
        }

    }
}

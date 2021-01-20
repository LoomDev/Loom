package org.loomdev.loom.container;

import net.kyori.adventure.text.Component;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.container.Container;
import org.loomdev.api.container.ContainerType;
import org.loomdev.api.entity.player.Player;
import org.loomdev.loom.entity.player.PlayerImpl;

public class ContainerImpl implements Container {

    private final AbstractContainerMenu container;

    public ContainerImpl(@NotNull AbstractContainerMenu container) {
        this.container = container;
    }

    @Override
    public int getSyncId() {
        return container.containerId;
    }

    @Override
    public void open(@NotNull Player player) {
        ((PlayerImpl) player).getMinecraftEntity().openMenu(new SimpleMenuProvider((id, inventory, player1) -> {
            return container;
        }, null));
    }

    @Override
    public void close(@NotNull Player player) {

    }

    public static class BuilderImpl implements Container.Builder {

        private Container container;

        @Override
        public Builder type(@NotNull ContainerType type) {
            var mcType = Registry.MENU.get(new ResourceLocation(type.getKey().toString()));
            container = new ContainerImpl(mcType.constructor.supply(100, null));
            return this;
        }

        @Override
        public Builder title(@NotNull Component title) {
            return null;
        }

        @Override
        public Builder from(Container value) {
            return null;
        }

        @Override
        public Container build() {
            return container;
        }
    }
}

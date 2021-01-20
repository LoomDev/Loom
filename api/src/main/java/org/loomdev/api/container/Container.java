package org.loomdev.api.container;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.util.builder.BuilderBase;

public interface Container {

    int getSyncId();

    void open(@NotNull Player player);

    void close(@NotNull Player player);

    static Builder builder() {
        return Loom.getRegistry().createBuilder(Container.class);
    }

    interface Builder extends BuilderBase<Container, Builder> {

        Builder type(@NotNull ContainerType type);

        Builder title(@NotNull Component title);
    }
}

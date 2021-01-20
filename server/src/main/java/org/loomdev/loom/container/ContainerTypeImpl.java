package org.loomdev.loom.container;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.container.ContainerType;
import org.loomdev.loom.util.registry.GenericWrapped;

public class ContainerTypeImpl extends GenericWrapped implements ContainerType {

    private final MenuType<?> menuType;

    public ContainerTypeImpl(@NotNull String key) {
        super(key);
        this.menuType = Registry.MENU.get(new ResourceLocation(key));
    }

    @NotNull
    public MenuType<?> getMinecraftType() {
        return menuType;
    }
}

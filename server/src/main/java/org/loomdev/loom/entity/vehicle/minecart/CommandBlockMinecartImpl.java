package org.loomdev.loom.entity.vehicle.minecart;

import net.kyori.adventure.text.Component;
import net.minecraft.world.entity.vehicle.MinecartCommandBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.vehicle.minecart.CommandBlockMinecart;
import org.loomdev.loom.transformer.Transformer;

import java.util.Optional;

public class CommandBlockMinecartImpl extends AbstractMinecartImpl implements CommandBlockMinecart {

    public CommandBlockMinecartImpl(MinecartCommandBlock entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<CommandBlockMinecart> getType() {
        return EntityType.COMMAND_BLOCK_MINECART;
    }

    @Override
    @NotNull
    public MinecartCommandBlock getMinecraftEntity() {
        return (MinecartCommandBlock) super.getMinecraftEntity();
    }

    @Override
    public String getCommand() {
        return getMinecraftEntity().getCommandBlock().getCommand();
    }

    @Override
    public void setCommand(String command) {
        getMinecraftEntity().getCommandBlock().setCommand(command);
    }

    @Override
    @NotNull
    public Optional<Component> getCustomName() {
        return Optional.ofNullable(getMinecraftEntity().getCommandBlock().getName())
                .map(Transformer.COMPONENT::toLoom);
    }

    @Override
    public void setCustomName(@Nullable Component component) {
        getMinecraftEntity().getCommandBlock().setName(component == null ? null : Transformer.COMPONENT.toMinecraft(component));
    }
}

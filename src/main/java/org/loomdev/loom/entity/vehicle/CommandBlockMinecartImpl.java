package org.loomdev.loom.entity.vehicle;

import net.kyori.adventure.text.Component;
import net.minecraft.entity.vehicle.CommandBlockMinecartEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.vehicle.CommandBlockMinecart;
import org.loomdev.loom.util.transformer.TextTransformer;

public class CommandBlockMinecartImpl extends MinecartImpl implements CommandBlockMinecart {

    public CommandBlockMinecartImpl(CommandBlockMinecartEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType<CommandBlockMinecart> getType() {
        return EntityType.COMMAND_BLOCK_MINECART;
    }

    @Override
    public @NotNull CommandBlockMinecartEntity getMinecraftEntity() {
        return (CommandBlockMinecartEntity) super.getMinecraftEntity();
    }

    @Override
    public String getCommand() {
        return getMinecraftEntity().getCommandExecutor().getCommand();
    }

    @Override
    public void setCommand(String command) {
        getMinecraftEntity().getCommandExecutor().setCommand(command);
    }

    @Override
    public @NotNull Component getCustomName() {
        return TextTransformer.toLoom(getMinecraftEntity().getCommandExecutor().getCustomName());
    }

    @Override
    public void setCustomName(@Nullable Component component) {
        getMinecraftEntity().getCommandExecutor().setCustomName(component == null ? null : TextTransformer.toMinecraft(component));
    }
}

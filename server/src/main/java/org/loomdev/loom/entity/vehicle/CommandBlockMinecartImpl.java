package org.loomdev.loom.entity.vehicle;

import net.kyori.adventure.text.Component;
import net.minecraft.world.entity.vehicle.MinecartCommandBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.vehicle.CommandBlockMinecart;
import org.loomdev.loom.util.transformer.TextTransformer;

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
    public @NotNull Component getCustomName() {
        return TextTransformer.toLoom(getMinecraftEntity().getCommandBlock().getName());
    }

    @Override
    public void setCustomName(@Nullable Component component) {
        getMinecraftEntity().getCommandBlock().setName(component == null ? null : TextTransformer.toMinecraft(component));
    }
}

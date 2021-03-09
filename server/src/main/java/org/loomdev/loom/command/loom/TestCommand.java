package org.loomdev.loom.command.loom;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandContext;
import org.loomdev.api.item.Enchantment;
import org.loomdev.api.item.property.ItemProperty;
import org.loomdev.api.util.EquipmentSlot;

public class TestCommand extends Command {

    public TestCommand() {
        super("test");
    }

    @Override
    public void execute(@NotNull CommandContext context) {
        var args = context.getArguments();
        var source = context.getSource();

        if (args.length != 1) {
            source.sendMessage("/test <damage/enchant>");
            return;
        }

        if (args[0].equalsIgnoreCase("damage")) {
            context.getSource().ifPlayer(player -> {
                var itemStack = player.getEquipment(EquipmentSlot.MAIN_HAND);
                var stack = itemStack.get();

                stack.changeProperty(ItemProperty.Damage, data -> {
                    data.setUnbreakable(!data.isUnbreakable());
                });
            });
        } else if (args[0].equalsIgnoreCase("enchant")) {
            context.getSource().ifPlayer(player -> {
                var itemStack = player.getEquipment(EquipmentSlot.MAIN_HAND);
                var stack = itemStack.get();

                stack.changeProperty(ItemProperty.Enchantments, data -> {
                    if (data.getEnchantments().size() > 0) {
                        data.clearEnchantments();
                    } else {
                        data.addEnchantment(Enchantment.UNBREAKING, 25);
                    }
                });
            });
        }

    }
}

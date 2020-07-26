package org.loomdev.loom.util.registry;

import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.item.Enchantment;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.property.ItemProperty;
import org.loomdev.api.item.property.data.*;
import org.loomdev.api.util.builder.BuilderBase;
import org.loomdev.api.util.registry.Registry;
import org.loomdev.loom.item.EnchantmentImpl;
import org.loomdev.loom.item.ItemStackImpl;
import org.loomdev.loom.item.property.DamageItemProperty;
import org.loomdev.loom.item.property.EnchantmentsItemProperty;
import org.loomdev.loom.item.property.LoreItemProperty;
import org.loomdev.loom.item.property.NameItemProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class RegistryImp implements Registry {

    private static final Logger LOGGER = LogManager.getLogger("Registry");

    private final Map<Class<?>, ItemProperty<?>> itemPropertyTypes = new HashMap<>();
    private final Map<Class<?>, Supplier<?>> builders = new HashMap<>();
    private final Map<String, Enchantment> enchantments = new HashMap<>();

    public RegistryImp() {
        // Item properties
        this.registerItemProperty(NameData.class, new NameItemProperty());
        this.registerItemProperty(LoreData.class, new LoreItemProperty());
        this.registerItemProperty(DamageData.class, new DamageItemProperty());
        this.registerItemProperty(EnchantmentData.class, new EnchantmentsItemProperty());

        // Builders
        this.registerBuilder(ItemStack.class, ItemStackImpl.BuilderImpl::new);

        // Enchantments
        registerAllFromMC(net.minecraft.util.registry.Registry.ENCHANTMENT, (id, mcEnchantment) ->
                this.enchantments.put(id.toString(), new EnchantmentImpl(id.toString(), mcEnchantment)));
    }

    @Override
    public <T extends ItemPropertyData> void registerItemProperty(@NotNull Class<T> dataType, @NotNull ItemProperty<T> itemProperty) {
        this.itemPropertyTypes.put(dataType, itemProperty);
    }

    @Override
    public <T extends ItemPropertyData> @Nullable ItemProperty<T>  getItemProperty(@NotNull Class<T> aClass) {
        if (!this.itemPropertyTypes.containsKey(aClass)) {
            LOGGER.error("No property registered for data type {}.", aClass.getCanonicalName());
            return null;
        }
        return (ItemProperty<T>) this.itemPropertyTypes.get(aClass);
    }

    @Override
    public <V, B extends BuilderBase<V, B>> @Nullable B createBuilder(@NotNull Class<V> aClass) {
        if (!this.builders.containsKey(aClass)) {
            LOGGER.error("No builder registered for {}.", aClass.getCanonicalName());
            return null;
        }

        try {
            return (B) this.builders.get(aClass).get();
        } catch (Exception e) {
            LOGGER.error("Could not create builder for {}.", aClass.getCanonicalName());
            return null;
        }
    }

    @Override
    public @Nullable Enchantment getEnchantment(String id) {
        return this.enchantments.get(id);
    }

    // region helpers

    public <V, B extends BuilderBase<V, B>> void registerBuilder(Class<V> aClass, Supplier<B> supplier) {
        this.builders.put(aClass, supplier);
    }

    private <T> void registerAllFromMC(net.minecraft.util.registry.Registry<T> registry, RegisterFunction<T> registerFunction) {
        registry.getIds().forEach(id -> registerFunction.register(id, registry.get(id)));
    }

    @FunctionalInterface
    private interface RegisterFunction<T> {
        void register(Identifier id, T value);
    }

    // endregion helpers
}

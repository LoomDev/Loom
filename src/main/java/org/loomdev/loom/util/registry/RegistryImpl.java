package org.loomdev.loom.util.registry;

import com.google.common.collect.Maps;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.bossbar.BossBar;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.SpawnGroup;
import org.loomdev.api.entity.decoration.Painting;
import org.loomdev.api.item.Enchantment;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.ItemType;
import org.loomdev.api.item.property.ItemProperty;
import org.loomdev.api.item.property.data.*;
import org.loomdev.api.util.builder.BuilderBase;
import org.loomdev.api.util.registry.Keyed;
import org.loomdev.api.util.registry.Registry;
import org.loomdev.api.village.VillagerProfession;
import org.loomdev.api.village.VillagerVariant;
import org.loomdev.api.world.biome.BiomeType;
import org.loomdev.api.world.poi.PointOfInterestType;
import org.loomdev.loom.block.BlockTypeImpl;
import org.loomdev.loom.bossbar.BossBarImpl;
import org.loomdev.loom.entity.EntityTypeImpl;
import org.loomdev.loom.entity.SpawnGroupImpl;
import org.loomdev.loom.entity.decoration.PaintingImpl;
import org.loomdev.loom.item.EnchantmentImpl;
import org.loomdev.loom.item.ItemStackImpl;
import org.loomdev.loom.item.ItemTypeImpl;
import org.loomdev.loom.item.property.DamageItemProperty;
import org.loomdev.loom.item.property.EnchantmentsItemProperty;
import org.loomdev.loom.item.property.LoreItemProperty;
import org.loomdev.loom.item.property.NameItemProperty;
import org.loomdev.loom.village.VillagerProfessionImpl;
import org.loomdev.loom.village.VillagerVariantImpl;
import org.loomdev.loom.world.biome.BiomeTypeImpl;
import org.loomdev.loom.world.poi.PointOfInterestTypeImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class RegistryImpl implements Registry {

    private static final Logger LOGGER = LogManager.getLogger("Registry");

    private final Map<Class<?>, Supplier<ItemProperty<? extends ItemPropertyData<?>>>> itemPropertySuppliers = Maps.newHashMap();

    private final Map<Class<?>, Supplier<?>> builders = new HashMap<>();

    private final Map<Class<? extends Keyed>, WrapperSupplier<? extends Keyed>> wrapperSuppliers = Maps.newHashMap();
    private final Map<Class<? extends Keyed>, Map<String, ? extends Keyed>> wrapperCache = Maps.newHashMap();

    public RegistryImpl() {
        initItemProperties();
        initBuilders();
        initMcRegistries();
    }

    private void initItemProperties() {
        this.registerItemProperty(NameData.class, NameItemProperty::new);
        this.registerItemProperty(LoreData.class, LoreItemProperty::new);
        this.registerItemProperty(DamageData.class, DamageItemProperty::new);
        this.registerItemProperty(EnchantmentData.class, EnchantmentsItemProperty::new);
    }

    private void initBuilders() {
        this.builders.put(ItemStack.class, ItemStackImpl.BuilderImpl::new);
        this.builders.put(BossBar.class, BossBarImpl.BuilderImpl::new);
    }

    private void initMcRegistries() {
        // MC Registries
        wrapperSuppliers.put(ItemType.class, ItemTypeImpl::new);
        wrapperSuppliers.put(Enchantment.class,EnchantmentImpl::new);
        wrapperSuppliers.put(BlockType.class, BlockTypeImpl::new);
        wrapperSuppliers.put(Painting.Motive.class, PaintingImpl.MotiveImpl::new);
        wrapperSuppliers.put(EntityType.class, EntityTypeImpl::new);
        wrapperSuppliers.put(BiomeType.class, BiomeTypeImpl::new);
        wrapperSuppliers.put(VillagerVariant.class, VillagerVariantImpl::new);
        wrapperSuppliers.put(VillagerProfession.class, VillagerProfessionImpl::new);
        wrapperSuppliers.put(PointOfInterestType.class, PointOfInterestTypeImpl::new);

        // MC Enums
        wrapperSuppliers.put(SpawnGroup.class, SpawnGroupImpl::new);
    }

    @Override
    public <T extends ItemPropertyData<T>> void registerItemProperty(@NotNull Class<T> type, @NotNull Supplier<ItemProperty<T>> supplier) {
        ((Map) this.itemPropertySuppliers).put(type, supplier);
    }

    @Override
    public <T extends ItemPropertyData<T>> @Nullable ItemProperty<T>  getItemProperty(@NotNull Class<T> type) {
        if (!this.itemPropertySuppliers.containsKey(type)) {
            LOGGER.error("No item property registered for data type {}.", type.getCanonicalName());
            return null;
        }
        return (ItemProperty<T>) this.itemPropertySuppliers.get(type).get();
    }

    @Override
    public <V, B extends BuilderBase<V, B>> @Nullable B createBuilder(@NotNull Class<V> type) {
        if (!this.builders.containsKey(type)) {
            LOGGER.error("No builder registered for {}.", type.getCanonicalName());
            return null;
        }

        try {
            return (B) this.builders.get(type).get();
        } catch (Exception e) {
            LOGGER.error("Could not create builder for {}.", type.getCanonicalName());
            return null;
        }
    }

    @Override
    public <T extends Keyed> @Nullable T getWrapped(Class<T> type, String key) {
        Map typeCache = wrapperCache.computeIfAbsent(type, k -> new HashMap<>());

        if (typeCache.containsKey(key)) {
            return (T) typeCache.get(key);
        }

        if (!wrapperSuppliers.containsKey(type)) {
            LOGGER.error("No wrapper registered for data type {}.", type.getCanonicalName());
            return null;
        }

        T wrappedObject = (T) wrapperSuppliers.get(type).get(key);
        typeCache.put(key, wrappedObject);
        return wrappedObject;
    }

    // region helpers

    @FunctionalInterface
    private interface RegisterFunction<T> {
        void register(Identifier id, T value);
    }

    // endregion helpers
}

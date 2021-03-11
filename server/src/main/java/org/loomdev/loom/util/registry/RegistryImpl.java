package org.loomdev.loom.util.registry;

import com.google.common.collect.Maps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.bossbar.BossBar;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.SpawnGroup;
import org.loomdev.api.entity.decoration.Painting;
import org.loomdev.api.entity.effect.StatusEffectType;
import org.loomdev.api.item.Enchantment;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.ItemType;
import org.loomdev.api.item.property.ItemProperty;
import org.loomdev.api.item.property.data.*;
import org.loomdev.api.particle.ParticleType;
import org.loomdev.api.sound.SoundSource;
import org.loomdev.api.sound.SoundEvent;
import org.loomdev.api.tag.Tag;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.api.util.builder.BuilderBase;
import org.loomdev.api.util.registry.Keyed;
import org.loomdev.api.util.registry.Registry;
import org.loomdev.api.village.VillagerProfession;
import org.loomdev.api.village.VillagerVariant;
import org.loomdev.api.world.biome.BiomeType;
import org.loomdev.api.world.event.GameEventType;
import org.loomdev.api.world.poi.PointOfInterestType;
import org.loomdev.loom.block.BlockTypeImpl;
import org.loomdev.loom.bossbar.BossBarImpl;
import org.loomdev.loom.entity.EntityTypeImpl;
import org.loomdev.loom.entity.SpawnGroupImpl;
import org.loomdev.loom.entity.decoration.PaintingImpl;
import org.loomdev.loom.entity.effect.StatusEffectTypeImpl;
import org.loomdev.loom.item.EnchantmentImpl;
import org.loomdev.loom.item.ItemStackImpl;
import org.loomdev.loom.item.ItemTypeImpl;
import org.loomdev.loom.item.property.*;
import org.loomdev.loom.particle.ParticleTypeImpl;
import org.loomdev.loom.sound.SoundSourceImpl;
import org.loomdev.loom.sound.SoundEventImpl;
import org.loomdev.loom.tag.BlockTagImpl;
import org.loomdev.loom.tag.EntityTypeTagImpl;
import org.loomdev.loom.tag.GameEventTagImpl;
import org.loomdev.loom.tag.ItemTagImpl;
import org.loomdev.loom.village.VillagerProfessionImpl;
import org.loomdev.loom.village.VillagerVariantImpl;
import org.loomdev.loom.world.biome.BiomeTypeImpl;
import org.loomdev.loom.world.event.GameEventTypeImpl;
import org.loomdev.loom.world.poi.PointOfInterestTypeImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class RegistryImpl extends Registry {

    private static final Logger LOGGER = LogManager.getLogger("Registry");

    private final Map<Class<?>, Supplier<ItemProperty<? extends ItemPropertyData<?>>>> itemPropertySuppliers = Maps.newHashMap();

    private final Map<Class<?>, Supplier<?>> builders = new HashMap<>();

    private final Map<Class<? extends Keyed>, RegistrySupplier<Tag<? extends Keyed>, NamespacedKey>> tagSuppliers = Maps.newHashMap();

    private final Map<Class<? extends Keyed>, WrapperSupplier<? extends Keyed>> wrapperSuppliers = Maps.newHashMap();
    private final Map<Class<? extends Keyed>, Map<String, ? extends Keyed>> wrapperCache = Maps.newHashMap();

    public RegistryImpl() {
        Registry.setRegistry(this);

        initItemProperties();
        initBuilders();
        initMcRegistries();
        initTags();
    }

    private void initItemProperties() {
        this.registerItemProperty(NameData.class, NameItemProperty::new);
        this.registerItemProperty(LoreData.class, LoreItemProperty::new);
        this.registerItemProperty(DamageData.class, DamageItemProperty::new);
        this.registerItemProperty(EnchantmentData.class, EnchantmentsItemProperty::new);
        this.registerItemProperty(RepairableData.class, RepairableItemProperty::new);
        this.registerItemProperty(LeatherDyeData.class, LeatherDyeProperty::new);
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
        wrapperSuppliers.put(StatusEffectType.class, StatusEffectTypeImpl::new);
        wrapperSuppliers.put(ParticleType.class, ParticleTypeImpl::new);
        wrapperSuppliers.put(SoundEvent.class, SoundEventImpl::new);
        wrapperSuppliers.put(GameEventType.class, GameEventTypeImpl::new);

        // MC Enums
        wrapperSuppliers.put(SpawnGroup.class, SpawnGroupImpl::new);
        wrapperSuppliers.put(SoundSource.class, SoundSourceImpl::new);
    }

    private void initTags() {
        tagSuppliers.put(BlockType.class, BlockTagImpl::new);
        tagSuppliers.put(ItemType.class, ItemTagImpl::new);
        tagSuppliers.put(EntityType.class, EntityTypeTagImpl::new);
        tagSuppliers.put(GameEventType.class, GameEventTagImpl::new);
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
    @NotNull
    public <T extends Tag<? extends Keyed>> Optional<T> getTag(@NotNull Class<? extends Keyed> type, @NotNull NamespacedKey key) {
        var supplier = tagSuppliers.get(type);

        if (supplier == null) {
            return Optional.empty();
        }

        try {
            return (Optional<T>) Optional.of(supplier.get(key));
        } catch (Exception e) {
            LOGGER.warn("Could not get Tag {} of type {}.", key.toString(), type.getSimpleName());
            return Optional.empty();
        }
    }

    @Override
    public <T extends Keyed> @Nullable T getWrapped(@NotNull Class<T> type, @NotNull String key) {
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
}

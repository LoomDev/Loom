package org.loomdev.api.item;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.util.registry.Keyed;

public interface Enchantment extends Keyed {

    // region Enchantments

    /**
     * Increases underwater mining speed.
     */
    Enchantment AQUA_AFFINITY = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:aqua_affinity");

    /**
     * Increases damage and applies Slowness IV to arthropod mobs (spiders, cave spiders, silverfish, endermites and bees).
     */
    Enchantment BANE_OF_ARTHROPODS = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:bane_of_arthropods");

    /**
     * Reduces explosion damage and knockback.
     */
    Enchantment BLAST_PROTECTION = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:blast_protection");

    /**
     * Trident "channels" a bolt of lightning toward a hit entity.
     * Functions only during thunderstorms and if target is unobstructed with opaque blocks.
     */
    Enchantment CHANNELING = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:channeling");

    /**
     * Except when in creative mode, items cannot be removed from armor slots except due to death or breaking.
     */
    Enchantment CURSE_OF_BINDING = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:binding_curse");

    /**
     * Item destroyed on death.
     */
    Enchantment CURSE_OF_VANISHING = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:vanishing_curse");

    /**
     * Increases underwater movement speed.
     */
    Enchantment DEPTH_STRIDER = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:depth_strider");

    /**
     * Increases mining speed.
     * When applied to an axe it increases the chance that the axe may stun a shield,
     * with the base chance being 25% and a 5% increase for each level of efficiency.
     */
    Enchantment EFFICIENCY = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:efficiency");

    /**
     * Reduces fall damage.
     */
    Enchantment FEATHER_FALLING = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:feather_falling");

    /**
     * Sets target on fire.
     */
    Enchantment FIRE_ASPECT = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:fire_aspect");

    /**
     * Reduces fire damage and burn time.
     */
    Enchantment FIRE_PROTECTION = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:fire_protection");

    /**
     * Arrows set target on fire.
     */
    Enchantment FLAME = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:flame");

    /**
     * Increases certain block drops.
     */
    Enchantment FORTUNE = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:fortune");

    /**
     * Turns water beneath the player into frosted ice and prevents
     * the damage the player would take from standing on magma blocks.
     */
    Enchantment FROST_WALKER = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:frost_walker");

    /**
     * Trident deals additional damage to mobs that spawn naturally in the ocean.
     */
    Enchantment IMPALING = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:impaling");

    /**
     * Shooting consumes no regular arrows.
     */
    Enchantment INFINITY = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:infinity");

    /**
     * Increases knockback.
     */
    Enchantment KNOCKBACK = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:knockback");

    /**
     * Increases mob loot.
     */
    Enchantment LOOTING = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:looting");

    /**
     * Trident returns after being thrown. Higher levels reduce return time.
     */
    Enchantment LOYALTY = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:loyalty");

    /**
     * Increases rate of good loot (enchanting books, etc.)
     */
    Enchantment LUCK_OF_THE_SEA = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:luck_of_the_sea");

    /**
     * Decreases wait time until fish/junk/loot "bites".
     */
    Enchantment LURE = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:lure");

    /**
     * Repair the item while gaining XP orbs.
     */
    Enchantment MENDING = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:mending");

    /**
     * Shoot 3 arrows at the cost of one; only one arrow can be recovered.
     */
    Enchantment MULTISHOT = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:multishot");

    /**
     * Arrows pass through multiple entities.
     */
    Enchantment PIERCING = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:piercing");

    /**
     * Increases arrow damage.
     */
    Enchantment POWER = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:power");

    /**
     * Reduces projectile damage.
     */
    Enchantment PROJECTILE_PROTECTION = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:projectile_protection");

    /**
     * Reduces most types of damage by 4% for each level.
     */
    Enchantment PROTECTION = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:protection");

    /**
     * Increases arrow knockback.
     */
    Enchantment PUNCH = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:punch");

    /**
     * Decreases crossbow charging time.
     */
    Enchantment QUICK_CHARGE = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:quick_charge");

    /**
     * Extends underwater breathing time.
     */
    Enchantment RESPIRATION = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:respiration");

    /**
     * Trident launches player with itself when thrown. Functions only in water or rain.
     */
    Enchantment RIPTIDE = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:riptide");

    /**
     * Increases damage.
     */
    Enchantment SHARPNESS = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:sharpness");

    /**
     * Mined blocks drop themselves.
     */
    Enchantment SILK_TOUCH = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:silk_touch");

    /**
     * Increases damage to undead mobs.
     */
    Enchantment SMITE = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:smite");

    /**
     * Increases walking speed on soul sand and soul soil.
     */
    Enchantment SOUL_SPEED = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:soul_speed");

    /**
     * Increases sweeping attack damage.
     */
    Enchantment SWEEPING_EDGE = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:sweeping");

    /**
     * Reflects some of the damage taken when hit, at the cost of reducing durability with each proc.
     */
    Enchantment THORNS = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:thorns");

    /**
     * Increases item durability.
     */
    Enchantment UNBREAKING = Loom.getRegistry().getWrapped(Enchantment.class,"minecraft:unbreaking");

    // endregion Enchantments

    /**
     * Get an enchantment based on the id.
     * @param id The if of the enchantment to get.
     * @return The enchantment if found, otherwise null.
     */
    static Enchantment getById(String id) {
        return Loom.getRegistry().getWrapped(Enchantment.class, id);
    }

    /**
     * Get the name of the enchantment.
     *
     * @param level The level to get the name for.
     * @return The name.
     */
    @NotNull Component getName(int level);

    /**
     * Get the minimum possible enchantment level of this enchantment.
     *
     * @return The minimum level.
     */
    int getMinLevel();

    /**
     * Get the maximum possible enchantment level of this enchantment.
     *
     * @return The maximum level.
     */
    int getMaxLevel();

    /**
     * Check if this enchantment can by applied to an {@link ItemStack}.
     *
     * <p>
     *     Note: All enchantments can be applied to any item.
     *     This method returns whether the enchantment can occur on a specific
     *     {@link ItemStack} in vanilla Minecraft.
     * </p>
     *
     * @param itemStack The {@link ItemStack} to check against.
     * @return True if the enchantment can be applied.
     */
    boolean isAcceptableItem(@NotNull ItemStack itemStack);

    /**
     * Check if this enchantment can be combined with another enchantment.
     *
     * <p>
     *     Note: All enchantments can be combined in Loom.
     *     This method returns whether two enchantments can occur combined on the same
     *     {@link ItemStack} in vanilla Minecraft.
     * </p>
     *
     * @param enchantment The enchantment to check against.
     * @return True if the enchantments can be combined on the same {@link ItemStack}.
     */
    boolean canCombineWith(@NotNull Enchantment enchantment);

    /**
     * Checks if this {@link Enchantment} is a curse.
     *
     * <p>
     *     Example of a curse enchantment is {@link Enchantment#CURSE_OF_BINDING}
     * </p>
     *
     * @return True if the enchantment is a curse, otherwise false.
     */
    boolean isCurse();

    /**
     * Check if this {@link Enchantment} is traded by villagers.
     *
     * @return True if the enchantment is traded by villagers, otherwise false.
     */
    boolean isTraded();

    // attack amount
    // protection amount
    // rarity
}

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
    Enchantment AQUA_AFFINITY = getById("minecraft:aqua_affinity");

    /**
     * Increases damage and applies Slowness IV to arthropod mobs (spiders, cave spiders, silverfish, endermites and bees).
     */
    Enchantment BANE_OF_ARTHROPODS = getById("minecraft:bane_of_arthropods");

    /**
     * Reduces explosion damage and knockback.
     */
    Enchantment BLAST_PROTECTION = getById("minecraft:blast_protection");

    /**
     * Trident "channels" a bolt of lightning toward a hit entity.
     * Functions only during thunderstorms and if target is unobstructed with opaque blocks.
     */
    Enchantment CHANNELING = getById("minecraft:channeling");

    /**
     * Except when in creative mode, items cannot be removed from armor slots except due to death or breaking.
     */
    Enchantment BINDING_CURSE = getById("minecraft:binding_curse");

    /**
     * Item destroyed on death.
     */
    Enchantment VANISHING_CURSE = getById("minecraft:vanishing_curse");

    /**
     * Increases underwater movement speed.
     */
    Enchantment DEPTH_STRIDER = getById("minecraft:depth_strider");

    /**
     * Increases mining speed.
     * When applied to an axe it increases the chance that the axe may stun a shield,
     * with the base chance being 25% and a 5% increase for each level of efficiency.
     */
    Enchantment EFFICIENCY = getById("minecraft:efficiency");

    /**
     * Reduces fall damage.
     */
    Enchantment FEATHER_FALLING = getById("minecraft:feather_falling");

    /**
     * Sets target on fire.
     */
    Enchantment FIRE_ASPECT = getById("minecraft:fire_aspect");

    /**
     * Reduces fire damage and burn time.
     */
    Enchantment FIRE_PROTECTION = getById("minecraft:fire_protection");

    /**
     * Arrows set target on fire.
     */
    Enchantment FLAME = getById("minecraft:flame");

    /**
     * Increases certain block drops.
     */
    Enchantment FORTUNE = getById("minecraft:fortune");

    /**
     * Turns water beneath the player into frosted ice and prevents
     * the damage the player would take from standing on magma blocks.
     */
    Enchantment FROST_WALKER = getById("minecraft:frost_walker");

    /**
     * Trident deals additional damage to mobs that spawn naturally in the ocean.
     */
    Enchantment IMPALING = getById("minecraft:impaling");

    /**
     * Shooting consumes no regular arrows.
     */
    Enchantment INFINITY = getById("minecraft:infinity");

    /**
     * Increases knockback.
     */
    Enchantment KNOCKBACK = getById("minecraft:knockback");

    /**
     * Increases mob loot.
     */
    Enchantment LOOTING = getById("minecraft:looting");

    /**
     * Trident returns after being thrown. Higher levels reduce return time.
     */
    Enchantment LOYALTY = getById("minecraft:loyalty");

    /**
     * Increases rate of good loot (enchanting books, etc.)
     */
    Enchantment LUCK_OF_THE_SEA = getById("minecraft:luck_of_the_sea");

    /**
     * Decreases wait time until fish/junk/loot "bites".
     */
    Enchantment LURE = getById("minecraft:lure");

    /**
     * Repair the item while gaining XP orbs.
     */
    Enchantment MENDING = getById("minecraft:mending");

    /**
     * Shoot 3 arrows at the cost of one; only one arrow can be recovered.
     */
    Enchantment MULTISHOT = getById("minecraft:multishot");

    /**
     * Arrows pass through multiple entities.
     */
    Enchantment PIERCING = getById("minecraft:piercing");

    /**
     * Increases arrow damage.
     */
    Enchantment POWER = getById("minecraft:power");

    /**
     * Reduces projectile damage.
     */
    Enchantment PROJECTILE_PROTECTION = getById("minecraft:projectile_protection");

    /**
     * Reduces most types of damage by 4% for each level.
     */
    Enchantment PROTECTION = getById("minecraft:protection");

    /**
     * Increases arrow knockback.
     */
    Enchantment PUNCH = getById("minecraft:punch");

    /**
     * Decreases crossbow charging time.
     */
    Enchantment QUICK_CHARGE = getById("minecraft:quick_charge");

    /**
     * Extends underwater breathing time.
     */
    Enchantment RESPIRATION = getById("minecraft:respiration");

    /**
     * Trident launches player with itself when thrown. Functions only in water or rain.
     */
    Enchantment RIPTIDE = getById("minecraft:riptide");

    /**
     * Increases damage.
     */
    Enchantment SHARPNESS = getById("minecraft:sharpness");

    /**
     * Mined blocks drop themselves.
     */
    Enchantment SILK_TOUCH = getById("minecraft:silk_touch");

    /**
     * Increases damage to undead mobs.
     */
    Enchantment SMITE = getById("minecraft:smite");

    /**
     * Increases walking speed on soul sand and soul soil.
     */
    Enchantment SOUL_SPEED = getById("minecraft:soul_speed");

    /**
     * Increases sweeping attack damage.
     */
    Enchantment SWEEPING = getById("minecraft:sweeping");

    /**
     * Reflects some of the damage taken when hit, at the cost of reducing durability with each proc.
     */
    Enchantment THORNS = getById("minecraft:thorns");

    /**
     * Increases item durability.
     */
    Enchantment UNBREAKING = getById("minecraft:unbreaking");

    // endregion Enchantments

    /**
     * Gets an enchantment based on the id.
     * @param id The if of the enchantment to get.
     * @return The enchantment if found, otherwise null.
     */
    static Enchantment getById(String id) {
        return Loom.getRegistry().getWrapped(Enchantment.class, id);
    }

    /**
     * Gets the name of the enchantment.
     *
     * @param level The level to get the name for.
     * @return The name.
     */
    @NotNull Component getName(int level);

    /**
     * Gets the minimum possible enchantment level of this enchantment.
     *
     * @return The minimum level.
     */
    int getMinLevel();

    /**
     * Gets the maximum possible enchantment level of this enchantment.
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
     *     item stack in vanilla Minecraft.
     * </p>
     *
     * @param itemStack The {@link ItemStack} to check against.
     * @return {@code true} if the enchantment can be applied.
     */
    boolean isAcceptableItem(@NotNull ItemStack itemStack);

    /**
     * Check if this enchantment can be combined with another enchantment.
     *
     * <p>
     *     Note: All enchantments can be combined in Loom.
     *     This method returns whether two enchantments can occur combined on the same
     *     item stack in vanilla Minecraft.
     * </p>
     *
     * @param enchantment The enchantment to check against.
     * @return {@code true} if the enchantments can be combined on the same item stack.
     */
    boolean canCombineWith(@NotNull Enchantment enchantment);

    /**
     * Checks if the enchantment is a curse.
     *
     * <p>
     *     Example of a curse enchantment is {@link Enchantment#BINDING_CURSE}
     * </p>
     *
     * @return {@code true} if the enchantment is a curse, otherwise {@code false}.
     */
    boolean isCurse();

    /**
     * Check if the enchantment is traded by villagers.
     *
     * @return {@code true} if the enchantment is traded by villagers, otherwise {@code false}.
     */
    boolean isTraded();

    // attack amount
    // protection amount
    // rarity
}

package org.loomdev.api.village;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.item.ItemStack;

public interface TradeOffer {

    @NotNull
    ItemStack getFirstBuyItem();

    @NotNull
    ItemStack getAdjustedFirstBuyItem();

    void setFirstBuyItem(@NotNull ItemStack item);

    @Nullable
    ItemStack getSecondBuyItem();

    void setSecondBuyItem(@Nullable ItemStack item);

    @NotNull
    ItemStack getSellItem();

    void setSellItem(@NotNull ItemStack item);

    int getUses();

    void setUses(int uses);

    int getMaxUses();

    void setMaxUses(int maxUses);

    void resetUses();

    boolean isRewardsExperience();

    void setRewardsExperience(boolean flag);

    int getSpecialPrice();

    void setSpecialPrice(int price);

    int getDemandBonus();

    void setDemandBonus(int bonus);

    float getPriceMultiplier();

    void setPriceMultiplier(float multiplier);

    int getExperienceAwardedToVillager();

    void setExperienceAwardedToVillager(int exp);

}

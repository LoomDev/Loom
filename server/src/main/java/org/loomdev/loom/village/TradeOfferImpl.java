package org.loomdev.loom.village;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.village.TradeOffer;
import org.loomdev.loom.item.ItemStackImpl;

public class TradeOfferImpl implements TradeOffer {

    private final net.minecraft.village.TradeOffer mcTradeOffer;

    private TradeOfferImpl(net.minecraft.village.TradeOffer mcTradeOffer) {
        this.mcTradeOffer = mcTradeOffer;
    }

    @Override
    public @NotNull ItemStack getFirstBuyItem() {
        return ItemStackImpl.ofMcStack(this.mcTradeOffer.getOriginalFirstBuyItem());
    }

    @Override
    public @NotNull ItemStack getAdjustedFirstBuyItem() {
        return ItemStackImpl.ofMcStack(this.mcTradeOffer.getAdjustedFirstBuyItem());
    }

    @Override
    public void setFirstBuyItem(@NotNull ItemStack itemStack) {
        this.mcTradeOffer.firstBuyItem = ((ItemStackImpl) itemStack).getMinecraftItemStack();
    }

    @Override
    public @Nullable ItemStack getSecondBuyItem() {
        if (this.mcTradeOffer.getSecondBuyItem() == null) {
            return null;
        }
        return ItemStackImpl.ofMcStack(this.mcTradeOffer.getSecondBuyItem());
    }

    @Override
    public void setSecondBuyItem(@Nullable ItemStack itemStack) {
        this.mcTradeOffer.secondBuyItem = itemStack == null ? null : ((ItemStackImpl) itemStack).getMinecraftItemStack();
    }

    @Override
    public @NotNull ItemStack getSellItem() {
        return ItemStackImpl.ofMcStack(this.mcTradeOffer.getSellItem());
    }

    @Override
    public void setSellItem(@NotNull ItemStack itemStack) {
        this.mcTradeOffer.sellItem = ((ItemStackImpl) itemStack).getMinecraftItemStack();
    }

    @Override
    public int getUses() {
        return this.mcTradeOffer.getUses();
    }

    @Override
    public void setUses(int uses) {
        this.mcTradeOffer.uses = uses;
    }

    @Override
    public int getMaxUses() {
        return this.mcTradeOffer.getMaxUses();
    }

    @Override
    public void setMaxUses(int maxUses) {
        this.mcTradeOffer.maxUses = maxUses;
    }

    @Override
    public void resetUses() {
        this.mcTradeOffer.resetUses();
    }

    @Override
    public boolean isRewardsExperience() {
        return this.mcTradeOffer.shouldRewardPlayerExperience();
    }

    @Override
    public void setRewardsExperience(boolean flag) {
        this.mcTradeOffer.rewardingPlayerExperience = flag;
    }

    @Override
    public int getSpecialPrice() {
        return this.mcTradeOffer.getSpecialPrice();
    }

    @Override
    public void setSpecialPrice(int specialPrice) {
        this.mcTradeOffer.specialPrice = specialPrice;
    }

    @Override
    public int getDemandBonus() {
        return this.mcTradeOffer.getDemandBonus();
    }

    @Override
    public void setDemandBonus(int demandBonus) {
        this.mcTradeOffer.demandBonus = demandBonus;
    }

    @Override
    public float getPriceMultiplier() {
        return this.mcTradeOffer.getPriceMultiplier();
    }

    @Override
    public void setPriceMultiplier(float multiplier) {
        this.mcTradeOffer.priceMultiplier = multiplier;
    }

    @Override
    public int getExperienceAwardedToVillager() {
        return this.mcTradeOffer.getTraderExperience();
    }

    @Override
    public void setExperienceAwardedToVillager(int experience) {
        this.mcTradeOffer.traderExperience = experience;
    }

    public net.minecraft.village.TradeOffer getMinecraftTradeOffer() {
        return this.mcTradeOffer;
    }

    public static TradeOffer ofMc(net.minecraft.village.TradeOffer mcTradeOffer) {
        return new TradeOfferImpl(mcTradeOffer);
    }
}

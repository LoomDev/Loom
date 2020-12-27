package org.loomdev.loom.village;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.village.MerchantOffer;
import org.loomdev.loom.item.ItemStackImpl;

import java.util.Optional;

public class MerchantOfferImpl implements MerchantOffer {

    private final net.minecraft.world.item.trading.MerchantOffer mcOffer;

    private MerchantOfferImpl(net.minecraft.world.item.trading.MerchantOffer mcTradeOffer) {
        this.mcOffer = mcTradeOffer;
    }

    @Override
    @NotNull
    public ItemStack getFirstBuyItem() {
        return ItemStackImpl.of(mcOffer.getBaseCostA());
    }

    @Override
    @NotNull
    public ItemStack getAdjustedFirstBuyItem() {
        return ItemStackImpl.of(mcOffer.getCostA());
    }

    @Override
    public void setFirstBuyItem(@NotNull ItemStack itemStack) {
        mcOffer.baseCostA = ((ItemStackImpl) itemStack).getMinecraftItemStack();
    }

    @Override
    @NotNull
    public Optional<ItemStack> getSecondBuyItem() {
        return Optional.ofNullable(mcOffer.costB)
                .map(ItemStackImpl::of);
    }

    @Override
    public void setSecondBuyItem(@Nullable ItemStack itemStack) {
        mcOffer.costB = itemStack == null ? null : ((ItemStackImpl) itemStack).getMinecraftItemStack();
    }

    @Override
    @NotNull
    public ItemStack getSellItem() {
        return ItemStackImpl.of(mcOffer.result);
    }

    @Override
    public void setSellItem(@NotNull ItemStack itemStack) {
        mcOffer.result = ((ItemStackImpl) itemStack).getMinecraftItemStack();
    }

    @Override
    public int getUses() {
        return mcOffer.getUses();
    }

    @Override
    public void setUses(int uses) {
        this.mcOffer.uses = uses;
    }

    @Override
    public int getMaxUses() {
        return mcOffer.getMaxUses();
    }

    @Override
    public void setMaxUses(int maxUses) {
        mcOffer.uses = maxUses;
    }

    @Override
    public void resetUses() {
        this.mcOffer.resetUses();
    }

    @Override
    public boolean isRewardsExperience() {
        return this.mcOffer.shouldRewardExp();
    }

    @Override
    public void setRewardsExperience(boolean flag) {
        mcOffer.rewardExp = flag;
    }

    @Override
    public int getSpecialPrice() {
        return mcOffer.getSpecialPriceDiff();
    }

    @Override
    public void setSpecialPrice(int specialPrice) {
        mcOffer.specialPriceDiff = specialPrice;
    }

    @Override
    public int getDemandBonus() {
        return mcOffer.getDemand();
    }

    @Override
    public void setDemandBonus(int demandBonus) {
        mcOffer.demand = demandBonus;
    }

    @Override
    public float getPriceMultiplier() {
        return this.mcOffer.getPriceMultiplier();
    }

    @Override
    public void setPriceMultiplier(float multiplier) {
        this.mcOffer.priceMultiplier = multiplier;
    }

    @Override
    public int getExperienceAwardedToVillager() {
        return this.mcOffer.getXp();
    }

    @Override
    public void setExperienceAwardedToVillager(int experience) {
        this.mcOffer.xp = experience;
    }

    public net.minecraft.world.item.trading.MerchantOffer getMinecraftOffer() {
        return this.mcOffer;
    }

    public static MerchantOffer of(net.minecraft.world.item.trading.MerchantOffer mcOffer) {
        return new MerchantOfferImpl(mcOffer);
    }
}

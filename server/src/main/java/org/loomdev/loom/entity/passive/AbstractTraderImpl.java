package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.village.TradeOfferList;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.passive.AbstractTrader;
import org.loomdev.api.village.TradeOffer;
import org.loomdev.loom.village.TradeOfferImpl;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractTraderImpl extends PassiveEntityImpl implements AbstractTrader {

    public AbstractTraderImpl(MerchantEntity entity) {
        super(entity);
    }

    @NotNull
    @Override
    public MerchantEntity getMinecraftEntity() {
        return (MerchantEntity) super.getMinecraftEntity();
    }

    @Override
    public @NotNull List<TradeOffer> getTradeOffers() {
        return getMinecraftEntity().getOffers().stream()
                .map(TradeOfferImpl::ofMc)
                .collect(Collectors.toList());
    }

    @Override
    public void setTradeOffers(@NotNull List<TradeOffer> list) {
        TradeOfferList traderOfferList = new TradeOfferList();
        list.stream()
                .map(to -> ((TradeOfferImpl) to).getMinecraftTradeOffer())
                .collect(Collectors.toCollection(() -> traderOfferList));
        getMinecraftEntity().offers = traderOfferList;
    }

    @Override
    public void trade(@NotNull TradeOffer tradeOffer) {
        getMinecraftEntity().trade(((TradeOfferImpl) tradeOffer).getMinecraftTradeOffer());
    }

    @Override
    public int getExperience() {
        return getMinecraftEntity().getExperience();
    }

    @Override
    public void setExperience(int experience) {
        getMinecraftEntity().setExperienceFromServer(experience);
    }

    @Override
    public boolean isLeveled() {
        return getMinecraftEntity().isLeveledMerchant();
    }
}

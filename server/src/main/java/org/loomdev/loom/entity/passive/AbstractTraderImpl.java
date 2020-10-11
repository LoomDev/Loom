package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.AbstractTraderEntity;
import net.minecraft.village.TraderOfferList;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.passive.AbstractTrader;
import org.loomdev.api.village.TradeOffer;
import org.loomdev.loom.village.TradeOfferImpl;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractTraderImpl extends PassiveEntityImpl implements AbstractTrader {

    public AbstractTraderImpl(AbstractTraderEntity entity) {
        super(entity);
    }

    @NotNull
    @Override
    public AbstractTraderEntity getMinecraftEntity() {
        return (AbstractTraderEntity) super.getMinecraftEntity();
    }

    @Override
    public @NotNull List<TradeOffer> getTradeOffers() {
        return getMinecraftEntity().getOffers().stream()
                .map(TradeOfferImpl::ofMc)
                .collect(Collectors.toList());
    }

    @Override
    public void setTradeOffers(@NotNull List<TradeOffer> list) {
        TraderOfferList traderOfferList = new TraderOfferList();
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
        return getMinecraftEntity().isLeveledTrader();
    }
}

package org.loomdev.api.village;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Trader {

    @NotNull
    List<TradeOffer> getTradeOffers();

    void setTradeOffers(@NotNull List<TradeOffer> tradeOffers);

    void trade(@NotNull TradeOffer offer);

    int getExperience();

    void setExperience(int experience);

    boolean isLeveled();

}

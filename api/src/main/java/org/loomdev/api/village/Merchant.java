package org.loomdev.api.village;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Merchant {

    @NotNull
    List<MerchantOffer> getTradeOffers();

    void setTradeOffers(@NotNull List<MerchantOffer> tradeOffers);

    void trade(@NotNull MerchantOffer offer);

    int getExperience();

    void setExperience(int experience);

    boolean hasProgressBar();
}

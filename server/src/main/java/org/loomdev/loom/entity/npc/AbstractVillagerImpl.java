package org.loomdev.loom.entity.npc;

import net.minecraft.world.item.trading.MerchantOffers;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.npc.AbstractVillager;
import org.loomdev.api.village.MerchantOffer;
import org.loomdev.loom.entity.AgeableMobImpl;
import org.loomdev.loom.village.MerchantOfferImpl;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractVillagerImpl extends AgeableMobImpl implements AbstractVillager {

    public AbstractVillagerImpl(net.minecraft.world.entity.npc.AbstractVillager entity) {
        super(entity);
    }

    @NotNull
    @Override
    public net.minecraft.world.entity.npc.AbstractVillager getMinecraftEntity() {
        return (net.minecraft.world.entity.npc.AbstractVillager) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public List<MerchantOffer> getTradeOffers() {
        return getMinecraftEntity().getOffers().stream()
                .map(MerchantOfferImpl::of)
                .collect(Collectors.toList());
    }

    @Override
    public void setTradeOffers(@NotNull List<MerchantOffer> list) {
        var traderOfferList = new MerchantOffers();
        list.stream()
                .map(to -> ((MerchantOfferImpl) to).getMinecraftOffer())
                .collect(Collectors.toCollection(() -> traderOfferList));
        getMinecraftEntity().offers = traderOfferList;
    }

    @Override
    public void trade(@NotNull MerchantOffer tradeOffer) {
        getMinecraftEntity().notifyTrade(((MerchantOfferImpl) tradeOffer).getMinecraftOffer());
    }

    @Override
    public int getExperience() {
        return getMinecraftEntity().getVillagerXp();
    }

    @Override
    public void setExperience(int experience) {
        getMinecraftEntity().overrideXp(experience);
    }

    @Override
    public boolean hasProgressBar() {
        return getMinecraftEntity().showProgressBar();
    }
}

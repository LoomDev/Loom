package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.AbstractDonkeyEntity;
import org.loomdev.api.entity.passive.AbstractDonkey;

public class AbstractDonkeyImpl extends HorseBaseImpl implements AbstractDonkey {

    public AbstractDonkeyImpl(AbstractDonkeyEntity entity) {
        super(entity);
    }

    @Override
    public AbstractDonkeyEntity getMinecraftEntity() {
        return (AbstractDonkeyEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean hasChest() {
        return getMinecraftEntity().hasChest();
    }

    @Override
    public void setHasChest(boolean b) {
        getMinecraftEntity().setHasChest(b);
    }
}

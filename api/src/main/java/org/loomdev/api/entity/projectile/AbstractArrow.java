package org.loomdev.api.entity.projectile;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.sound.Sound;
import org.loomdev.api.sound.SoundEvent;

public interface AbstractArrow extends Projectile {

    boolean isInBlock();

    double getDamage();

    void setDamage(double damage);

    float getWaterIntertia();

    void setWaterIntertia(float inertia);

    @NotNull
    PickupPermission getPickupPermission();

    void setPickupPermission(@NotNull PickupPermission pickupPermission);

    boolean isShotFromCrossbow();

    void setShotFromCrossbow(boolean flag);

    boolean isNoClip();

    void setNoClip(boolean flag);

    byte getPierceLevel();

    void setPierceLevel(byte level);

    boolean isCritical();

    void setCritical(boolean flag);

    int getKnockback();

    void setKnockback(int knockback);

    ItemStack asItemStack();

    enum PickupPermission {
        DISALLOWED, ALLOWED, CREATIVE_ONLY;

        public static PickupPermission fromOrdinal(int i) {
            if (i < 0 || i > values().length) {
                i = 0;
            }

            return values()[i];
        }
    }
}

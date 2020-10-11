package org.loomdev.loom.item.property.data;

import org.loomdev.api.item.property.data.DamageData;
import org.loomdev.api.math.MathHelper;

public class DamageDataImpl implements DamageData {

    private int damage;
    private final int maxDamage;
    private boolean unbreakable;

    public DamageDataImpl(int damage, int maxDamage, boolean unbreakable) {
        this.maxDamage = maxDamage;
        this.setDamage(damage);
        this.unbreakable = unbreakable;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = MathHelper.clamp(damage, 0, getMaxDamage());
    }

    @Override
    public void increment(int i) {
        this.setDamage(getDamage() + i);
    }

    @Override
    public void decrement(int i) {
        this.setDamage(getDamage() - i);
    }

    @Override
    public int getMaxDamage() {
        return this.maxDamage;
    }

    @Override
    public boolean isUnbreakable() {
        return this.unbreakable;
    }

    @Override
    public void setUnbreakable(boolean flag) {
        this.unbreakable = flag;
    }
}

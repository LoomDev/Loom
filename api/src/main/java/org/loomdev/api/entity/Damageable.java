package org.loomdev.api.entity;

public interface Damageable extends Entity {

    void damage(float amount);

    void damage(float amount, Entity source);

    float getHealth();

    void setHealth(float health);

    float getMaxHealth();

    void setMaxHealth(float maxHealth);

    void resetMaxHealth();

    float getAbsorptionAmount();

    void setAbsorptionAmount(float amount);

}

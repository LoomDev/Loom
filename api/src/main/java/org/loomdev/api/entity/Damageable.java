package org.loomdev.api.entity;

import org.loomdev.api.entity.damage.DamageSource;

/**
 * Represents an entity with health.
 */
public interface Damageable extends Entity {

    void damage(float amount);

    void damage(float amount, Entity source);

    void damage(float amount, DamageSource source);

    float getHealth();

    void setHealth(float health);

    float getMaxHealth();

    void setMaxHealth(float maxHealth);

    void resetMaxHealth();

    float getAbsorptionAmount();

    void setAbsorptionAmount(float amount);

}

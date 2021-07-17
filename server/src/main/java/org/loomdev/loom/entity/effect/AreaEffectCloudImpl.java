package org.loomdev.loom.entity.effect;

import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.effect.AreaEffectCloud;
import org.loomdev.api.entity.effect.StatusEffect;
import org.loomdev.api.entity.effect.StatusEffectType;
import org.loomdev.api.particle.Particle;
import org.loomdev.loom.entity.EntityImpl;
import org.loomdev.loom.entity.LivingEntityImpl;
import org.loomdev.loom.transformer.Transformer;

import java.util.Optional;
import java.util.stream.Stream;

public class AreaEffectCloudImpl extends EntityImpl implements AreaEffectCloud {

    public AreaEffectCloudImpl(net.minecraft.world.entity.AreaEffectCloud entity) {
        super(entity);
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.AreaEffectCloud getMinecraftEntity() {
        return (net.minecraft.world.entity.AreaEffectCloud) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public EntityType<AreaEffectCloud> getType() {
        return EntityType.AREA_EFFECT_CLOUD;
    }

    @Override
    @NotNull
    public Stream<StatusEffect> getStatusEffects() {
        return getMinecraftEntity().effects.stream()
                .map(Transformer.STATUS_EFFECT::toLoom);
    }

    @Override
    @NotNull
    public Optional<StatusEffect> getStatusEffect(@NotNull StatusEffectType type) {
        return getStatusEffects()
                .filter(effect -> effect.getType().equals(type))
                .findFirst();
    }

    @Override
    public void addStatusEffect(@NotNull StatusEffect effect) {
        getMinecraftEntity().addEffect(Transformer.STATUS_EFFECT.toMinecraft(effect));
    }

    @Override
    public void removeStatusEffect(@NotNull StatusEffectType type) {
        getMinecraftEntity().effects.removeIf(effect -> effect.getEffect().equals(Transformer.STATUS_EFFECT_TYPE.toMinecraft(type)));
    }

    @Override
    public boolean hasStatusEffect(@NotNull StatusEffectType type) {
        return getStatusEffect(type).isPresent();
    }

    @Override
    public void clearStatusEffects() {
        getMinecraftEntity().effects.clear();
    }

    @Override
    public int getDuration() {
        return getMinecraftEntity().getDuration();
    }

    @Override
    public void setDuration(int duration) {
        getMinecraftEntity().setDuration(duration);
    }

    @Override
    public float getRadius() {
        return getMinecraftEntity().getRadius();
    }

    @Override
    public void setRadius(float radius) {
        getMinecraftEntity().setRadius(radius);
    }

    @Override
    public int getColor() {
        return getMinecraftEntity().getColor();
    }

    @Override
    public void setColor(int color) {
        getMinecraftEntity().setFixedColor(color);
    }

    @Override
    public int getWaitTime() {
        return getMinecraftEntity().waitTime;
    }

    @Override
    public void setWaitTime(int ticks) {
        getMinecraftEntity().setWaitTime(ticks);
    }

    @Override
    public boolean isWaiting() {
        return getMinecraftEntity().isWaiting();
    }

    @Override
    public void setWaiting(boolean waiting) {
        getMinecraftEntity().setWaiting(waiting);
    }

    @Override
    public float getRadiusOnUse() {
        return getMinecraftEntity().radiusOnUse;
    }

    @Override
    public void setRadiusOnUse(float radius) {
        getMinecraftEntity().radiusOnUse = radius;
    }

    @Override
    public float getRadiusPerTick() {
        return getMinecraftEntity().radiusPerTick;
    }

    @Override
    public void setRadiusPerTick(float radius) {
        getMinecraftEntity().radiusPerTick = radius;
    }

    @Override
    @NotNull
    public Optional<LivingEntity> getOwner() {
        return Optional.ofNullable(getMinecraftEntity().getOwner())
                .map(owner -> (LivingEntity) owner.getLoomEntity());
    }

    @Override
    public void setOwner(@Nullable LivingEntity entity) {
        if (entity == null) {
            getMinecraftEntity().setOwner(null);
        } else {
            getMinecraftEntity().setOwner(((LivingEntityImpl) entity).getMinecraftEntity());
        }
    }

    @Override
    @NotNull
    public Stream<LivingEntity> getAffectedEntities() {
        return getMinecraftEntity().victims.keySet().stream()
                .map(Entity::getLoomEntity)
                .map(LivingEntity.class::cast);
    }

    @Override
    @NotNull
    public Particle getParticle() {
        return null;
        // return ParticleTransformer.toLoom(); TODO update when we have a toLoom transformer
    }

    @Override
    public void setParticle(@NotNull Particle particle) {
        getMinecraftEntity().setParticle(Transformer.PARTICLE.toMinecraft(particle));
    }
}

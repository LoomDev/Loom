package org.loomdev.loom.entity.effect;

import org.loomdev.api.entity.effect.StatusEffectType;
import org.loomdev.loom.util.registry.GenericWrapped;

public class StatusEffectTypeImpl extends GenericWrapped implements StatusEffectType {
    public StatusEffectTypeImpl(String key) {
        super(key);
    }
}

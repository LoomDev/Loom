package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.SpellcastingIllagerEntity;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.mob.SpellcastingIllager;

public class SpellcastingIllagerImpl extends IllagerImpl implements SpellcastingIllager {

    public SpellcastingIllagerImpl(SpellcastingIllagerEntity entity) {
        super(entity);
    }

    @Override
    public SpellcastingIllagerEntity getMinecraftEntity() {
        return (SpellcastingIllagerEntity) super.getMinecraftEntity();
    }

    @Override
    public @NonNull Spell getSpell() {
        return Spell.getById(getMinecraftEntity().spell.id);
    }

    @Override
    public void setSpell(@NonNull Spell spell) {
        getMinecraftEntity().setSpell(SpellcastingIllagerEntity.Spell.byId(spell.getId()));
    }
}

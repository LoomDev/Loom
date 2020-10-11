package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.SpellcastingIllagerEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.mob.SpellcastingIllager;

public abstract class SpellcastingIllagerImpl extends IllagerImpl implements SpellcastingIllager {

    public SpellcastingIllagerImpl(SpellcastingIllagerEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull SpellcastingIllagerEntity getMinecraftEntity() {
        return (SpellcastingIllagerEntity) super.getMinecraftEntity();
    }

    @Override
    public @NotNull Spell getSpell() {
        return Spell.getById(getMinecraftEntity().spell.id);
    }

    @Override
    public void setSpell(@NotNull Spell spell) {
        getMinecraftEntity().setSpell(SpellcastingIllagerEntity.Spell.byId(spell.getId()));
    }
}

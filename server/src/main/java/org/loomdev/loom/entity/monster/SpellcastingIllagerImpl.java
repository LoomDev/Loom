package org.loomdev.loom.entity.monster;

import net.minecraft.world.entity.monster.SpellcasterIllager;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.mob.SpellcastingIllager;

public abstract class SpellcastingIllagerImpl extends AbstractIllagerImpl implements SpellcastingIllager {

    public SpellcastingIllagerImpl(SpellcasterIllager entity) {
        super(entity);
    }

    @Override
    @NotNull
    public SpellcasterIllager getMinecraftEntity() {
        return (SpellcasterIllager) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public Spell getSpell() {
        return Spell.getById(getMinecraftEntity().currentSpell.id);
    }

    @Override
    public void setSpell(@NotNull Spell spell) {
        getMinecraftEntity().setIsCastingSpell(SpellcasterIllager.IllagerSpell.byId(spell.getId()));
    }
}

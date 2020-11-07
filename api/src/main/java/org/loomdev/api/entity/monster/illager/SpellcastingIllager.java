package org.loomdev.api.entity.monster.illager;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public interface SpellcastingIllager extends Illager {

    @NotNull Spell getSpell();

    void setSpell(@NotNull Spell spell);

    enum Spell {
        NONE(0),
        SUMMON_VEX(1),
        FANGS(2),
        WOLOLO(3),
        DISAPPEAR(4),
        BLINDNESS(5);

        private static final Map<Integer, Spell> mapById = new HashMap<>();
        private final int id;

        Spell(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        static {
            for (Spell spell : values()) {
                mapById.put(spell.id, spell);
            }
        }

        public static Spell getById(int id) {
            return mapById.get(id);
        }
    }
}

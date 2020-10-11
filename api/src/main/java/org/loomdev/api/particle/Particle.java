package org.loomdev.api.particle;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.particle.data.ParticleData;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Particle {

    private final @NotNull Type type;
    private final @Nullable ParticleData data;
    private final int amount;
    private final double offsetX, offsetY, offsetZ;
    private final int extraData;

    private Particle(@NotNull Type type, @Nullable ParticleData data, int amount, double offsetX, double offsetY, double offsetZ, int extraData) {
        this.type = type;
        this.data = data;
        this.amount = amount;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.extraData = extraData;
    }

    public @NotNull Type getType() {
        return type;
    }

    public @NotNull Optional<ParticleData> getData() {
        return Optional.ofNullable(data);
    }

    public int getAmount() {
        return amount;
    }

    public double getOffsetX() {
        return offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public double getOffsetZ() {
        return offsetZ;
    }

    public int getExtraData() {
        return extraData;
    }

    public static Builder builder(@NotNull Type type) {
        return new Builder(type);
    }

    public static final class Builder {

        private final @NotNull Type type;
        private int amount = 1;
        private double offsetX = 0;
        private double offsetY = 0;
        private double offsetZ = 0;
        private @Nullable ParticleData data;
        private int extraData = 0;

        public Builder(@NotNull Type type) {
            this.type = type;
        }

        public Builder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder offset(double offsetX, double offsetY, double offsetZ) {
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            this.offsetZ = offsetZ;
            return this;
        }

        public Builder data(@NotNull ParticleData data) {
            this.data = data;
            return this;
        }

        public Builder extraData(int extraData) {
            this.extraData = extraData;
            return this;
        }

        public Particle build() {
            return new Particle(
                    this.type,
                    this.data,
                    this.amount,
                    this.offsetX,
                    this.offsetY,
                    this.offsetZ,
                    this.extraData
            );
        }
    }

    public enum Type {

        AMBIENT_ENTITY_EFFECT("minecraft:ambient_entity_effect"),
        ANGRY_VILLAGER("minecraft:angry_villager"),
        ASH("minecraft:ash"),
        BARRIER("minecraft:barrier"),
        BLOCK("minecraft:block"),
        BUBBLE("minecraft:bubble"),
        BUBBLE_COLUMN_UP("minecraft:bubble_column_up"),
        BUBBLE_POP("minecraft:bubble_pop"),
        CAMPFIRE_COSY_SMOKE("minecraft:campfire_cosy_smoke"),
        CAMPFIRE_SIGNAL_SMOKE("minecraft:campfire_signal_smoke"),
        CLOUD("minecraft:cloud"),
        COMPOSTER("minecraft:composter"),
        CRIMSON_SPORE("minecraft:crimson_spore"),
        CRIT("minecraft:crit"),
        CURRENT_DOWN("minecraft:current_down"),
        DAMAGE_INDICATOR("minecraft:damage_indicator"),
        DOLPHIN("minecraft:dolphin"),
        DRAGON_BREATH("minecraft:dragon_breath"),
        DRIPPING_HONEY("minecraft:dripping_honey"),
        DRIPPING_LAVA("minecraft:dripping_lava"),
        DRIPPING_OBSIDIAN_TEAR("minecraft:dripping_obsidian_tear"),
        DRIPPING_WATER("minecraft:dripping_water"),
        DUST("minecraft:dust"),
        EFFECT("minecraft:effect"),
        ELDER_GUARDIAN("minecraft:elder_guardian"),
        ENCHANT("minecraft:enchant"),
        ENCHANTED_HIT("minecraft:enchanted_hit"),
        END_ROD("minecraft:end_rod"),
        ENTITY_EFFECT("minecraft:entity_effect"),
        EXPLOSION("minecraft:explosion"),
        EXPLOSION_EMITTER("minecraft:explosion_emitter"),
        FALLING_DUST("minecraft:falling_dust"),
        FALLING_HONEY("minecraft:falling_honey"),
        FALLING_LAVA("minecraft:falling_lava"),
        FALLING_NECTAR("minecraft:falling_nectar"),
        FALLING_OBSIDIAN_TEAR("minecraft:falling_obsidian_tear"),
        FALLING_WATER("minecraft:falling_water"),
        FIREWORK("minecraft:firework"),
        FISHING("minecraft:fishing"),
        FLAME("minecraft:flame"),
        FLASH("minecraft:flash"),
        HAPPY_VILLAGER("minecraft:happy_villager"),
        HEART("minecraft:heart"),
        INSTANT_EFFECT("minecraft:instant_effect"),
        ITEM("minecraft:item"),
        ITEM_SLIME("minecraft:item_slime"),
        ITEM_SNOWBALL("minecraft:item_snowball"),
        LANDING_HONEY("minecraft:landing_honey"),
        LANDING_LAVA("minecraft:landing_lava"),
        LANDING_OBSIDIAN_TEAR("minecraft:landing_obsidian_tear"),
        LARGE_SMOKE("minecraft:large_smoke"),
        LAVA("minecraft:lava"),
        MYCELIUM("minecraft:mycelium"),
        NAUTILUS("minecraft:nautilus"),
        NOTE("minecraft:note"),
        POOF("minecraft:poof"),
        PORTAL("minecraft:portal"),
        RAIN("minecraft:rain"),
        REVERSE_PORTAL("minecraft:reverse_portal"),
        SMOKE("minecraft:smoke"),
        SNEEZE("minecraft:sneeze"),
        SOUL("minecraft:soul"),
        SOUL_FIRE_FLAME("minecraft:soul_fire_flame"),
        SPIT("minecraft:spit"),
        SPLASH("minecraft:splash"),
        SQUID_INK("minecraft:squid_ink"),
        SWEEP_ATTACK("minecraft:sweep_attack"),
        TOTEM_OF_UNDYING("minecraft:totem_of_undying"),
        UNDERWATER("minecraft:underwater"),
        WARPED_SPORE("minecraft:warped_spore"),
        WHITE_ASH("minecraft:white_ash"),
        WITCH("minecraft:witch");

        private static Map<String, Type> mapById = new HashMap<>();
        private final String id;

        Type(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }

        public static @Nullable Type getById(String id) {
            return mapById.get(id);
        }

        static {
            for (Type type : values()) {
                mapById.put(type.id, type);
            }
        }
    }
}

package org.loomdev.api.entity.effect;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class StatusEffectTypeTests {

    @Test
    void doEnumValuesMatchRegistry() {
        Map<String, Integer> current = Arrays.stream(StatusEffect.Type.values()).collect(Collectors.toMap(Enum::name, StatusEffect.Type::rawId));
        Map<String, Integer> missing = new HashMap<>();
        List<String> versionMismatch = new ArrayList<>();

        for (Identifier id : Registry.STATUS_EFFECT.getIds()) {
            String name = id.getPath().toUpperCase();
            int rawId = Registry.STATUS_EFFECT.getRawId(Registry.STATUS_EFFECT.get(id));
            if (!current.containsKey(name)) {
                missing.put(name, rawId);
            } else if (current.get(name) != rawId) {
                versionMismatch.add("Wrong raw id:! " + name + " has " + current.get(name) + " but should be " + rawId);
            }
        }

        List<Map.Entry<String, Integer>> missingSorted = new ArrayList<>(missing.entrySet());
        missingSorted.sort(Map.Entry.comparingByValue());
        Assertions.assertTrue(missing.isEmpty(), "Missing status effects: \n" + String.join(",\n", missingSorted.stream().map(kv -> kv.getKey() + "(" + kv.getValue() + ")").collect(Collectors.toList())));
        Assertions.assertTrue(versionMismatch.isEmpty(), String.join("\n", versionMismatch));
    }
}

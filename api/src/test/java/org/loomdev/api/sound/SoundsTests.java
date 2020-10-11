package org.loomdev.api.sound;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class SoundsTests {

    @Test
    void doEnumValuesMatchRegistry() {
        Map<String, Integer> current = Arrays.stream(Sound.Type.values()).collect(Collectors.toMap(Enum::name, Sound.Type::rawId));
        Map<String, Integer> missing = new HashMap<>();
        List<String> versionMismatch = new ArrayList<>();

        for (Identifier id : Registry.SOUND_EVENT.getIds()) {
            String name = id.getPath().toUpperCase().replaceAll("\\.", "_");
            int rawId = Registry.SOUND_EVENT.getRawId(Registry.SOUND_EVENT.get(id));
            if (!current.containsKey(name)) {
                missing.put(name, rawId);
            } else if (current.get(name) != rawId) {
                versionMismatch.add("Wrong raw id:! " + name + " has " + current.get(name) + " but should be " + rawId);
            }
        }

        List<Map.Entry<String, Integer>> missingSorted = new ArrayList<>(missing.entrySet());
        missingSorted.sort(Map.Entry.comparingByValue());
        Assertions.assertTrue(missing.isEmpty(), "Missing sounds: \n" + String.join(",\n", missingSorted.stream().map(kv -> kv.getKey() + "(" + kv.getValue() + ")").collect(Collectors.toList())));
        Assertions.assertTrue(versionMismatch.isEmpty(), String.join("\n", versionMismatch));
    }
}

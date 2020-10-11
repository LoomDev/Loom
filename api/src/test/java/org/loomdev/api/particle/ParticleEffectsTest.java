package org.loomdev.api.particle;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

class ParticleEffectsTest {

    @Test
    void doEnumValuesMatchRegistry() {
        Map<String, String> current = Arrays.stream(Particle.Type.values()).collect(Collectors.toMap(Enum::name, Particle.Type::getId));
        Map<String, String> missing = new HashMap<>();
        List<String> versionMismatch = new ArrayList<>();

        for (Identifier id : Registry.PARTICLE_TYPE.getIds()) {
            String name = id.getPath().toUpperCase();
            if (!current.containsKey(name)) {
                missing.put(name, id.toString());
            } else if (!current.get(name).equals(id.toString())) {
                versionMismatch.add("Wrong raw id:! " + name + " has " + current.get(name) + " but should be " + id.toString());
            }
        }

        List<Map.Entry<String, String>> missingSorted = new ArrayList<>(missing.entrySet());
        missingSorted.sort(Map.Entry.comparingByValue());
        Assertions.assertTrue(missing.isEmpty(), "Missing particles: \n" + String.join(",\n", missingSorted.stream().map(kv -> kv.getKey() + "(\"" + kv.getValue() + "\")").collect(Collectors.toList())));
        Assertions.assertTrue(versionMismatch.isEmpty(), String.join("\n", versionMismatch));
    }

}
package org.loomdev.api.sound;

import net.minecraft.sound.SoundCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class SoundCategoriesTest {

    @Test
    void doEnumValuesMatchRegistry() {
        Map<String, String> missing = new HashMap<>();
        for (SoundCategory mcCategory : SoundCategory.values()) {
            if (SoundCategories.getByName(mcCategory.name()) == null) {
                missing.put(mcCategory.name(), mcCategory.getName());
            }
        }

        List<Map.Entry<String, String>> missingSorted = new ArrayList<>(missing.entrySet());
        missingSorted.sort(Map.Entry.comparingByValue());
        Assertions.assertTrue(missing.isEmpty(), "Missing sound categories: \n" + String.join(",\n", missingSorted.stream().map(kv -> kv.getKey() + "(" + kv.getValue() + ")").collect(Collectors.toList())));
    }

}
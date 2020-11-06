package org.loomdev.api.helpers;

import com.google.common.collect.Maps;
import net.minecraft.core.Registry;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class LoomAssert {
    private LoomAssert() {}

    public static void fieldsAgainstMCRegistry(Class<?> typesClass, Registry<?> registry) {
        fieldsAgainstMCRegistry(typesClass, registry, field -> Modifier.isStatic(field.getModifiers()));
    }

    public static void fieldsAgainstMCRegistry(Class<?> typesClass, Registry<?> registry, Predicate<Field> fieldFilter) {
        List<String> loomTypes = Arrays.stream(typesClass.getDeclaredFields())
                .filter(fieldFilter)
                .map(Field::getName)
                .collect(Collectors.toList());

        Map<String, String> missingTypes = Maps.newHashMap();

        for (var id : registry.keySet()) {
            String typeName = id.getPath().toUpperCase().replace(".", "_");
            if (!loomTypes.contains(typeName)) {
                missingTypes.put(typeName, id.toString());
            }
        }

        List<String> code = missingTypes.entrySet().stream()
                .map(missingType -> typesClass.getSimpleName() + " " + missingType.getKey() + " = getById(\"" + missingType.getValue() + "\");")
                .collect(Collectors.toList());

        Assertions.assertTrue(missingTypes.isEmpty(), "Missing: \n\n" + String.join("\n", code) + "\n\n");
    }
}

package org.loomdev.api.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class NamespacedKeyTests {

    // region providers

    private static Stream<Arguments> provideInvalidNamespacesAndKeys() {
        return Stream.of(
                Arguments.of("minecr@ft", "somekey@123"),
                Arguments.of("minecraft/test", "somekey@123"),
                Arguments.of("minecraft", "somekey{123}"),
                Arguments.of("minecraft", "som#key*123")
        );
    }

    private static Stream<Arguments> provideValidNamespacesAndKeys() {
        return Stream.of(
                Arguments.of("minecraft", "some_key"),
                Arguments.of("minecraft01", "some_key"),
                Arguments.of("minecraft.1", "some_key"),
                Arguments.of("minecraft_1", "some_key"),
                Arguments.of("minecraft-1", "some_key"),
                Arguments.of("minecraft", "some_key"),
                Arguments.of("minecraft", "some_key01"),
                Arguments.of("minecraft", "some_key.1"),
                Arguments.of("minecraft", "some_key_1"),
                Arguments.of("minecraft", "some_key-1"),
                Arguments.of("minecraft", "some_key/1")
        );
    }

    private static Stream<Arguments> provideInvalidNamespacedKeys() {
        return provideInvalidNamespacesAndKeys()
                .map(argument ->  Arguments.of(argument.get()[0] + NamespacedKey.SEPARATOR + argument.get()[1]));
    }

    private static Stream<Arguments> provideValidNamespacedKeys() {
        return provideValidNamespacesAndKeys()
                .map(argument ->  Arguments.of(argument.get()[0] + NamespacedKey.SEPARATOR + argument.get()[1]));
    }

    private static Stream<Arguments> provideInvalidKeys() {
        return provideInvalidNamespacesAndKeys()
                .map(argument ->  Arguments.of(argument.get()[1]));
    }

    private static Stream<Arguments> provideValidKeys() {
        return provideValidNamespacesAndKeys()
                .map(argument ->  Arguments.of(argument.get()[1]));
    }

    // endregion providers

    @ParameterizedTest
    @MethodSource("provideInvalidNamespacedKeys")
    public void ofValue_shouldThrow_InvalidNamespacedKeyException_whenInvalid(String value) {
        assertThrows(InvalidNamespacedKeyException.class, () -> NamespacedKey.of(value));
    }

    @ParameterizedTest
    @MethodSource("provideValidNamespacedKeys")
    public void ofValue(String value) {
        assertDoesNotThrow(() -> NamespacedKey.of(value));
    }

    @Test
    public void ofValue_shouldHaveCorrectNamespaceAndKey() {
        var namespacedKey = NamespacedKey.of("namespace_value" + NamespacedKey.SEPARATOR + "key_value");
        assertEquals("namespace_value", namespacedKey.getNamespace());
        assertEquals("key_value", namespacedKey.getKey());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidNamespacesAndKeys")
    public void ofNamespaceKey_shouldThrow_InvalidNamespacedKeyException_whenInvalid(String namespace, String key) {
        assertThrows(InvalidNamespacedKeyException.class, () -> NamespacedKey.of(namespace, key));
    }

    @ParameterizedTest
    @MethodSource("provideValidNamespacesAndKeys")
    public void ofNamespaceKey(String namespace, String key) {
        assertDoesNotThrow(() -> NamespacedKey.of(namespace, key));
    }

    @Test
    public void ofNamespaceKey_shouldHaveCorrectNamespaceAndKey() {
        var namespacedKey = NamespacedKey.of("namespace_value", "key_value");
        assertEquals("namespace_value", namespacedKey.getNamespace());
        assertEquals("key_value", namespacedKey.getKey());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidKeys")
    public void minecraft_shouldThrow_InvalidNamespacedKeyException_whenKeyInvalid(String key) {
        assertThrows(InvalidNamespacedKeyException.class, () -> NamespacedKey.minecraft(key));
    }

    @ParameterizedTest
    @MethodSource("provideValidKeys")
    public void minecraft(String key) {
        assertDoesNotThrow(() -> NamespacedKey.minecraft(key));
    }

    @Test
    public void minecraft_shouldHaveCorrectNamespace() {
        var namespacedKey = NamespacedKey.minecraft("test");
        assertEquals("minecraft", namespacedKey.getNamespace());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidKeys")
    public void loom_shouldThrow_InvalidNamespacedKeyException_whenKeyInvalid(String key) {
        assertThrows(InvalidNamespacedKeyException.class, () -> NamespacedKey.loom(key));
    }

    @ParameterizedTest
    @MethodSource("provideValidKeys")
    public void loom(String key) {
        assertDoesNotThrow(() -> NamespacedKey.loom(key));
    }

    @Test
    public void loom_shouldHaveCorrectNamespace() {
        var namespacedKey = NamespacedKey.loom("test");
        assertEquals("loom", namespacedKey.getNamespace());
    }

}

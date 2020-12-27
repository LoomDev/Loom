package org.loomdev.api.util;

public class InvalidNamespacedKeyException extends RuntimeException {

    public InvalidNamespacedKeyException(String value) {
        super("Invalid namespaced key '" + value + "'.\nNamespaced keys can only contain lowercase alphanumeric characters, periods, underscores and hyphens.");
    }
}

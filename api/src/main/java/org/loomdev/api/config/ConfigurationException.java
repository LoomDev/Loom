package org.loomdev.api.config;

public class ConfigurationException extends RuntimeException {
    public ConfigurationException() {
        super();
    }

    public ConfigurationException(String message) {
        super(message);
    }


    public ConfigurationException(Throwable e) {
        super(e);
    }

    public ConfigurationException(String message, Throwable e) {
        super(message, e);
    }
}

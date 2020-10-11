package org.loomdev.loom.plugin.loader.injector;

public class InjectionException extends RuntimeException {
    public InjectionException() {
    }

    public InjectionException(String message) {
        super(message);
    }

    public InjectionException(Throwable e) {
        super(e);
    }

    public InjectionException(String message, Throwable e) {
        super(message, e);
    }
}

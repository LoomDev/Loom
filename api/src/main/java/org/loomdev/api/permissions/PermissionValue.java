package org.loomdev.api.permissions;

public enum PermissionValue {

    UNKNOWN(false),
    PERMITTED(true),
    DENIED(false);

    private final boolean booleanValue;

    PermissionValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    boolean asBoolean() {
        return this.booleanValue;
    }
}

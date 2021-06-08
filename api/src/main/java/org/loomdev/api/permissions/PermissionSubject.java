package org.loomdev.api.permissions;

public interface PermissionSubject {

    PermissionValue getPermission(String permission);

    default boolean hasPermission(String permission) {
        return getPermission(permission).asBoolean();
    }

    boolean isOperator();

}

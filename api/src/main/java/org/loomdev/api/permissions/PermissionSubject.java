package org.loomdev.api.permissions;

import org.loomdev.api.Loom;

public interface PermissionSubject {

    default PermissionValue getPermission(String permission) {
        return Loom.getPermissionsEngine().getPermission(this, permission);
    }

    default boolean hasPermission(String permission) {
        return getPermission(permission).asBoolean();
    }

    boolean isOperator();

}

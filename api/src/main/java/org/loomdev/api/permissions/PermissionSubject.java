package org.loomdev.api.permissions;

public interface PermissionSubject {

    PermissionValue getPermission(String permission);

    boolean hasPermission(String permission);

    boolean isOperator();

}

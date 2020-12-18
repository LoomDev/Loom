package org.loomdev.api.permissions;

import org.jetbrains.annotations.NotNull;

public interface PermissionHandler {

    @NotNull
    PermissionValue getPermission(@NotNull PermissionSubject subject, @NotNull String permission);

}

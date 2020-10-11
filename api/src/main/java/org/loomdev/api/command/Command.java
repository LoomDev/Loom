package org.loomdev.api.command;

import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public abstract class Command implements CommandExecutor {

    private final String name;

    private final String[] aliases;

    private String description, usage, permission;

    protected Command(@NotNull String name) {
        this.name = name;
        this.aliases = new String[0];
    }

    protected Command(@NotNull String name, @Nullable String... aliases) {
        this.name = name;
        this.aliases = aliases;
    }

    public @NotNull List<String> suggest(@NotNull CommandSource source, @Nullable String[] args) {
        return ImmutableList.of();
    }

    public @NotNull String getName() {
        return this.name;
    }

    public @NotNull String[] getAliases() {
        return this.aliases;
    }

    public @NotNull Optional<String> getDescription() {
        return Optional.ofNullable(this.description);
    }

    public void setDescription(@NotNull String description) {
        this.description = description;
    }

    public @NotNull Optional<String> getUsage() {
        return Optional.ofNullable(this.usage);
    }

    public void setUsage(@NotNull String usage) {
        this.usage = usage;
    }

    public @NotNull Optional<String> getPermission() {
        return Optional.ofNullable(this.permission);
    }

    public void setPermission(@NotNull String permission) {
        this.permission = permission;
    }
}

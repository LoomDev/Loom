package org.loomdev.api.scheduler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.plugin.metadata.PluginMetadata;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static org.loomdev.api.scheduler.ScheduledTask.Builder;

/**
 * Represents a task scheduler.
 */
public interface Scheduler {

    @NotNull
    Builder createTask();

    @NotNull
    Optional<ScheduledTask> getTaskById(int id);

    @NotNull
    Stream<ScheduledTask> getScheduledTasks();

    @NotNull
    Stream<ScheduledTask> getScheduledTasks(@NotNull PluginMetadata plugin);

    void unregisterTasks(@NotNull PluginMetadata plugin);

    void cancelTask(int id, boolean interrupt);
}

package org.loomdev.api.scheduler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    Stream<ScheduledTask> getScheduledTasks(@NotNull Object plugin);

    void unregisterTasks(@NotNull Object plugin);

    void cancelTask(int id, boolean interrupt);
}

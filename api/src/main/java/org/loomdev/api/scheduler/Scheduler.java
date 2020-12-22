package org.loomdev.api.scheduler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

import static org.loomdev.api.scheduler.ScheduledTask.Builder;

/**
 * Represents a task scheduler.
 */
public interface Scheduler {

    @NotNull
    Builder createTask();

    @Nullable
    ScheduledTask getTaskById(int id);

    @NotNull
    Set<ScheduledTask> getScheduledTasks();

    @NotNull
    Set<ScheduledTask> getScheduledTasks(@NotNull Object plugin);

    void unregisterTasks(@NotNull Object plugin);

    void cancelTask(int id, boolean interrupt);
}

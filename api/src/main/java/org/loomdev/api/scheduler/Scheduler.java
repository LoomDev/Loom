package org.loomdev.api.scheduler;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.plugin.Plugin;

import java.util.Optional;
import java.util.Set;

import static org.loomdev.api.scheduler.Task.Builder;

public interface Scheduler {

    @NotNull Builder createTask();

    @NotNull Optional<Task> getTaskById(int id);

    @NotNull Set<Task> getScheduledTasks();

    @NotNull Set<Task> getScheduledTasks(@NotNull Plugin plugin);

    void unregisterSchedulers(@NotNull Plugin plugin);

    void disableTask(int id, boolean interruptIfRunning);
}

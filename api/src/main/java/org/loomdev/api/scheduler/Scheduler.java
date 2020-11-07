package org.loomdev.api.scheduler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.plugin.Plugin;

import java.util.Set;

import static org.loomdev.api.scheduler.ScheduledTask.Builder;

public interface Scheduler {

    @NotNull
    Builder createTask();

    @Nullable
    ScheduledTask getTaskById(int id);

    @NotNull
    Set<ScheduledTask> getScheduledTasks();

    @NotNull
    Set<ScheduledTask> getScheduledTasks(@NotNull Plugin plugin);

    void unregisterTasks(@NotNull Plugin plugin);

    void cancelTask(int id, boolean interrupt);
}

package org.loomdev.api.scheduler;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.plugin.metadata.PluginMetadata;

import java.util.concurrent.TimeUnit;

/**
 * Represents a task that has been scheduled.
 */
public interface ScheduledTask {

    @NotNull
    PluginMetadata getPlugin();

    int getTaskId();

    boolean isSync();

    long getDelay();

    long getPeriod();

    void cancelTask(boolean interrupt);

    @NotNull
    ExecutionState getExecutionState();

    interface Builder {

        @NotNull
        Builder async();

        @NotNull
        Builder execute(@NotNull Runnable runnable);

        @NotNull
        Builder delay(long delay);

        @NotNull
        Builder delay(long delay, TimeUnit timeUnit);

        @NotNull
        Builder interval(long ticks);

        @NotNull
        Builder interval(long delay, TimeUnit timeUnit);

        @NotNull
        ScheduledTask complete(@NotNull PluginMetadata plugin);
    }

    enum ExecutionState {
        STOPPED,
        RUNNING,
        WAITING
    }
}

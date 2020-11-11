package org.loomdev.api.scheduler;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;

import java.util.concurrent.TimeUnit;

public interface ScheduledTask {

    @NotNull
    Object getPlugin();

    int getTaskId();

    boolean isSync();

    long getDelay();

    long getPeriod();

    void cancelTask(boolean interrupt);

    @NotNull
    ExecutionState getExecutionState();

    @NotNull
    static Builder builder() {
        return Loom.getServer().getScheduler().createTask();
    }

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
        ScheduledTask complete(@NotNull Object plugin);
    }

    enum ExecutionState {
        STOPPED,
        RUNNING,
        WAITING
    }
}

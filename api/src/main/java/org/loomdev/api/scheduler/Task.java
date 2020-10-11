package org.loomdev.api.scheduler;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.plugin.Plugin;

import java.util.concurrent.TimeUnit;

public interface Task {

    int getTaskId();

    @NotNull Plugin getPlugin();

    void cancel();

    void cancelInterrupt();

    static @NotNull Builder builder() {
        return Loom.getServer().getScheduler().createTask();
    }

    interface Builder {

        @NotNull Builder async();

        @NotNull Builder execute(@NotNull Runnable runnable);

        @NotNull Builder delay(long delay, TimeUnit timeUnit);

        @NotNull Builder delayTicks(long delay);

        @NotNull Builder interval(long delay, TimeUnit timeUnit);

        @NotNull Builder intervalTicks(long ticks);

        @NotNull Task complete(@NotNull Plugin plugin);
    }
}

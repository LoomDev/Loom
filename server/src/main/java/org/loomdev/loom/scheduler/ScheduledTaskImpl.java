package org.loomdev.loom.scheduler;

import com.google.common.base.Preconditions;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.scheduler.ScheduledTask;
import org.loomdev.api.scheduler.ScheduledTaskRunnable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ScheduledTaskImpl extends FutureTask<Void> implements ScheduledTask {

    private static final AtomicInteger NEXT_TASK_ID = new AtomicInteger(0);

    private final Object plugin;
    private final int taskId;
    private final boolean sync;
    private final long delay;
    private final long period;
    private ExecutionState state;
    private long tickCounter;

    public ScheduledTaskImpl(@NotNull Object plugin, @NotNull Runnable task, boolean sync, long delay, long period) {
        super(task, null);
        Preconditions.checkArgument(period != 0, "Period cannot be zero.");
        this.plugin = plugin;
        this.taskId = NEXT_TASK_ID.getAndIncrement();
        this.sync = sync;
        this.delay = delay;
        this.period = period;
        this.state = ExecutionState.WAITING;
    }

    public void serverTick() {
        if (isDone()) {
            state = ExecutionState.STOPPED;
            return;
        }

        ++tickCounter;
        if (tickCounter >= delay && (period == -1 || (tickCounter - delay) % period == 0)) {
            state = ExecutionState.RUNNING;
        }
    }

    @NotNull
    public Object getPlugin() {
        return plugin;
    }

    @Override
    public int getTaskId() {
        return taskId;
    }

    @Override
    public boolean isSync() {
        return sync;
    }

    @Override
    public long getDelay() {
        return delay;
    }

    @Override
    public long getPeriod() {
        return period;
    }

    @Override
    @NotNull
    public ExecutionState getExecutionState() {
        return state;
    }

    @Override
    public void cancelTask(boolean interrupt) {
        super.cancel(interrupt);
    }

    @Override
    public void run() {
        if (period == -1) {
            super.run();
        } else {
            runAndReset();
        }
    }

    @Override
    protected void done() {
        super.done();

        if (isCancelled()) {
            return;
        }

        try {
            get();
        } catch (ExecutionException ex) {
            LogManager.getLogger("Task logger - #" + taskId).log(Level.ERROR, "Error while executing " + this, ex.getCause());
        } catch (InterruptedException e) {
            // Task is already done
        }
    }

    static class BuilderImpl implements Builder {

        private final SchedulerImpl scheduler;
        private boolean async;
        private Runnable runnable;
        private long delayTicks;
        private long intervalTicks;

        public BuilderImpl(SchedulerImpl scheduler) {
            this.scheduler = scheduler;
            this.async = false;
        }

        @Override
        @NotNull
        public Builder async() {
            async = true;
            return this;
        }

        @Override
        @NotNull
        public Builder execute(@NotNull Runnable task) {
            runnable = task;
            return this;
        }

        @Override
        @NotNull
        public Builder delay(long delay) {
            delayTicks = delay;
            return this;
        }

        @Override
        @NotNull
        public Builder delay(long delay, @NotNull TimeUnit timeUnit) {
            delayTicks = timeUnit.convert(delay, TimeUnit.SECONDS) * 20;
            return this;
        }

        @Override
        @NotNull
        public Builder interval(long interval) {
            intervalTicks = interval;
            return this;
        }

        @Override
        @NotNull
        public Builder interval(long interval, TimeUnit timeUnit) {
            intervalTicks = timeUnit.convert(interval, TimeUnit.SECONDS) * 20;
            return this;
        }

        @Override
        @NotNull
        public ScheduledTask complete(@NotNull Object plugin) {
            var task = new ScheduledTaskImpl(plugin, runnable, !async, delayTicks, intervalTicks < 1 ? -1 : intervalTicks);

            if (runnable instanceof ScheduledTaskRunnable) {
                ((ScheduledTaskRunnable) runnable).setTask(task);
            }

            this.scheduler.scheduleTask(task);
            return task;
        }
    }
}

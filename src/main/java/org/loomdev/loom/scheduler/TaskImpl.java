package org.loomdev.loom.scheduler;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.loomdev.api.plugin.Plugin;
import org.loomdev.api.scheduler.Task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskImpl extends FutureTask<Void> implements Task {

    private static final AtomicInteger nextTaskId = new AtomicInteger(0);

    private final int taskId;
    private final Plugin plugin;
    private final boolean sync;
    private final long delay;
    private final long period;
    private Thread thread;

    private long tickCounter;

    public TaskImpl(Plugin plugin, Runnable task, boolean sync, long delay, long period) {
        super(task, null);
        taskId = nextTaskId.getAndIncrement();
        this.plugin = plugin;
        this.sync = sync;
        this.delay = delay;
        this.period = period;
        this.tickCounter = 0;
    }

    public void cancel() {
        cancel(false);
    }

    TaskExecutionState shouldExecute() {
        TaskExecutionState execState = shouldExecuteUpdate();
        return execState;
    }

    private TaskExecutionState shouldExecuteUpdate() {
        if (isDone()) {
            return TaskExecutionState.STOP;
        }

        ++tickCounter;
        if (tickCounter >= delay) {
            if (period == -1 || (tickCounter - delay) % period == 0) {
                return TaskExecutionState.RUN;
            }
        }

        return TaskExecutionState.WAIT;
    }

    @Override
    public void run() {
        thread = Thread.currentThread();
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
            LogManager.getLogger("Loom task logger - #" + taskId).log(Level.ERROR, "Error while executing " + this, ex.getCause());
        } catch (InterruptedException e) {
            // Task is already done
        }
    }

    public int getTaskId() {
        return taskId;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public boolean isSync() {
        return sync;
    }

    enum TaskExecutionState {
        STOP, RUN, WAIT;
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
        public Builder async() {
            this.async = true;
            return this;
        }

        @Override
        public Builder execute(Runnable runnable) {
            this.runnable = runnable;
            return this;
        }

        @Override
        public Builder delayTicks(long l) {
            this.delayTicks = l;
            return this;
        }

        @Override
        public Builder intervalTicks(long l) {
            this.intervalTicks = l;
            return this;
        }

        @Override
        public Task complete(Plugin plugin) {
            TaskImpl task = new TaskImpl(plugin, this.runnable, !this.async, delayTicks, intervalTicks);
            this.scheduler.scheduleTask(task);
            return task;
        }
    }
}

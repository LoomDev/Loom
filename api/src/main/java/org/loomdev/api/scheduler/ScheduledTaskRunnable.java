package org.loomdev.api.scheduler;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;

public abstract class ScheduledTaskRunnable implements Runnable {

    private ScheduledTask task;

    public ScheduledTask getTask() {
        return task;
    }

    public void setTask(@NotNull ScheduledTask task) {
        Preconditions.checkArgument(this.task == null, "Task is already set.");
        this.task = task;
    }

    public void cancel() {
        task.cancelTask(true);
    }
}

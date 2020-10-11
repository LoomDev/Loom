package org.loomdev.api.scheduler;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;

public abstract class TaskRunnable implements Runnable {

    private Task task;

    public Task getTask() {
        return task;
    }

    public void setTask(@NotNull Task task) {
        Preconditions.checkArgument(this.task == null, "Task is already set.");
        this.task = task;
    }

    public void cancel() {
        task.cancelInterrupt();
    }
}

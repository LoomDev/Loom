package org.loomdev.loom.scheduler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.plugin.PluginManager;
import org.loomdev.api.scheduler.Scheduler;
import org.loomdev.api.scheduler.ScheduledTask;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class SchedulerImpl implements Scheduler {

    private final ExecutorService asyncExecutor;
    private final ConcurrentHashMap<Integer, ScheduledTaskImpl> taskQueue;

    public SchedulerImpl(PluginManager pluginManager) {
        this.asyncExecutor = new ThreadPoolExecutor(1, Runtime.getRuntime().availableProcessors(), 60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        this.taskQueue = new ConcurrentHashMap<>();
    }

    @Override
    @NotNull
    public ScheduledTask.Builder createTask() {
        return new ScheduledTaskImpl.BuilderImpl(this);
    }

    public void scheduleTask(@NotNull ScheduledTaskImpl task) {
        taskQueue.put(task.getTaskId(), task);
    }

    public void shutdown() {
        asyncExecutor.shutdownNow();
    }

    @Override
    @Nullable
    public ScheduledTask getTaskById(int id) {
        return taskQueue.get(id);
    }

    @Override
    @NotNull
    public Set<ScheduledTask> getScheduledTasks() {
        return new HashSet<>(taskQueue.values());
    }

    @Override
    @NotNull
    public Set<ScheduledTask> getScheduledTasks(@NotNull Object plugin) {
        return taskQueue.values().stream()
                .filter(t -> t.getPlugin() == plugin)
                .collect(Collectors.toSet());
    }

    @Override
    public void cancelTask(int id, boolean interruptIfRunning) {
        var task = taskQueue.get(id);
        if (task == null) {
            return;
        }

        task.cancelTask(interruptIfRunning);
        taskQueue.remove(id);
    }

    @Override
    public void unregisterTasks(@NotNull Object plugin) {
        getScheduledTasks(plugin).forEach(task -> cancelTask(task.getTaskId(), true));
    }

    public void serverTick() {
        var iterator = taskQueue.values().iterator();
        while (iterator.hasNext()) {
            var task = iterator.next();
            task.serverTick();

            switch (task.getExecutionState()) {
                case RUNNING:
                    if (task.isSync()) {
                        task.run();
                    } else {
                        asyncExecutor.submit(task);
                    }
                    break;
                case STOPPED:
                    iterator.remove();
                    break;
                default:
                    break;
            }
        }
    }
}

package org.loomdev.loom.scheduler;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.plugin.PluginManager;
import org.loomdev.api.scheduler.Scheduler;
import org.loomdev.api.scheduler.ScheduledTask;

import java.util.Optional;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class SchedulerImpl implements Scheduler {

    private final ExecutorService asyncExecutor;
    private final ConcurrentHashMap<Integer, ScheduledTask> taskQueue;

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
    @NotNull
    public Optional<ScheduledTask> getTaskById(int id) {
        return Optional.ofNullable(taskQueue.get(id));
    }

    @Override
    @NotNull
    public Stream<ScheduledTask> getScheduledTasks() {
        return taskQueue.values().stream();
    }

    @Override
    @NotNull
    public Stream<ScheduledTask> getScheduledTasks(@NotNull Object plugin) {
        return getScheduledTasks()
                .filter(task -> task.getPlugin() == plugin);
    }

    @Override
    public void cancelTask(int id, boolean interrupt) {
        getTaskById(id).ifPresent(task -> {
            task.cancelTask(interrupt);
            taskQueue.remove(id);
        });
    }

    @Override
    public void unregisterTasks(@NotNull Object plugin) {
        getScheduledTasks(plugin).forEach(task -> cancelTask(task.getTaskId(), true));
    }

    public void serverTick() {
        var iterator = taskQueue.values().iterator();
        while (iterator.hasNext()) {
            var task = (ScheduledTaskImpl) iterator.next();
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

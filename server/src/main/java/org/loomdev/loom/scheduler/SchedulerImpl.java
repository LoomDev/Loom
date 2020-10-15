package org.loomdev.loom.scheduler;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.plugin.Plugin;
import org.loomdev.api.plugin.PluginManager;
import org.loomdev.api.scheduler.Scheduler;
import org.loomdev.api.scheduler.Task;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class SchedulerImpl implements Scheduler {

    private final PluginManager pluginManager;
    private final ScheduledExecutorService executor;
    private final ExecutorService asyncTaskExecutor;

    private final ConcurrentHashMap<Integer, TaskImpl> tasks = new ConcurrentHashMap<>();

    public SchedulerImpl(PluginManager pluginManager) {
        this.pluginManager = pluginManager;

        executor = Executors.newSingleThreadScheduledExecutor(); // Scheduler executor
        asyncTaskExecutor = new ThreadPoolExecutor(0, Runtime.getRuntime().availableProcessors(), 60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>()); // async task executor
    }

    @Override
    public Task.@NotNull Builder createTask() {
        return new TaskImpl.BuilderImpl(this);
    }

    public void scheduleTask(TaskImpl task) {
        this.tasks.put(task.getTaskId(), task);
    }

    public void start() { }

    public void stop() {
        tasks.values().forEach(task -> disableTask(task.getTaskId(), true));
        executor.shutdownNow();
        asyncTaskExecutor.shutdown();
    }

    public void pulse() {
        for (Iterator<TaskImpl> it = tasks.values().iterator(); it.hasNext();) {
            TaskImpl task = it.next();
            switch (task.shouldExecute()) {
                case RUN:
                    if (task.isSync()) {
                        task.run();
                    } else {
                        asyncTaskExecutor.submit(task);
                    }
                    break;
                case STOP:
                    it.remove();
                    break;
                default:
                    break;
            }
        }

    }

    @Override
    public @NotNull Optional<Task> getTaskById(int id) {
        return Optional.ofNullable(tasks.get(id));
    }

    @Override
    public @NotNull Set<Task> getScheduledTasks() {
        return new HashSet<>(tasks.values());
    }

    @Override
    public @NotNull Set<Task> getScheduledTasks(@NotNull Plugin plugin) {
        return tasks.values().stream()
                .filter(t -> t.getPlugin() == plugin)
                .collect(Collectors.toSet());
    }

    @Override
    public void unregisterSchedulers(@NotNull Plugin plugin) {
        getScheduledTasks(plugin).forEach(task -> disableTask(task.getTaskId(), true));
    }

    @Override
    public void disableTask(int taskId, boolean interruptIfRunning) {
        Task task = tasks.get(taskId);
        if (interruptIfRunning) {
            task.cancelInterrupt();
        } else {
            task.cancel();
        }
        this.tasks.remove(taskId);
    }
}

package org.loomdev.loom.scheduler;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.plugin.Plugin;
import org.loomdev.api.plugin.PluginManager;
import org.loomdev.api.scheduler.Scheduler;
import org.loomdev.api.scheduler.Task;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class SchedulerImpl implements Scheduler {

    /**
     * The number of milliseconds between pulses.
     */
    static final int PULSE_EVERY = 50;

    private final PluginManager pluginManager;
    private final ScheduledExecutorService executor;
    private final ExecutorService asyncTaskExecutor;

    private final ConcurrentHashMap<Integer, TaskImpl> tasks = new ConcurrentHashMap<>();

    private Thread primaryThread;

    public SchedulerImpl(PluginManager pluginManager) {
        this.pluginManager = pluginManager;

        executor = Executors.newSingleThreadScheduledExecutor(); // Scheduler executor
        asyncTaskExecutor = new ThreadPoolExecutor(0, Runtime.getRuntime().availableProcessors(), 60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>()); // async task executor
        this.primaryThread = Thread.currentThread();
    }

    @Override
    public Task.@NonNull Builder createTaskBuilder() {
        return new TaskImpl.BuilderImpl(this);
    }

    public void scheduleTask(TaskImpl task) {
        this.tasks.put(task.getTaskId(), task);
    }

    public void start() {
        System.out.println("start logger XDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
        //executor.scheduleAtFixedRate(this::pulse, 0, PULSE_EVERY, TimeUnit.MILLISECONDS); // TODO should this be changed to the ms tickloop?
    }

    public void stop() {
        // TODO cancel all tasks
        executor.shutdownNow();
        asyncTaskExecutor.shutdown();
    }

    public void pulse() {
        primaryThread = Thread.currentThread();

        // System.out.println("pulse");

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
    public @NonNull Optional<Task> getTaskById(int id) {
        return Optional.ofNullable(tasks.get(id));
    }

    @Override
    public @NonNull Set<Task> getScheduledTasks() {
        return new HashSet<>(tasks.values());
    }

    @Override
    public @NonNull Set<Task> getScheduledTasks(Plugin plugin) {
        return tasks.values().stream()
                .filter(t -> t.getPlugin() == plugin)
                .collect(Collectors.toSet());
    }

}

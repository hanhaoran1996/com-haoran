package com.haoran.common;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author hanhaoran
 * @date 2019/8/24 12:16
 */
public final class Task {
    private Task() {}

    private static final Logger LOGGER = LoggerFactory.getLogger(Task.class);

    private static final ExecutorService POOL = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors() * 2,
            Constants.SIXTEEN,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(Constants.INT128),
            new ThreadFactoryBuilder().setDaemon(true).setNameFormat("task-pool-%d").build(),
            (r, executor) -> LOGGER.warn("Task {} ignored by {}", r.toString(), executor.toString())
    );

    static {
        Runtime.getRuntime().addShutdownHook(Executors.defaultThreadFactory().newThread(POOL::shutdown));
    }

    public void execute(Runnable command) {
        POOL.execute(command);
    }

    public Future<?> submit(Runnable task) {
        return POOL.submit(task);
    }

    public <V> Future<V> submit(Runnable task, V result) {
        return POOL.submit(task, result);
    }

    public <V> Future<V> submit(Callable<V> task) {
        return POOL.submit(task);
    }

    public <V> List<Future<V>> invokeAll(Collection<? extends Callable<V>> tasks) {
        try {
            return POOL.invokeAll(tasks);
        } catch (InterruptedException e) {
            return null;
        }
    }

    public <V> List<Future<V>> invokeAll(Collection<? extends Callable<V>> tasks, long timeout, TimeUnit unit) {
        try {
            return POOL.invokeAll(tasks, timeout, unit);
        } catch (InterruptedException e) {
            return null;
        }
    }
}

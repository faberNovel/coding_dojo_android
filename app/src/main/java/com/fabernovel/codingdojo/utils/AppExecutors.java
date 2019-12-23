package com.fabernovel.codingdojo.utils;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Global executor pools for the whole application.
 * <p>
 * Grouping tasks like this avoids the effects of task starvation (e.g. disk reads don't wait behind
 * webservice requests).
 */
public class AppExecutors {

    private static final int THREAD_COUNT = 3;

    private static final AppExecutors ourInstance = new AppExecutors();

    public static AppExecutors getInstance() {
        return ourInstance;
    }

    private final Executor diskIO;

    private final Executor networkIO;

    private final Executor mainThread;

    private AppExecutors(Executor diskIO, Executor networkIO, Executor mainThread) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }

    private AppExecutors() {
        this(
            Executors.newSingleThreadExecutor(),
            Executors.newFixedThreadPool(THREAD_COUNT),
            new MainThreadExecutor()
        );
    }

    public Executor diskIO() {
        return diskIO;
    }

    public Executor networkIO() {
        return networkIO;
    }

    public Executor mainThread() {
        return mainThread;
    }

    public void onMainThread(@NonNull Runnable runnable) {
        mainThread.execute(runnable);
    }

    public void onDiskIO(@NonNull Runnable runnable) {
        diskIO.execute(runnable);
    }

    public void onNetworkIO(@NonNull Runnable runnable) {
        networkIO.execute(runnable);
    }

    private static class MainThreadExecutor implements Executor {
        private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}

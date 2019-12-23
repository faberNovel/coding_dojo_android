package com.fabernovel.codingdojo.data;

import com.fabernovel.codingdojo.utils.AppExecutors;
import java.util.Date;
import timber.log.Timber;

public class TimeRepository {

    private final AppExecutors appExecutors;

    public TimeRepository(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
    }

    public void getTime(GetTimeCallback callback) {
        appExecutors.onNetworkIO(() -> {
            try {
                Thread.sleep(1000);
                Date date = new Date();
                dispatchSuccess(date, callback);
            } catch (InterruptedException e) {
                Timber.w(e);
                dispatchError(e, callback);
            }
        });
    }

    private void dispatchError(Throwable e, GetTimeCallback callback) {
        appExecutors.onMainThread(() -> callback.onError(e));
    }

    private void dispatchSuccess(Date date, GetTimeCallback callback) {
        appExecutors.onMainThread(() -> callback.onGetTime(date));
    }
}

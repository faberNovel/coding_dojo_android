package com.fabernovel.codingdojo.app.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.fabernovel.codingdojo.data.GetTimeCallback;
import com.fabernovel.codingdojo.data.TimeRepository;
import com.fabernovel.codingdojo.utils.AppExecutors;
import java.util.Date;

public class DiscoverPresenter {
    public DiscoverPresenter(
        TimeRepository timeRepository
    ) {
        this.timeRepository = timeRepository;
    }

    public static DiscoverPresenter getInstance() {
        return new DiscoverPresenter(new TimeRepository(AppExecutors.getInstance()));
    }

    @Nullable
    private DiscoverViewContract view;

    private final TimeRepository timeRepository;

    public void start(@NonNull DiscoverViewContract view) {
        this.view = view;
        view.showLoading();

        getTime();
    }

    private void getTime() {
        timeRepository.getTime(new GetTimeCallback() {
            @Override
            public void onGetTime(@NonNull Date time) {
                if (view != null) {
                    String message = String.format("It's %s", time.toString());
                    view.showContent(message);
                }
            }

            @Override
            public void onError(@NonNull Throwable error) {
                if (view != null) {
                    view.showError(error.getLocalizedMessage());
                }
            }
        });
    }

    public void stop() {
        this.view = null;
    }
}

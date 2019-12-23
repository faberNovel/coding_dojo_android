package com.fabernovel.codingdojo.data;

import androidx.annotation.NonNull;
import java.util.Date;

public interface GetTimeCallback {
    void onGetTime(@NonNull Date time);
    void onError(@NonNull Throwable error);
}

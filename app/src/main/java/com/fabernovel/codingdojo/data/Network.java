package com.fabernovel.codingdojo.data;

import androidx.annotation.NonNull;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class Network {
    private static final String BASE_URL = "";
    private static final long WRITE_TIMEOUT = 10_000L;
    private static final long CONNECT_TIMEOUT = 10_000L;

    private static final Network ourInstance = new Network();

    public static Network getInstance() {
        return ourInstance;
    }

    private final MovieApi movieApi;

    private Network() {
        Retrofit retrofit = buildRetrofit();
        movieApi = retrofit.create(MovieApi.class);
    }

    @NonNull
    private Retrofit buildRetrofit() {
        return new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(buildOkHttpClient())
            .build();
    }

    @NonNull
    private OkHttpClient buildOkHttpClient() {
        return new OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
            .addInterceptor(new HttpLoggingInterceptor())
            .build();
    }

    @NonNull
    public MovieApi movieApi() {
        return movieApi;
    }
}

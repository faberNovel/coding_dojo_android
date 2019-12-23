package com.fabernovel.codingdojo.data;

import android.content.Context;

import androidx.annotation.NonNull;

import com.readystatesoftware.chuck.ChuckInterceptor;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class Network {
    private static final String BASE_URL = "https://api.themoviedb.org/";
    private static final long WRITE_TIMEOUT = 10_000L;
    private static final long CONNECT_TIMEOUT = 10_000L;

    private static Network ourInstance;

    public static Network getInstance(Context context) {
        if (ourInstance == null) ourInstance = new Network(context);
        return ourInstance;
    }

    private final MovieApi movieApi;

    private Network(Context context) {
        Retrofit retrofit = buildRetrofit(context);
        movieApi = retrofit.create(MovieApi.class);
    }

    @NonNull
    private Retrofit buildRetrofit(Context context) {
        return new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(buildOkHttpClient(context))
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    @NonNull
    private OkHttpClient buildOkHttpClient(Context context) {
        return new OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
            .addInterceptor(new HttpLoggingInterceptor())
            .addInterceptor(new ChuckInterceptor(context))
            .build();
    }

    @NonNull
    MovieApi movieApi() {
        return movieApi;
    }
}

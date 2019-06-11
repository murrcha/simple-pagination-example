package com.example.iceandfirecharacters.network;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * IceAndFireService
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 */
import static com.example.iceandfirecharacters.network.IceAndFireApi.BASE_URL;

public class IceAndFireService {

    private static Retrofit retrofit;

    private static IceAndFireService instance;

    private IceAndFireService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory
                        .create(new GsonBuilder()
                                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                .setLenient()
                                .create()))
                .client(client.build())
                .build();
    }

    public static IceAndFireService getInstance() {
        if (instance == null) {
            instance = new IceAndFireService();
        }
        return instance;
    }

    public IceAndFireApi getApi() {
        return retrofit.create(IceAndFireApi.class);
    }
}

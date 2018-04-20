package com.example.android.currencyconversionapp.activity.conversion.di;

import com.example.android.currencyconversionapp.activity.conversion.network.CurrencyWebService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final String BASE_URL = "http://hnbex.eu/api/v1/";

    private static final int CONNECT_TIMEOUT_IN_MS = 30000;


    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient(){

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder().connectTimeout(CONNECT_TIMEOUT_IN_MS, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor).build();
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit (OkHttpClient okHttpClient){

        return new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(okHttpClient).build();
    }

    @Provides
    @Singleton
    CurrencyWebService providesCurrencyWebService(Retrofit retrofit){
        return retrofit.create(CurrencyWebService.class);
    }

}

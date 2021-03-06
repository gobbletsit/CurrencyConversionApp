package com.example.android.currencyconversionapp.activity.conversion.di;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context context;

    public AppModule (Application application){
        context = application;
    }

    @Provides
    @Singleton
    public Context providesContext(){return context;}

    @Provides
    @Singleton
    public Resources provideResources (Context context){
        return context.getResources();
    }

}

package com.example.android.currencyconversionapp.activity.conversion;

import android.app.Application;
import android.os.StrictMode;

import com.example.android.currencyconversionapp.activity.conversion.di.AppComponent;
import com.example.android.currencyconversionapp.activity.conversion.di.AppModule;
import com.example.android.currencyconversionapp.activity.conversion.di.ConversionComponent;
import com.example.android.currencyconversionapp.activity.conversion.di.ConversionModule;

import com.example.android.currencyconversionapp.activity.conversion.di.DaggerAppComponent;
import com.example.android.currencyconversionapp.activity.conversion.di.NetworkModule;

public class BaseApplication extends Application {

    private AppComponent appComponent;
    private ConversionComponent conversionComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = createAppComponent();
    }

    public AppComponent createAppComponent(){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule()).build();

    }

    public ConversionComponent createConversionComponent(){
        conversionComponent = appComponent.plus(new ConversionModule());
        return conversionComponent;
    }

    // to release it in onDestroy
    public void releaseConversionComponent(){conversionComponent = null;}
}

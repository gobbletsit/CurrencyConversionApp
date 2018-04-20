package com.example.android.currencyconversionapp.activity.conversion.di;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    ConversionComponent plus (ConversionModule conversionModule);

}

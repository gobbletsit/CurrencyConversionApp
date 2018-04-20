package com.example.android.currencyconversionapp.activity.conversion.di;

import com.example.android.currencyconversionapp.activity.conversion.conversion.model.ConversionInteractor;
import com.example.android.currencyconversionapp.activity.conversion.conversion.model.ConversionInteractorImpl;
import com.example.android.currencyconversionapp.activity.conversion.conversion.presenter.ConversionPresenter;
import com.example.android.currencyconversionapp.activity.conversion.conversion.presenter.ConversionPresenterImpl;
import com.example.android.currencyconversionapp.activity.conversion.network.CurrencyWebService;

import dagger.Module;
import dagger.Provides;

@Module
public class ConversionModule {

    @ConversionScope
    @Provides
    ConversionInteractor providesConversionInteractor (CurrencyWebService currencyWebService){
        return new ConversionInteractorImpl(currencyWebService);
    }

    @ConversionScope
    @Provides
    ConversionPresenter providesConversionPresenter(ConversionInteractor conversionInteractor){
        return new ConversionPresenterImpl(conversionInteractor);
    }
}

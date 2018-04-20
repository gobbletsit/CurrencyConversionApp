package com.example.android.currencyconversionapp.activity.conversion.di;

import com.example.android.currencyconversionapp.activity.conversion.conversion.view.ConversionFragment;

import dagger.Subcomponent;

@ConversionScope
@Subcomponent (modules = {ConversionModule.class})
public interface ConversionComponent {

    void inject(ConversionFragment conversionFragment);

}

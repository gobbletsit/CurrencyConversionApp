package com.example.android.currencyconversionapp.activity.conversion.conversion.presenter;

import com.example.android.currencyconversionapp.activity.conversion.conversion.view.ConversionView;
import com.example.android.currencyconversionapp.activity.conversion.data.Unit;

public interface ConversionPresenter {

    void showUnits();

    void setConversionView(ConversionView conversionView);

    void onButtonClick(Unit unitFrom, Unit unitTo);

    void destroy();

}

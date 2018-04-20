package com.example.android.currencyconversionapp.activity.conversion.conversion.view;

import com.example.android.currencyconversionapp.activity.conversion.data.Unit;

import java.util.List;

public interface ConversionView {

    void setUpView(List<Unit> unitList);

    void onSubmitButtonClick(Unit unitFrom, Unit unitTo);

}

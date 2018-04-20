package com.example.android.currencyconversionapp.activity.conversion.conversion.model;

import com.example.android.currencyconversionapp.activity.conversion.data.Unit;

import java.util.List;
import java.util.Observable;

public interface ConversionInteractor {

    io.reactivex.Observable<List<Unit>> getListOfUnits();

}

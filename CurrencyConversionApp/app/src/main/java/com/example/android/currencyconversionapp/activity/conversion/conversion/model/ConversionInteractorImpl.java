package com.example.android.currencyconversionapp.activity.conversion.conversion.model;

import com.example.android.currencyconversionapp.activity.conversion.data.Unit;
import com.example.android.currencyconversionapp.activity.conversion.network.CurrencyWebService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ConversionInteractorImpl implements ConversionInteractor {

    private CurrencyWebService currencyWebService;


    public ConversionInteractorImpl (CurrencyWebService currencyWebService){
        this.currencyWebService = currencyWebService;
    }

    @Override
    public Observable<List<Unit>> getListOfUnits() {
        return currencyWebService.getCurrencyUnits();
    }
}

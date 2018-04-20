package com.example.android.currencyconversionapp.activity.conversion.network;

import com.example.android.currencyconversionapp.activity.conversion.data.Unit;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface CurrencyWebService {

    @GET("rates/daily/")
    Observable<List<Unit>> getCurrencyUnits();
}

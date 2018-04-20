package com.example.android.currencyconversionapp.activity.conversion.conversion.presenter;

import android.graphics.Movie;
import android.nfc.Tag;
import android.util.Log;

import com.example.android.currencyconversionapp.activity.conversion.conversion.model.ConversionInteractor;
import com.example.android.currencyconversionapp.activity.conversion.conversion.view.ConversionView;
import com.example.android.currencyconversionapp.activity.conversion.data.Unit;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ConversionPresenterImpl implements ConversionPresenter {

    private static final String TAG = "ConversionPresenterImpl";

    private ConversionInteractor conversionInteractor;

    private Disposable disposable;

    private ConversionView conversionView;

    public ConversionPresenterImpl(ConversionInteractor conversionInteractor){
        this.conversionInteractor = conversionInteractor;
    }

    @Override
    public void showUnits() {
        disposable = conversionInteractor.getListOfUnits()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onUnitsFetchSuccess, this::onUnitsFetchFailed);
    }

    @Override
    public void setConversionView(ConversionView conversionView) {
        this.conversionView = conversionView;
        showUnits();
    }

    @Override
    public void onButtonClick(Unit unitFrom, Unit unitTo) {
        conversionView.onSubmitButtonClick(unitFrom, unitTo);
    }

    @Override
    public void destroy() {
        conversionView = null;
        if (disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    // Success and failure methods for RX
    private void onUnitsFetchSuccess(List<Unit> unitsList) {
        if (isViewAttached()) {
            conversionView.setUpView(unitsList);
        }

    }

    private void onUnitsFetchFailed(Throwable e) {
        // nothing or show error
        Log.e(TAG, "onUnitsFetchFailed = " + e);
    }

    private boolean isViewAttached() {
        return conversionView != null;
    }

}

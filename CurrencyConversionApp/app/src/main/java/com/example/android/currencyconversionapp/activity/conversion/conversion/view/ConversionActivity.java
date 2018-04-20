package com.example.android.currencyconversionapp.activity.conversion.conversion.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.currencyconversionapp.R;

public class ConversionActivity extends AppCompatActivity {

    public static final String CONV_FRAG = "CONV_FRAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        FragmentManager manager = getSupportFragmentManager();

        ConversionFragment conversionFragment = (ConversionFragment) manager.findFragmentByTag(CONV_FRAG);

        if (conversionFragment == null){
            // if null create new
            conversionFragment = ConversionFragment.newInstance();
        }

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.root_activity_conversion, conversionFragment, CONV_FRAG);
        transaction.commit();

    }
}

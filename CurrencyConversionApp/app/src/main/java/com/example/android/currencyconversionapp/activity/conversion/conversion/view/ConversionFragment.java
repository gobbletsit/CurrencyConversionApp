package com.example.android.currencyconversionapp.activity.conversion.conversion.view;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.currencyconversionapp.R;
import com.example.android.currencyconversionapp.activity.conversion.BaseApplication;
import com.example.android.currencyconversionapp.activity.conversion.conversion.presenter.ConversionPresenter;
import com.example.android.currencyconversionapp.activity.conversion.data.Unit;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConversionFragment extends Fragment implements ConversionView {

    public static final String TAG = "ConversionFragment";

    @Inject
    ConversionPresenter conversionPresenter;

    private Spinner fromCurrencySpinner;
    private Spinner toCurrencySpinner;

    private TextView tvResult;
    private TextView tvCurrencyResult;

    private Button submitButton;

    private List<Unit> unitList = new ArrayList<>(30);

    private Unit currentUnitFrom;

    private Unit currentUnitTo;

    public ConversionFragment() {
        // Required empty public constructor
    }

    public static ConversionFragment newInstance(){
        return new ConversionFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        // create with dagger
        ((BaseApplication) getActivity().getApplication()).createConversionComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_conversion, container, false);

        // no need to use Butterknife for this
        fromCurrencySpinner = view.findViewById(R.id.spinner_from_currency);
        toCurrencySpinner = view.findViewById(R.id.spinner_to_currency);
        tvResult = view.findViewById(R.id.tv_result);
        tvCurrencyResult = view.findViewById(R.id.tv_final_conversion);
        submitButton = view.findViewById(R.id.button_submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUnitFrom != null && currentUnitTo != null){
                    conversionPresenter.onButtonClick(currentUnitFrom, currentUnitTo);
                } else {
                    Toast.makeText(getContext(), "Please select the preferred currencies", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (isNetworkAvailable()){
            conversionPresenter.setConversionView(this);
        }

    }

    @Override
    public void setUpView(List<Unit> unitList) {
        this.unitList.clear();
        // to load up the spinners
        this.unitList.addAll(unitList);

        fromCurrencySpinner.setEnabled(true);
        toCurrencySpinner.setEnabled(true);

        // setting the list of from currencies on the spinner
        ArrayAdapter<Unit> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, unitList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromCurrencySpinner.setAdapter(adapter);
        fromCurrencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // getting the item to pass for comparison in onButtonClick
                currentUnitFrom = (Unit) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // nothing
            }
        });


        toCurrencySpinner.setAdapter(adapter);
        toCurrencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // getting the item to pass for comparison in onButtonClick
                currentUnitTo = (Unit) parent.getItemAtPosition(position);
                Log.e(TAG, String.valueOf(currentUnitTo.getBuying_rate()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // nothing
            }
        });

    }

    @Override
    public void onSubmitButtonClick(Unit unitFrom, Unit unitTo) {
        BigDecimal buyingRate = unitFrom.getBuying_rate();
        BigDecimal sellingRate = unitTo.getSellingRate();
        String currencyCode = unitTo.getCurrencyCode();

        BigDecimal result = buyingRate.divide(sellingRate, 2, BigDecimal.ROUND_HALF_UP);
        tvResult.setText(String.valueOf(result));
        tvCurrencyResult.setText(currencyCode);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // for disposable unsubscribe
        conversionPresenter.destroy();

        // releasing the component
        ((BaseApplication) getActivity().getApplication()).releaseConversionComponent();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

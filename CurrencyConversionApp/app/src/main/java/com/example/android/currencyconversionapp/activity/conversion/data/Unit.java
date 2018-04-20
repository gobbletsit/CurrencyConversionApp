package com.example.android.currencyconversionapp.activity.conversion.data;

import android.graphics.Movie;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class Unit implements Parcelable{

    @SerializedName("unit_value")
    private int unitValue;

    @SerializedName("buying_rate")
    private BigDecimal buying_rate;

    @SerializedName("currency_code")
    private String currencyCode;

    @SerializedName("selling_rate")
    private BigDecimal sellingRate;

    @SerializedName("median_rate")
    private BigDecimal medianRate;

    protected Unit(Parcel in){
        unitValue = in.readInt();
        // BigDecimal is Serializable so needed to cast it
        buying_rate = (BigDecimal) in.readSerializable();
        currencyCode = in.readString();
        sellingRate = (BigDecimal) in.readSerializable();
        medianRate = (BigDecimal) in.readSerializable();

    }

    public static final Creator<Unit> CREATOR = new Creator<Unit>() {
        @Override
        public Unit createFromParcel(Parcel parcel) {
            return new Unit(parcel);
        }

        @Override
        public Unit[] newArray(int i) {
            return new Unit[i];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(unitValue);
        dest.writeSerializable(buying_rate);
        dest.writeString(currencyCode);
        dest.writeSerializable(sellingRate);
        dest.writeSerializable(medianRate);
    }

    public int getUnitValue() {
        return unitValue;
    }

    public BigDecimal getBuying_rate() {
        return buying_rate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public BigDecimal getSellingRate() {
        return sellingRate;
    }

    public BigDecimal getMedianRate() {
        return medianRate;
    }


    // override-ing so ArrayAdapter for Spinners could read it
    @Override
    public String toString() {
        return currencyCode;
    }
}

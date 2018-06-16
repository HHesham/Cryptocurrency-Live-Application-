package com.example.ahmed.cryptocurrencyliveapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Quote implements Parcelable{
    /*
    "quotes": {
                "USD": {
                    "price": 0.777915,
                    "volume_24h": 6424980.0,
                    "market_cap": 54654036.0,
                    "percent_change_1h": 0.17,
                    "percent_change_24h": 3.13,
                    "percent_change_7d": -18.8
                }
            }
    */
    private Currency USD;

    public Quote(){

    }

    public Currency getUSD() {
        return USD;
    }

    public void setUSD(Currency USD) {
        this.USD = USD;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.USD, flags);
    }

    protected Quote(Parcel in) {
        this.USD = in.readParcelable(Currency.class.getClassLoader());
    }

    public static final Creator<Quote> CREATOR = new Creator<Quote>() {
        @Override
        public Quote createFromParcel(Parcel source) {
            return new Quote(source);
        }

        @Override
        public Quote[] newArray(int size) {
            return new Quote[size];
        }
    };
}

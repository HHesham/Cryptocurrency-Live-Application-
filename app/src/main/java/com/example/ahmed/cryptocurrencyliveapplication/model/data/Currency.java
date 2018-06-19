package com.example.ahmed.cryptocurrencyliveapplication.model.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Currency implements Parcelable{

    /*
                    "price": 0.777915,
                    "volume_24h": 6424980.0,
                    "market_cap": 54654036.0,
                    "percent_change_1h": 0.17,
                    "percent_change_24h": 3.13,
                    "percent_change_7d": -18.8
    */

    private double price;
    private double volume_24h;
    private double market_cap;
    private double percent_change_1h;
    private double percent_change_24h;
    private double percent_change_7d;

    public Currency(){

    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVolume_24h() {
        return volume_24h;
    }

    public void setVolume_24h(double volume_24h) {
        this.volume_24h = volume_24h;
    }

    public double getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(double market_cap) {
        this.market_cap = market_cap;
    }

    public double getPercent_change_1h() {
        return percent_change_1h;
    }

    public void setPercent_change_1h(double percent_change_1h) {
        this.percent_change_1h = percent_change_1h;
    }

    public double getPercent_change_24h() {
        return percent_change_24h;
    }

    public void setPercent_change_24h(double percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public double getPercent_change_7d() {
        return percent_change_7d;
    }

    public void setPercent_change_7d(double percent_change_7d) {
        this.percent_change_7d = percent_change_7d;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.price);
        dest.writeDouble(this.volume_24h);
        dest.writeDouble(this.market_cap);
        dest.writeDouble(this.percent_change_1h);
        dest.writeDouble(this.percent_change_24h);
        dest.writeDouble(this.percent_change_7d);
    }

    protected Currency(Parcel in) {
        this.price = in.readDouble();
        this.volume_24h = in.readDouble();
        this.market_cap = in.readDouble();
        this.percent_change_1h = in.readDouble();
        this.percent_change_24h = in.readDouble();
        this.percent_change_7d = in.readDouble();
    }

    public static final Creator<Currency> CREATOR = new Creator<Currency>() {
        @Override
        public Currency createFromParcel(Parcel source) {
            return new Currency(source);
        }

        @Override
        public Currency[] newArray(int size) {
            return new Currency[size];
        }
    };
}

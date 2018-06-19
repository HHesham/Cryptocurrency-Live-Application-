package com.example.ahmed.cryptocurrencyliveapplication.model.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Cryptocurrency implements Parcelable, Comparable<Cryptocurrency>{
    /*
    "id": 258,
            "name": "Groestlcoin",
            "symbol": "GRS",
            "website_slug": "groestlcoin",
            "rank": 159,
            "circulating_supply": 70257079.0,
            "total_supply": 70257079.0,
            "max_supply": 105000000.0,
            "quotes": {
                "USD": {
                    "price": 0.777915,
                    "volume_24h": 6424980.0,
                    "market_cap": 54654036.0,
                    "percent_change_1h": 0.17,
                    "percent_change_24h": 3.13,
                    "percent_change_7d": -18.8
                }
            },
            "last_updated": 1529083144

    */

    private int id;
    private String name;
    private String symbol;
    private String website_slug;
    private int rank;
    private double circulating_supply;
    private double total_supply;
    private double max_supply;
    private Quote quotes;
    private long last_updated;

    public Cryptocurrency(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getWebsite_slug() {
        return website_slug;
    }

    public void setWebsite_slug(String website_slug) {
        this.website_slug = website_slug;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getCirculating_supply() {
        return circulating_supply;
    }

    public void setCirculating_supply(double circulating_supply) {
        this.circulating_supply = circulating_supply;
    }

    public double getTotal_supply() {
        return total_supply;
    }

    public void setTotal_supply(double total_supply) {
        this.total_supply = total_supply;
    }

    public double getMax_supply() {
        return max_supply;
    }

    public void setMax_supply(double max_supply) {
        this.max_supply = max_supply;
    }

    public Quote getQuotes() {
        return quotes;
    }

    public void setQuotes(Quote quotes) {
        this.quotes = quotes;
    }

    public long getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(long last_updated) {
        this.last_updated = last_updated;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.symbol);
        dest.writeString(this.website_slug);
        dest.writeInt(this.rank);
        dest.writeDouble(this.circulating_supply);
        dest.writeDouble(this.total_supply);
        dest.writeDouble(this.max_supply);
        dest.writeParcelable(this.quotes, flags);
        dest.writeLong(this.last_updated);
    }

    protected Cryptocurrency(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.symbol = in.readString();
        this.website_slug = in.readString();
        this.rank = in.readInt();
        this.circulating_supply = in.readDouble();
        this.total_supply = in.readDouble();
        this.max_supply = in.readDouble();
        this.quotes = in.readParcelable(Quote.class.getClassLoader());
        this.last_updated = in.readLong();
    }

    public static final Creator<Cryptocurrency> CREATOR = new Creator<Cryptocurrency>() {
        @Override
        public Cryptocurrency createFromParcel(Parcel source) {
            return new Cryptocurrency(source);
        }

        @Override
        public Cryptocurrency[] newArray(int size) {
            return new Cryptocurrency[size];
        }
    };

    @Override
    public int compareTo(Cryptocurrency other) {
        return this.id>other.id?1:-1;
    }
}

package com.example.ahmed.cryptocurrencyliveapplication.model;

public class Cryptocurrency {
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
}

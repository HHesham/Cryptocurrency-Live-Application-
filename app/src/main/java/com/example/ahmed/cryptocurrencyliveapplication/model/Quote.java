package com.example.ahmed.cryptocurrencyliveapplication.model;

public class Quote {
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
}

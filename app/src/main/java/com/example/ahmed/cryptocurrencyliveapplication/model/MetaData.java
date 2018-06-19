package com.example.ahmed.cryptocurrencyliveapplication.model;

public class MetaData {
    private long timestamp;
    private int num_cryptocurrencies;
    private String error;

    public MetaData(){

    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getNum_cryptocurrencies() {
        return num_cryptocurrencies;
    }

    public void setNum_cryptocurrencies(int num_cryptocurrencies) {
        this.num_cryptocurrencies = num_cryptocurrencies;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

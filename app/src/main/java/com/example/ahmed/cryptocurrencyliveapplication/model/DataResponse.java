package com.example.ahmed.cryptocurrencyliveapplication.model;

public class DataResponse {
    private Object data;
    private MetaData metadata;

    public DataResponse(){

    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public MetaData getMetadata() {
        return metadata;
    }

    public void setMetadata(MetaData metadata) {
        this.metadata = metadata;
    }
}

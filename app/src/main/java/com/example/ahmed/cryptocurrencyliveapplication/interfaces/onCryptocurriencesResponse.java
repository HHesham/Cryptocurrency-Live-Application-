package com.example.ahmed.cryptocurrencyliveapplication.interfaces;

import com.android.volley.VolleyError;
import com.example.ahmed.cryptocurrencyliveapplication.model.DataResponse;

public interface onCryptocurriencesResponse {
    void onSuccess(DataResponse dataResponse);
    void onFailure(VolleyError error);
}

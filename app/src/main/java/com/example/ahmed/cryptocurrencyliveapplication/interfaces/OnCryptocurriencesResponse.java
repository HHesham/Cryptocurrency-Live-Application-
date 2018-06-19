package com.example.ahmed.cryptocurrencyliveapplication.interfaces;

import com.android.volley.VolleyError;
import com.example.ahmed.cryptocurrencyliveapplication.model.DataResponse;

public interface OnCryptocurriencesResponse {
    void onSuccess(DataResponse dataResponse, boolean isPeriodicalRefresh);
    void onFailure(VolleyError error);
}

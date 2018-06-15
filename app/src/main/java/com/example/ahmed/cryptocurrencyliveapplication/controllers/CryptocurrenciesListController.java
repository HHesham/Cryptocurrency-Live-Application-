package com.example.ahmed.cryptocurrencyliveapplication.controllers;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.ahmed.cryptocurrencyliveapplication.interfaces.onCryptocurriencesResponse;
import com.example.ahmed.cryptocurrencyliveapplication.managers.VolleyManager;
import com.example.ahmed.cryptocurrencyliveapplication.model.Cryptocurrency;
import com.example.ahmed.cryptocurrencyliveapplication.model.DataResponse;
import com.example.ahmed.cryptocurrencyliveapplication.utilities.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CryptocurrenciesListController {

    private Context mContext;
    private String mTag;
    private onCryptocurriencesResponse mListener;


    public CryptocurrenciesListController(String tag, Context context,onCryptocurriencesResponse listener){
        mContext=context;
        mTag=tag;
        mListener=listener;
    }

    public void getCurriencies(int start, int limit, String sort, String structure){
        VolleyManager.getInstance().getCurrenciesList(start,limit, sort,structure, mContext,mTag,mListener);
    }

    public List<Cryptocurrency> castResponse(DataResponse response){
        List<Cryptocurrency> items = new ArrayList<>();
        if(response!=null){
            Gson gs = new Gson();
            String js = gs.toJson(response.getData());
            Cryptocurrency[] cryptocurrencies = gs.fromJson(js, Cryptocurrency[].class);
            for (int i=0;i<cryptocurrencies.length;i++){
                items.add(cryptocurrencies[i]);
            }
        }
        return items;
    }
}

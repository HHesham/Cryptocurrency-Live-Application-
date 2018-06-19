package com.example.ahmed.cryptocurrencyliveapplication.controllers;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.example.ahmed.cryptocurrencyliveapplication.R;
import com.example.ahmed.cryptocurrencyliveapplication.interfaces.OnCryptocurriencesResponse;
import com.example.ahmed.cryptocurrencyliveapplication.managers.VolleyManager;
import com.example.ahmed.cryptocurrencyliveapplication.model.data.Cryptocurrency;
import com.example.ahmed.cryptocurrencyliveapplication.model.responces.DataResponse;
import com.example.ahmed.cryptocurrencyliveapplication.utilities.Helper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CryptocurrenciesListController {

    private Context mContext;
    private String mTag;
    private OnCryptocurriencesResponse mListener;


    public CryptocurrenciesListController(String tag, Context context,OnCryptocurriencesResponse listener){
        mContext=context;
        mTag=tag;
        mListener=listener;
    }

    public void getCurriencies(int start, int limit, String sort, String structure, ProgressDialog dialog, boolean isPeriodicalRefresh){
        if(!Helper.isNetworkAvailable(mContext)){
            Toast.makeText(mContext, mContext.getResources().getString(R.string.check_connection), Toast.LENGTH_SHORT).show();
            return;
        }
        dialog.show();
        VolleyManager.getInstance().getCurrenciesList(start,limit, sort,structure, mContext,mTag,mListener, isPeriodicalRefresh);
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

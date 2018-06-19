package com.example.ahmed.cryptocurrencyliveapplication.managers;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.ahmed.cryptocurrencyliveapplication.interfaces.OnCryptocurriencesResponse;
import com.example.ahmed.cryptocurrencyliveapplication.model.responces.DataResponse;
import com.example.ahmed.cryptocurrencyliveapplication.utilities.Constants;

import java.util.HashMap;
import java.util.Map;

public class VolleyManager {

    static VolleyManager instance;

    public VolleyManager(){

    }

    public static synchronized VolleyManager getInstance(){
        if(instance==null)
            instance=new VolleyManager ();
        return instance;
    }

    /**
     * sample request
     * https://api.coinmarketcap.com/v2/ticker/?start=101&limit=10&sort=id&structure=array
     */
    public void getCurrenciesList(int start, int limit, String sort, String structure, Context context, String TAG,
                                  final OnCryptocurriencesResponse mListener, final boolean isPeriodicalRefresh){
        String url = Constants.GET_CURRENCIES_URL;
        Map<String, String> headers = new HashMap<String, String>();
        Map<String, String> params = new HashMap<String, String>();
        url+="?start="+start;
        url+="&limit="+limit;
        url+="&sort="+sort;
        url+="&structure="+structure;
        NetworkManager networkManager = NetworkManager.getInstance(context);
        BaseRequest request = new BaseRequest<>( Request.Method.GET, url, DataResponse.class, headers, params, new Response.Listener<DataResponse>() {
            @Override
            public void onResponse(DataResponse response) {
                mListener.onSuccess(response, isPeriodicalRefresh);
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                mListener.onFailure(error);
            }
        });
        networkManager.addToRequestQueue(request, TAG);
    }

    /**
     * not used in this context
     * sample request
     * https://api.coinmarketcap.com/v2/ticker/1
     */
    public void getCurrency(int id, Context context, String TAG, final OnCryptocurriencesResponse mListener, final boolean isPeriodicalRefresh){
        String url = Constants.GET_CURRENCIES_URL;
        Map<String, String> headers = new HashMap<String, String>();
        Map<String, String> params = new HashMap<String, String>();
        url+="?id="+id;
        NetworkManager networkManager = NetworkManager.getInstance(context);
        BaseRequest request = new BaseRequest<>( Request.Method.GET, url, DataResponse.class, headers, params, new Response.Listener<DataResponse>() {
            @Override
            public void onResponse(DataResponse response) {
                mListener.onSuccess(response, isPeriodicalRefresh);
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                mListener.onFailure(error);
            }
        });
        networkManager.addToRequestQueue(request, TAG);
    }
}

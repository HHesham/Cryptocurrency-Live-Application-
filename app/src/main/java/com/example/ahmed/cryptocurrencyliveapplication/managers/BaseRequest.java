package com.example.ahmed.cryptocurrencyliveapplication.managers;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.util.Map;

public class BaseRequest<T> extends Request<T> {
    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Map<String, String> headers;
    private final Map<String, String> params;
    private final Response.Listener<T> listener;
    private int timeoutRequest = 0;
    private String jsonBody;


    private void setTimeout() {
        this.setRetryPolicy(
                new DefaultRetryPolicy(
                        timeoutRequest,
                        3,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    /**
     * Make a GET or Post depends on Method request and return a parsed object from JSON.
     *
     * @param url     URL of the request to make
     * @param clazz   Relevant class object, for Gson's reflection  "if it is null then return its string response"
     * @param headers Map of request headers
     */
    public BaseRequest(int requetsMethod, String url, Class<T> clazz, Map<String, String> headers,
                       Response.Listener<T> listener, String json, Response.ErrorListener errorListener) {
        super(requetsMethod, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
        this.params = null;
        this.jsonBody = json;
        setTimeout();
        setShouldCache(true);
    }

    /**
     * Make a GET or Post depends on Method request and return a parsed object from JSON.
     *
     * @param url     URL of the request to make
     * @param clazz   Relevant class object, for Gson's reflection  "if it is null then return its string response"
     * @param headers Map of request headers
     */
    public BaseRequest(int requetsMethod, String url, Class<T> clazz, Map<String, String> headers, Map<String, String> params,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(requetsMethod, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
        this.params = params;
        this.jsonBody = null;
        setTimeout();
        setShouldCache(true);
    }

    /**
     * for upload the multipart parameters for uploading images
     *
     * @param requetsMethod like Method.POST, ...
     * @param url           requested url
     * @param clazz         response class
     * @param headers       headers for the request
     * @param listener      response listner
     * @param errorListener
     *
     */
    public BaseRequest(int requetsMethod, String url, Class<T> clazz, Map<String, String> headers,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(requetsMethod, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
        this.params = null;
        this.jsonBody = null;

        setTimeout();
        setShouldCache(true);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    public Map<String, String> getParams() throws AuthFailureError {
        return params != null ? params : super.getHeaders();
    }

    @Override
    protected String getParamsEncoding() {
        return super.getParamsEncoding();
    }



    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {

        if(this.jsonBody != null) {
            try {
                String json = new String(
                        response.data,
                        HttpHeaderParser.parseCharset(response.headers));

                if (clazz != null)
                    return Response.success(
                            gson.fromJson(json, clazz),
                            HttpHeaderParser.parseCacheHeaders(response));
                else
                    return Response.success(
                            json,
                            HttpHeaderParser.parseCacheHeaders(response));
            } catch (Exception e) {
                return Response.error(new ParseError(e));
            }

//            return DataResponse.success(response.data, HttpHeaderParser.parseCacheHeaders(response));
        }
        try {

            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            if (clazz != null)
                return Response.success(
                        gson.fromJson(json, clazz),
                        HttpHeaderParser.parseCacheHeaders(response));
            else
                return Response.success(
                        json,
                        HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    public byte[] getBody() throws AuthFailureError {

          if(this.jsonBody != null){
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] bytes = jsonBody.getBytes();
            bos.write(bytes, 0, bytes.length);
            return bos.toByteArray();
        }else
            return super.getBody();
    }

    @Override
    public String getBodyContentType() {
         if(jsonBody != null){
            return "application/json";
        }else
            return super.getBodyContentType();
    }
}
package com.example.ahmed.cryptocurrencyliveapplication;


import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;

import com.android.volley.VolleyError;
import com.example.ahmed.cryptocurrencyliveapplication.interfaces.OnCryptocurriencesResponse;
import com.example.ahmed.cryptocurrencyliveapplication.managers.VolleyManager;
import com.example.ahmed.cryptocurrencyliveapplication.model.data.Cryptocurrency;
import com.example.ahmed.cryptocurrencyliveapplication.model.responces.DataResponse;
import com.example.ahmed.cryptocurrencyliveapplication.utilities.Constants;
import com.google.gson.Gson;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

/**
 * Created by InovaPC on 2/22/18.
 */
@RunWith(AndroidJUnit4.class)
@MediumTest
public class VolleyManagerTest {

    private Context context = InstrumentationRegistry.getTargetContext();

    @Test
    public void cryptoCurrenciesSizeTest() throws Exception {
        int start = 0;
        int limit = Constants.PAGE_SIZE;
        final int[] test = new int[1];
        final CountDownLatch signal = new CountDownLatch(1);

        VolleyManager.getInstance().getCurrenciesList(start, limit, Constants.SORT_VALUE, Constants.STRUCTURE_VALUE,
                context, "", new OnCryptocurriencesResponse() {
                    @Override
                    public void onSuccess(DataResponse dataResponse,boolean isPeriodically) {
                        try {
                            Gson gs = new Gson();
                            String js = gs.toJson(dataResponse.getData());
                            Cryptocurrency[] currencies = gs.fromJson(js, Cryptocurrency[].class);
                            test[0] = currencies.length;
                            signal.countDown();// notify the count down latch
                        }catch (Exception e){
                            test[0] = -1;
                            signal.countDown();// notify the count down latch
                        }
                    }
                    @Override
                    public void onFailure(VolleyError error) {
                        test[0] = -1;
                        signal.countDown();// notify the count down latch

                    }
                },false);
        signal.await();// wait for callback
        Assert.assertEquals(test[0], Constants.PAGE_SIZE);
    }

    @Test
    public void cryptoCurrenciesModifiedSizeTest() throws Exception {
        int start = 2;
        int limit = Constants.PAGE_SIZE+10;
        final int[] test = new int[1];
        final CountDownLatch signal = new CountDownLatch(1);

        VolleyManager.getInstance().getCurrenciesList(start, limit, Constants.SORT_VALUE, Constants.STRUCTURE_VALUE,
                context, "", new OnCryptocurriencesResponse() {
                    @Override
                    public void onSuccess(DataResponse dataResponse,boolean isPeriodically) {
                        try {
                            Gson gs = new Gson();
                            String js = gs.toJson(dataResponse.getData());
                            Cryptocurrency[] currencies = gs.fromJson(js, Cryptocurrency[].class);
                            test[0] = currencies.length;
                            signal.countDown();// notify the count down latch
                        }catch (Exception e){
                            test[0] = -1;
                            signal.countDown();// notify the count down latch
                        }
                    }
                    @Override
                    public void onFailure(VolleyError error) {
                        test[0] = -1;
                        signal.countDown();// notify the count down latch

                    }
                },false);
        signal.await();// wait for callback
        Assert.assertEquals(test[0], Constants.PAGE_SIZE+10);
    }

    @Test
    public void cryptoCurrenciesMaxSizeTest() throws Exception {
        int start = 2;
        int limit = Constants.PAGE_SIZE+10;
        final int[] test = new int[1];
        final CountDownLatch signal = new CountDownLatch(1);

        VolleyManager.getInstance().getCurrenciesList(-1, -1, "", Constants.STRUCTURE_VALUE,
                context, "", new OnCryptocurriencesResponse() {
                    @Override
                    public void onSuccess(DataResponse dataResponse, boolean isPeriodically) {
                        try {
                            Gson gs = new Gson();
                            String js = gs.toJson(dataResponse.getData());
                            Cryptocurrency[] currencies = gs.fromJson(js, Cryptocurrency[].class);
                            test[0] = currencies.length;
                            signal.countDown();// notify the count down latch
                        }catch (Exception e){
                            test[0] = -1;
                            signal.countDown();// notify the count down latch
                        }
                    }
                    @Override
                    public void onFailure(VolleyError error) {
                        test[0] = -1;
                        signal.countDown();// notify the count down latch

                    }
                },false);
        signal.await();// wait for callback
        Assert.assertEquals(test[0], 100);
    }
}
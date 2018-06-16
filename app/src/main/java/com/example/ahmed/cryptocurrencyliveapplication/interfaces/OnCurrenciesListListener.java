package com.example.ahmed.cryptocurrencyliveapplication.interfaces;

import android.view.View;

import com.example.ahmed.cryptocurrencyliveapplication.model.Cryptocurrency;

public interface OnCurrenciesListListener {

    void onLongPress();
    void onItemClicked(Cryptocurrency cryptocurrency);
}

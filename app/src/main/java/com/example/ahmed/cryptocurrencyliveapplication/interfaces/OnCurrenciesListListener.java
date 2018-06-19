package com.example.ahmed.cryptocurrencyliveapplication.interfaces;

import com.example.ahmed.cryptocurrencyliveapplication.model.data.Cryptocurrency;

public interface OnCurrenciesListListener {

    void onLongPress();
    void onItemClicked(Cryptocurrency cryptocurrency);
}

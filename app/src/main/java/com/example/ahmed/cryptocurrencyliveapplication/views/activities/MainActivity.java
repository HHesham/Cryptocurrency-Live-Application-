package com.example.ahmed.cryptocurrencyliveapplication.views.activities;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ahmed.cryptocurrencyliveapplication.R;
import com.example.ahmed.cryptocurrencyliveapplication.interfaces.InterfaceCryptocurrenciesList;
import com.example.ahmed.cryptocurrencyliveapplication.model.data.Cryptocurrency;
import com.example.ahmed.cryptocurrencyliveapplication.utilities.Constants;
import com.example.ahmed.cryptocurrencyliveapplication.utilities.Helper;
import com.example.ahmed.cryptocurrencyliveapplication.views.fragments.BarChartFragment;
import com.example.ahmed.cryptocurrencyliveapplication.views.fragments.CalculatorFragment;
import com.example.ahmed.cryptocurrencyliveapplication.views.fragments.CryptocurrenciesListFragment;
import com.example.ahmed.cryptocurrencyliveapplication.views.fragments.MyFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity implements InterfaceCryptocurrenciesList {

    private Menu mMenu;

    //timer for refresh data.
    private final static int INTERVAL = 1000 * 60 *Constants.REFRESH_MINS; //5 minutes
    Handler mHandler = new Handler();

    Runnable mHandlerTask = new Runnable() {
        @Override
        public void run() {
            refreshData();
            mHandler.postDelayed(mHandlerTask, INTERVAL);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_back);
        this.getSupportFragmentManager()
                .beginTransaction().replace(R.id.content_layout, CryptocurrenciesListFragment.newInstance())
                .commit();
        mHandlerTask.run();

    }

    private void refreshData(){
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.content_layout);
        if(f instanceof MyFragment){
            ((MyFragment) f).refreshData();
        }
    }

    @Override
    public void openCalculator(Cryptocurrency cryptocurrency){
        this.getSupportFragmentManager()
                .beginTransaction().replace(R.id.content_layout, CalculatorFragment.newInstance(cryptocurrency))
                .addToBackStack(null)
                .commit();
    }

    public void setChartVisibilty(boolean isVisiable){
        if(mMenu!=null){
            mMenu.findItem(R.id.bar_chart).setVisible(isVisiable);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        menu.findItem(R.id.bar_chart).setVisible(false);
        mMenu=menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.bar_chart:
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.content_layout);
                if(f instanceof CryptocurrenciesListFragment){
                    Hashtable<Integer, Cryptocurrency> hashtable = ((CryptocurrenciesListFragment) f).getAdapter().getHashtable();
                    ArrayList<Cryptocurrency> arr = new ArrayList<Cryptocurrency>(hashtable.values());
                    Cryptocurrency[] myArray = arr.toArray(new Cryptocurrency[arr.size()]);
                    Arrays.sort(myArray);
                    if(myArray.length<2){
                        Toast.makeText(this, getResources().getText(R.string.invalid_parametesr),Toast.LENGTH_SHORT).show();
                    }else {
                        this.getSupportFragmentManager()
                                .beginTransaction().hide(f).add(R.id.content_layout, BarChartFragment.newInstance(myArray))
                                .addToBackStack(null)
                                .commit();
                    }
                }
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Helper.hideKeyboard(this);
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.content_layout);
        if(f instanceof CryptocurrenciesListFragment){
            CryptocurrenciesListFragment fragment = ((CryptocurrenciesListFragment) f);
            if(fragment.getAdapter().isSelectionMode())
                fragment.onBackPressed();
            else
                super.onBackPressed();
        }else {
            super.onBackPressed();
            if(f instanceof BarChartFragment){
                setChartVisibilty(true);
            }
        }
        Helper.hideKeyboard(this);
    }
}

package com.example.ahmed.cryptocurrencyliveapplication.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmed.cryptocurrencyliveapplication.R;
import com.example.ahmed.cryptocurrencyliveapplication.model.Cryptocurrency;
import com.example.ahmed.cryptocurrencyliveapplication.utilities.Helper;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link CalculatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculatorFragment extends MyFragment implements View.OnClickListener{

    //TODO add service to update value each 5 mins

    private Context mContext;
    private TextView mToCurrencyVal;
    private TextView mToCurrencyText;
    private TextView mToUSDVal;
    private TextView mToUSDText;
    private EditText mToCurrEditText;
    private EditText mToUSDEditText;
    private TextView mConvertToCurrency;
    private Button mToUSDBtn;
    private Button mToCurrencyBtn;
    private LinearLayout mLayout;
    private Cryptocurrency mCryptoCurrency;
    private static final String CRYPTOCURRENCY_KEY="cryptocurrency_key";

    public CalculatorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment CalculatorFragment.
     */
    public static CalculatorFragment newInstance(Cryptocurrency currency) {
        CalculatorFragment fragment = new CalculatorFragment();
        Bundle args = new Bundle();
        args.putParcelable(CRYPTOCURRENCY_KEY, currency);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCryptoCurrency = getArguments().getParcelable(CRYPTOCURRENCY_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_calculator, container, false);
        initView(view);
        fillFields();
        setToolBar();
        return view;
    }

    @Override
    public void initView(View view){
        mContext = this.getContext();
        mLayout = (LinearLayout) view.findViewById(R.id.layout);
        mToCurrencyVal = (TextView) view.findViewById(R.id.to_currency_value);
        mToCurrencyText= (TextView)view.findViewById(R.id.to_currency_text);
        mToUSDVal= (TextView)view.findViewById(R.id.to_usd_value);
        mToUSDText= (TextView)view.findViewById(R.id.to_usd_currency_text);
        mConvertToCurrency= (TextView)view.findViewById(R.id.to_currency_title);
        mToCurrEditText= (EditText) view.findViewById(R.id.to_currency_edit_text);
        mToUSDEditText= (EditText) view.findViewById(R.id.to_usd_edit_text);
        mToCurrencyBtn = (Button) view.findViewById(R.id.to_currency_convert_btn);
        mToUSDBtn = (Button) view.findViewById(R.id.to_usd_convert_btn);
        mToCurrencyBtn.setOnClickListener(this);
        mToUSDBtn.setOnClickListener(this);
        mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.hideKeyboard(getActivity());
            }
        });
    }

    private void updateValue(TextView view, double val){
        view.setText(Helper.formateDouble(val));
    }

    @Override
    public void onClick(View view){
        double val;
        switch (view.getId()){
            case R.id.to_currency_convert_btn:
                Helper.hideKeyboard(getActivity());
                if(mToCurrEditText.getText()==null || mToCurrEditText.getText().toString().isEmpty()){
                    Toast.makeText(mContext, getResources().getText(R.string.enter_amount_first), Toast.LENGTH_SHORT).show();
                }else {
                    val = Double.parseDouble(mToCurrEditText.getText().toString()) / mCryptoCurrency.getQuotes().getUSD().getPrice();
                    updateValue(mToCurrencyVal, val);
                }
                break;
            case R.id.to_usd_convert_btn:
                Helper.hideKeyboard(getActivity());
                if(mToUSDEditText.getText()==null || mToUSDEditText.getText().toString().isEmpty()){
                    Toast.makeText(mContext, getResources().getText(R.string.enter_amount_first), Toast.LENGTH_SHORT).show();
                }else {
                    val = 1.0 * mCryptoCurrency.getQuotes().getUSD().getPrice() * Double.parseDouble(mToUSDEditText.getText().toString());
                    updateValue(mToUSDVal, val);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void fillFields(){
        String title = getResources().getText(R.string.to_currency)+" "+mCryptoCurrency.getName();
        mConvertToCurrency.setText(title);
        mToCurrencyText.setText(mCryptoCurrency.getName());
        mToUSDText.setText(mCryptoCurrency.getName());
        double usdToCurrency =1.0/mCryptoCurrency.getQuotes().getUSD().getPrice();
        updateValue(mToUSDVal, mCryptoCurrency.getQuotes().getUSD().getPrice());
        updateValue(mToCurrencyVal, usdToCurrency);
    }

    @Override
    public void setToolBar(){
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.my_toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(R.string.calculator_title);
        android.support.v7.app.ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void refreshData(){
        //if we want to refresh data in the calculator
    }
}

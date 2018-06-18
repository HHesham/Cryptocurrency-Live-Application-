package com.example.ahmed.cryptocurrencyliveapplication.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.ahmed.cryptocurrencyliveapplication.R;
import com.example.ahmed.cryptocurrencyliveapplication.model.Cryptocurrency;
import com.example.ahmed.cryptocurrencyliveapplication.utilities.Constants;
import com.example.ahmed.cryptocurrencyliveapplication.utilities.IntValueFormatter;
import com.example.ahmed.cryptocurrencyliveapplication.utilities.LabelFormatter;
import com.example.ahmed.cryptocurrencyliveapplication.views.activities.MainActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

public class BarChartFragment extends MyFragment {
    private static final String ITEMS_TAG = "param1";
    private Context mContext;
    private BarChart mBarChart;
    private Cryptocurrency[] mItems;

    public BarChartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment BarChartFragment.
     */
    public static BarChartFragment newInstance(Cryptocurrency[] items) {
        BarChartFragment fragment = new BarChartFragment();
        Bundle args = new Bundle();
        args.putParcelableArray(ITEMS_TAG, items);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mItems = (Cryptocurrency[])getArguments().getParcelableArray(ITEMS_TAG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bar_chart, container, false);
        initView(view);
        setToolBar();
        return view;
    }

    @Override
    public void setToolBar(){
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.my_toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(R.string.cryptocurrency_title);
        android.support.v7.app.ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        ((MainActivity)getActivity()).setChartVisibilty(false);

    }

    @Override
    public void initView(View view){
        int xAxixCount = mItems.length;
        int yAxixCount =6;
        String []labels =new String[mItems.length];
        double maxValue = Double.MIN_VALUE;
        for(int i=mItems.length-1;i>=0;i--){
            labels[i]=mItems[i].getName();
            if(mItems[i].getQuotes().getUSD().getPrice()>maxValue)
                maxValue=mItems[i].getQuotes().getUSD().getPrice();
        }
        mContext=this.getContext();
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mBarChart = view.findViewById(R.id.chart);
        mBarChart.setDrawBarShadow(false);
        mBarChart.setDrawValueAboveBar(true);
        mBarChart.getDescription().setEnabled(false);
        mBarChart.setMaxVisibleValueCount(Constants.MAX_BARS);
        // scaling can now only be done on x- and y-axis separately
        mBarChart.setPinchZoom(false);
        mBarChart.setDrawGridBackground(false);
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new LabelFormatter(labels));
        xAxis.setLabelCount(xAxixCount, false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);

        YAxis leftAxis = mBarChart.getAxisLeft();
        YAxis rightAxis = mBarChart.getAxisRight();

        leftAxis.setDrawGridLines(false);
        leftAxis.setLabelCount(yAxixCount, true);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f);

        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(yAxixCount, true);
        rightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinimum(0f);

        // count = number of bars, range = max value
        setData(mItems.length, maxValue);
    }


    private void setData(int count, double range) {
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        for (int i=0;i<mItems.length;i++){
            yVals1.add(new BarEntry(i, (float)(mItems[i].getQuotes().getUSD().getPrice())));
        }
        BarDataSet set;
        if (mBarChart.getData() != null && mBarChart.getData().getDataSetCount() > 0) {
            set = (BarDataSet) mBarChart.getData().getDataSetByIndex(0);
            set.setValues(yVals1);
            mBarChart.getData().notifyDataChanged();
            mBarChart.notifyDataSetChanged();
        } else {
            set = new BarDataSet(yVals1, "");
            set.setDrawIcons(false);

            int startColor1 = ContextCompat.getColor(mContext, android.R.color.holo_orange_light);
            int startColor2 = ContextCompat.getColor(mContext, android.R.color.holo_blue_light);
            int startColor3 = ContextCompat.getColor(mContext, android.R.color.holo_orange_light);
            int startColor4 = ContextCompat.getColor(mContext, android.R.color.holo_green_light);
            int startColor5 = ContextCompat.getColor(mContext, android.R.color.holo_red_light);

            List<Integer> gradientColors = new ArrayList<>();
            gradientColors.add(startColor1);
            gradientColors.add(startColor2);
            gradientColors.add(startColor3);
            gradientColors.add(startColor4);
            gradientColors.add(startColor5);

            set.setColors(gradientColors);
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);
            set.setValueFormatter(new IntValueFormatter());

            mBarChart.setData(data);
        }
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

    }
}

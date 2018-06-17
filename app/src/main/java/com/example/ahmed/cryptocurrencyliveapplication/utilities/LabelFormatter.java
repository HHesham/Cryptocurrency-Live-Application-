package com.example.ahmed.cryptocurrencyliveapplication.utilities;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

public class LabelFormatter implements IAxisValueFormatter {
    private final String[] mLabels;
    private int index=0;

    public LabelFormatter(String[] labels) {
        mLabels = labels;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
//        String str = mLabels[index%mLabels.length];
//        index++;
//        return str;
        return mLabels[(int)value];
    }


}
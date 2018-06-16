package com.example.ahmed.cryptocurrencyliveapplication.adapters;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmed.cryptocurrencyliveapplication.R;
import com.example.ahmed.cryptocurrencyliveapplication.interfaces.OnCurrenciesListListener;
import com.example.ahmed.cryptocurrencyliveapplication.model.Cryptocurrency;

import java.util.Hashtable;
import java.util.List;

public class CryptocurrencyListAdapter extends RecyclerView.Adapter<CryptocurrencyListAdapter.ViewHolder> {

    private String TAG;
    private Activity mActivity;
    private final List<Cryptocurrency> mValues;
    private OnCurrenciesListListener mListener;
    private boolean isSelectionMode;
    private Hashtable<Integer, Cryptocurrency> hashtable = new Hashtable<>();

    public Hashtable<Integer, Cryptocurrency> getHashtable() {
        return hashtable;
    }

    public void setHashtable(Hashtable<Integer, Cryptocurrency> hashtable) {
        this.hashtable = hashtable;
    }

    public boolean isSelectionMode() {
        return isSelectionMode;
    }

    public void setSelectionMode(boolean selectionMode) {
        isSelectionMode = selectionMode;
    }

    public CryptocurrencyListAdapter(List<Cryptocurrency> items, String TAG, Activity activity, OnCurrenciesListListener listener) {
        mValues = items;
        mListener=listener;
        this.TAG = TAG;
        this.mActivity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_cryptocurrency_cell, parent, false);
        return new ViewHolder(view);
    }

    private void selectItem(Cryptocurrency cryptocurrency, View view){
        ImageView checkImg = (ImageView)(view.findViewById(R.id.selection_box));
        if(hashtable.containsKey(cryptocurrency.getId())){
            hashtable.remove(cryptocurrency.getId());
            checkImg.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.ic_check_box_outline_blank_black_24dp));
        }else {
            hashtable.put(cryptocurrency.getId(),cryptocurrency);
            checkImg.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.ic_check_box_black_24dp));
        }
    }

    public void removeSelectionMode(){
        isSelectionMode=false;
        hashtable.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mCurrencyText.setText(mValues.get(position).getName());
        if(isSelectionMode){
            holder.mCheckImage.setVisibility(View.VISIBLE);
        }else {
            holder.mCheckImage.setVisibility(View.GONE);
        }
        if(hashtable.containsKey(holder.mItem.getId())){
            holder.mCheckImage.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.ic_check_box_black_24dp));
        }else {
            holder.mCheckImage.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.ic_check_box_outline_blank_black_24dp));
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelectionMode){
                    selectItem(mValues.get(position), holder.mView);
                }else {
                    mListener.onItemClicked(mValues.get(position));
                }
            }
        });
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                isSelectionMode=true;
                mListener.onLongPress();
                selectItem(mValues.get(position),v);
                notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public Cryptocurrency mItem;
        public TextView mCurrencyText;
        public ImageView mCheckImage;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mCurrencyText=(TextView)view.findViewById(R.id.currency_text);
            mCheckImage=(ImageView)view.findViewById(R.id.selection_box);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}


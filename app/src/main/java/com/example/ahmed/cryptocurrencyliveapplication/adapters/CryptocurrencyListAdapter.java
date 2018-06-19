package com.example.ahmed.cryptocurrencyliveapplication.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        String priceStr = mActivity.getResources().getString(R.string.price)+": "+mValues.get(position).getQuotes().getUSD().getPrice()
                + " "+mActivity.getResources().getString(R.string.usd);
        String marketStr = mActivity.getResources().getString(R.string.market)+": "+mValues.get(position).getQuotes().getUSD().getMarket_cap()
                + " "+mActivity.getResources().getString(R.string.usd);
        String volumeStr = mActivity.getResources().getString(R.string.volume)+": "+mValues.get(position).getQuotes().getUSD().getVolume_24h()
                + " "+mActivity.getResources().getString(R.string.usd);
        holder.mPriceText.setText(priceStr);
        holder.mMarketCapText.setText(marketStr);
        holder.mVolumText.setText(volumeStr);

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
        public TextView mPriceText;
        public TextView mVolumText;
        public TextView mMarketCapText;

        public ImageView mCheckImage;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mPriceText=(TextView)view.findViewById(R.id.price_text);
            mVolumText=(TextView)view.findViewById(R.id.volume_24h_text);
            mMarketCapText=(TextView)view.findViewById(R.id.market_cap_text);
            mCurrencyText=(TextView)view.findViewById(R.id.currency_text);
            mCheckImage=(ImageView)view.findViewById(R.id.selection_box);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}


package com.example.ahmed.cryptocurrencyliveapplication.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.ahmed.cryptocurrencyliveapplication.R;
import com.example.ahmed.cryptocurrencyliveapplication.adapters.CryptocurrencyListAdapter;
import com.example.ahmed.cryptocurrencyliveapplication.controllers.CryptocurrenciesListController;
import com.example.ahmed.cryptocurrencyliveapplication.interfaces.onCryptocurriencesResponse;
import com.example.ahmed.cryptocurrencyliveapplication.model.Cryptocurrency;
import com.example.ahmed.cryptocurrencyliveapplication.model.DataResponse;
import com.example.ahmed.cryptocurrencyliveapplication.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link CryptocurrenciesListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CryptocurrenciesListFragment extends Fragment implements onCryptocurriencesResponse {

    private Context mContext;
    private RecyclerView mDoctorsList;
    private List<Cryptocurrency> items;
    private LinearLayoutManager layoutManager;
    private CryptocurrencyListAdapter mCryptocurrencyListAdapter;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int mPage=-1;
    private CryptocurrenciesListController mController;
    public static final String TAG = CryptocurrenciesListFragment.class.getSimpleName();

    public CryptocurrenciesListFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment CryptocurrenciesListFragment.
     */
    public static CryptocurrenciesListFragment newInstance() {
        CryptocurrenciesListFragment fragment = new CryptocurrenciesListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cryptocurrencies_list, container, false);
        initView(view);
        setToolBar();
        return view;
    }

    private void initView(View view){
        mContext = this.getContext();
        mDoctorsList = (RecyclerView) view.findViewById(R.id.list);
        layoutManager = new LinearLayoutManager(mContext);
        mDoctorsList.setLayoutManager(layoutManager);
        items = new ArrayList<>();
        mCryptocurrencyListAdapter = new CryptocurrencyListAdapter(items, TAG,getActivity());
        mDoctorsList.setAdapter(mCryptocurrencyListAdapter);
        mDoctorsList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        mController=new CryptocurrenciesListController(TAG, mContext, this);
        mController.getCurriencies(0,10, Constants.SORT_VALUE,Constants.STRUCTURE_VALUE);
//        DividerItemDecoration divider = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
//        divider.setDrawable(ContextCompat.getDrawable(mContext, R.drawable.account_list_divider));
//        mDoctorsList.addItemDecoration(divider);
    }

    private void setToolBar(){
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.my_toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(R.string.cryptocurrency_title);
        android.support.v7.app.ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
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
    public void onSuccess(DataResponse dataResponse){
        List<Cryptocurrency> cryptocurrencies= mController.castResponse(dataResponse);
        for(int i=0;i<cryptocurrencies.size();i++)
            items.add(cryptocurrencies.get(i));
        mCryptocurrencyListAdapter.notifyDataSetChanged();
        Toast.makeText(mContext,getResources().getText(R.string.data_fetched_successfully),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(VolleyError error){

    }
}

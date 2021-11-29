package com.example.appcompanypets.ui.produto;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.appcompanypets.Api.Produto.AsyncProduto;
import com.example.appcompanypets.R;
import com.example.appcompanypets.RecyclerViewClickListener;

public class ProdutoFragment extends Fragment
{
    private FragmentActivity context;
    RecyclerView recyclerViewProduto;

    public ProdutoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity)
    {
        context = (FragmentActivity) activity;

        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_cadastro1, container, false);

        recyclerViewProduto = view.findViewById(R.id.recyclerViewProduto);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerViewProduto.setLayoutManager(layoutManager);

        AsyncProduto asyncProduto = new AsyncProduto(recyclerViewProduto, ".JPEG");
        asyncProduto.execute();

        recyclerViewProduto.addOnItemTouchListener(new RecyclerViewClickListener(context, recyclerViewProduto,
                new RecyclerViewClickListener.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(View view, int position) {

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }));

        return view;
    }
}
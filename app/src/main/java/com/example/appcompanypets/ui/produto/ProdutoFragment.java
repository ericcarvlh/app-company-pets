package com.example.appcompanypets.ui.produto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.appcompanypets.Activities.DetalhesProdutoActivity;
import com.example.appcompanypets.Api.Produto.AsyncProduto;
import com.example.appcompanypets.DTO.DtoProduto;
import com.example.appcompanypets.R;
import com.example.appcompanypets.RecyclerItemClickListener;
import com.example.appcompanypets.RecyclerViewAdapter;

import java.io.Serializable;
import java.util.ArrayList;

public class ProdutoFragment extends Fragment implements Serializable
{
    private FragmentActivity context;
    RecyclerView recyclerViewProduto;
    static ArrayList<DtoProduto> arrayListProduto = new ArrayList<>();
    DtoProduto dto = new DtoProduto();

    public ProdutoFragment()
    {
        // Required empty public constructor
    }

    public ProdutoFragment(ArrayList<DtoProduto> arrayListProduto)
    {
        this.arrayListProduto = arrayListProduto;
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
        View view = inflater.inflate(R.layout.fragment_produto, container, false);

        recyclerViewProduto = view.findViewById(R.id.recyclerViewProduto);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerViewProduto.setLayoutManager(layoutManager);

        AsyncProduto asyncProduto = new AsyncProduto(recyclerViewProduto);
        asyncProduto.execute();

        recyclerViewProduto.addOnItemTouchListener(new RecyclerItemClickListener(context, recyclerViewProduto,
                new RecyclerItemClickListener.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(View view, int position)
                    {
                        dto.setCd_Produto(arrayListProduto.get(position).getCd_Produto());
                        dto.setCd_Categoria(arrayListProduto.get(position).getCd_Categoria());
                        dto.setDs_Produto(arrayListProduto.get(position).getDs_Produto());
                        dto.setNm_Marca(arrayListProduto.get(position).getNm_Marca());
                        dto.setVl_Produto(arrayListProduto.get(position).getVl_Produto());
                        dto.setNm_Produto(arrayListProduto.get(position).getNm_Produto());
                        dto.setQt_Estoque(arrayListProduto.get(position).getQt_Estoque());
                        dto.setDs_Foto(arrayListProduto.get(position).getDs_Foto());

                        Intent intent = new Intent(context, DetalhesProdutoActivity.class);
                        intent.putExtra("dto", dto);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position)
                    {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {

                    }
                }));

        return view;
    }
}
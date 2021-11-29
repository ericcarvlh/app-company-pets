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
import android.widget.Toast;

import com.example.appcompanypets.Api.Produto.AsyncProduto;
import com.example.appcompanypets.DTO.DtoProduto;
import com.example.appcompanypets.DTO.DtoUsuario;
import com.example.appcompanypets.R;
import com.example.appcompanypets.RecyclerViewClickListener;
import com.example.appcompanypets.ui.cadastro.CadastroFragment3;

import java.util.ArrayList;

public class ProdutoFragment extends Fragment
{
    private FragmentActivity context;
    RecyclerView recyclerViewProduto;
    ArrayList<DtoProduto> arrayListProduto = new ArrayList<>();
    RecyclerView recyclerViewFilme;
    DtoProduto dto = new DtoProduto();

    public ProdutoFragment()
    {
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
        View view = inflater.inflate(R.layout.fragment_produto, container, false);

        recyclerViewProduto = view.findViewById(R.id.recyclerViewProduto);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerViewProduto.setLayoutManager(layoutManager);

        AsyncProduto asyncProduto = new AsyncProduto(recyclerViewProduto, ".JPEG");
        asyncProduto.execute();

        recyclerViewProduto.addOnItemTouchListener(new RecyclerViewClickListener(context, recyclerViewProduto,
                new RecyclerViewClickListener.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(View view, int position)
                    {
                        dto.setCd_Produto(arrayListProduto.get(position).getCd_Categoria());
                        Toast.makeText(context, "produto: " + dto.getCd_Produto(), Toast.LENGTH_SHORT).show();
                        DetalhesProdutoFragment fragment = new DetalhesProdutoFragment(dto);
                    }

                    @Override
                    public void onLongItemClick(View view, int position)
                    {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }));

        return view;
    }
}
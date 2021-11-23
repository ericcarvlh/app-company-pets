package com.example.appcompanypets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcompanypets.Dto.DtoProduto;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ProdutoHolder>
{
    ArrayList<DtoProduto> arrayListProduto;

    public RecyclerViewAdapter(ArrayList<DtoProduto> arrayListProduto)
    {
        this.arrayListProduto = arrayListProduto;
    }

    @NonNull
    @Override
    public ProdutoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View produtosLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.produto_adapter, parent, false);
        return new ProdutoHolder(produtosLista);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoHolder holder, int position)
    {
        // holder.imageViewProduto.setImageBitmap(arrayListProduto.get(position).getImagem());
    }

    @Override
    public int getItemCount()
    {
        return arrayListProduto.size();
    }

    class ProdutoHolder extends RecyclerView.ViewHolder
    {
        ImageView imageViewProduto;
        public ProdutoHolder(@NonNull View itemView)
        {
            super(itemView);

            //imageViewFilme = itemView.findViewById(R.id.imageViewThumb);
        }
    }
}

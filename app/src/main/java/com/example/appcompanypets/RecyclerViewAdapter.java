package com.example.appcompanypets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcompanypets.DTO.DtoCarrinho;
import com.example.appcompanypets.DTO.DtoProduto;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewAdapterHolder>
{
    ArrayList<DtoProduto> arrayListProduto;
    ArrayList<DtoCarrinho> arrayListCarrinho;
    int procedimento, adapterLayout;


    public RecyclerViewAdapter(ArrayList<DtoProduto> arrayListProduto, ArrayList<DtoCarrinho> arrayListCarrinho, int procedimento, int adapterLayout)
    {
        this.arrayListProduto = arrayListProduto;
        this.arrayListCarrinho = arrayListCarrinho;
        this.procedimento = procedimento;
        this.adapterLayout = adapterLayout;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(adapterLayout, parent, false);
        return new RecyclerViewAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterHolder recyclerViewAdapterHolder, int position)
    {
        if(procedimento==0)
        {
            //arrayListProduto.get(position).getImagem()
            recyclerViewAdapterHolder.imageViewProduto.setImageBitmap(null);
            recyclerViewAdapterHolder.textViewNomeProduto.setText(null);
            recyclerViewAdapterHolder.textViewPreco.setText(null);
            recyclerViewAdapterHolder.textViewMarca.setText(null);
        }
    }

    @Override
    public int getItemCount()
    {
        return arrayListProduto.size();
    }

    class RecyclerViewAdapterHolder extends RecyclerView.ViewHolder
    {
        ImageView imageViewProduto;
        TextView textViewNomeProduto, textViewPreco, textViewMarca;
        public RecyclerViewAdapterHolder(@NonNull View itemView)
        {
            super(itemView);

            if(procedimento==0)
            {
                imageViewProduto = itemView.findViewById(R.id.imageViewProduto);
                textViewNomeProduto = itemView.findViewById(R.id.textViewNomeProduto);
                textViewPreco = itemView.findViewById(R.id.textViewPreco);
                textViewMarca = itemView.findViewById(R.id.textViewMarca);
            }
        }
    }
}

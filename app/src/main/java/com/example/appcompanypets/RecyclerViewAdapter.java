package com.example.appcompanypets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcompanypets.DTO.DtoCarrinho;
import com.example.appcompanypets.DTO.DtoProduto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewAdapterHolder>
{
    ArrayList<DtoProduto> arrayListProduto;
    int procedimento, adapterLayout;

    public RecyclerViewAdapter(ArrayList<DtoProduto> arrayListProduto, int procedimento, int adapterLayout)
    {
        this.arrayListProduto = arrayListProduto;
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
            Picasso.get().load(arrayListProduto.get(position).getDs_Foto()).into(recyclerViewAdapterHolder.imageViewProduto);
            recyclerViewAdapterHolder.textViewNomeProduto.setText(arrayListProduto.get(position).getNm_Produto());
            recyclerViewAdapterHolder.textViewPreco.setText(String.valueOf(arrayListProduto.get(position).getVl_Produto()));
            recyclerViewAdapterHolder.textViewMarca.setText(arrayListProduto.get(position).getNm_Marca());
        }
        else if(procedimento==1)
        {
            Picasso.get().load(arrayListProduto.get(position).getDs_Foto()).into(recyclerViewAdapterHolder.imageViewProduto);
            recyclerViewAdapterHolder.textViewNomeProduto.setText(arrayListProduto.get(position).getNm_Produto());
            recyclerViewAdapterHolder.textViewPreco.setText(String.valueOf(arrayListProduto.get(position).getVl_Produto()));
            recyclerViewAdapterHolder.textViewMarca.setText(arrayListProduto.get(position).getNm_Marca());
            //recyclerViewAdapterHolder.editTextQuantidade.setText(arrayListProduto.get(position).getQt_Produto());
        }
        else if(procedimento==2)
        {
            Picasso.get().load(arrayListProduto.get(position).getDs_Foto()).into(recyclerViewAdapterHolder.imageViewProduto);
            recyclerViewAdapterHolder.textViewNomeProduto.setText(arrayListProduto.get(position).getNm_Produto());
            recyclerViewAdapterHolder.textViewPreco.setText(String.valueOf(arrayListProduto.get(position).getVl_Produto()));
            recyclerViewAdapterHolder.textViewMarca.setText(arrayListProduto.get(position).getNm_Marca());
            recyclerViewAdapterHolder.textViewQuantidade.setText(arrayListProduto.get(position).getNm_Marca());
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
        EditText editTextQuantidade;
        TextView textViewNomeProduto, textViewPreco, textViewMarca, textViewQuantidade;
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
            else if(procedimento==1)
            {
                imageViewProduto = itemView.findViewById(R.id.imageViewProduto);
                textViewNomeProduto = itemView.findViewById(R.id.textViewNomeProduto);
                textViewPreco = itemView.findViewById(R.id.textViewPreco);
                textViewMarca = itemView.findViewById(R.id.textViewMarca);
                editTextQuantidade = itemView.findViewById(R.id.editTextQuantidade);
            }
            else if(procedimento==2)
            {
                imageViewProduto = itemView.findViewById(R.id.imageViewProduto);
                textViewNomeProduto = itemView.findViewById(R.id.textViewNomeProduto);
                textViewPreco = itemView.findViewById(R.id.textViewPreco);
                textViewMarca = itemView.findViewById(R.id.textViewMarca);
                textViewQuantidade = itemView.findViewById(R.id.textViewQuantidade);
            }
        }
    }
}

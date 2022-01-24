package com.example.appcompanypets;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcompanypets.DTO.DtoProduto;
import com.example.appcompanypets.DTO.DtoVeterinarios;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewAdapterHolder>
{
    ArrayList<DtoProduto> arrayListProduto;
    ArrayList<DtoVeterinarios> arrayListVeterinario;
    int procedimento, adapterLayout;

    public RecyclerViewAdapter(ArrayList<DtoProduto> arrayListProduto, int procedimento, int adapterLayout, ArrayList<DtoVeterinarios> arrayListVeterinario)
    {
        this.arrayListProduto = arrayListProduto;
        this.procedimento = procedimento;
        this.adapterLayout = adapterLayout;
        this.arrayListVeterinario = arrayListVeterinario;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(adapterLayout, parent, false);
        return new RecyclerViewAdapterHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterHolder recyclerViewAdapterHolder, int position)
    {
        if(procedimento==0)
        {
            Picasso.get().load(arrayListProduto.get(position).getDs_Foto()).into(recyclerViewAdapterHolder.imageViewProduto);
            recyclerViewAdapterHolder.textViewNomeProduto.setText(arrayListProduto.get(position).getNm_Produto());
            recyclerViewAdapterHolder.textViewPreco.setText("R$ "+arrayListProduto.get(position).getVl_Produto());
            recyclerViewAdapterHolder.textViewMarca.setText(arrayListProduto.get(position).getNm_Marca());
        }
        else if(procedimento==1)
        {
            Picasso.get().load(arrayListProduto.get(position).getDs_Foto()).into(recyclerViewAdapterHolder.imageViewProduto);
            recyclerViewAdapterHolder.textViewNomeProduto.setText(arrayListProduto.get(position).getNm_Produto());
            recyclerViewAdapterHolder.textViewPreco.setText(String.valueOf(arrayListProduto.get(position).getVl_Produto()));
            recyclerViewAdapterHolder.textViewMarca.setText(arrayListProduto.get(position).getNm_Marca());
            recyclerViewAdapterHolder.textViewQuantidadeProduto.setText(String.valueOf(arrayListProduto.get(position).getQt_Produto()));
        }
        else if(procedimento==2)
        {
            Picasso.get().load(arrayListProduto.get(position).getDs_Foto()).into(recyclerViewAdapterHolder.imageViewProduto);
            recyclerViewAdapterHolder.textViewNomeProduto.setText(arrayListProduto.get(position).getNm_Produto());
            recyclerViewAdapterHolder.textViewPreco.setText(String.valueOf(arrayListProduto.get(position).getVl_Produto()));
            recyclerViewAdapterHolder.textViewMarca.setText(arrayListProduto.get(position).getNm_Marca());
            recyclerViewAdapterHolder.textViewQuantidade.setText(arrayListProduto.get(position).getQt_Produto()+"");
        }
        else if(procedimento==3)
        {
            Picasso.get().load(arrayListVeterinario.get(position).getDs_Imagem()).into(recyclerViewAdapterHolder.imageViewVeterinario);
            recyclerViewAdapterHolder.textViewVetEmail.setText(arrayListVeterinario.get(position).getEmail());
            recyclerViewAdapterHolder.textViewVetNumero.setText(String.valueOf(arrayListVeterinario.get(position).getTelefone()));
            recyclerViewAdapterHolder.textViewVetNome.setText(arrayListVeterinario.get(position).getNome());
        }
    }

    @Override
    public int getItemCount()
    {
        return arrayListProduto.size();
    }

    class RecyclerViewAdapterHolder extends RecyclerView.ViewHolder
    {
        ImageView imageViewProduto, imageViewVeterinario;
        TextView textViewNomeProduto, textViewPreco, textViewMarca, textViewQuantidade, textViewQuantidadeProduto,
        textViewVetEmail, textViewVetNome, textViewVetNumero;
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
                textViewQuantidadeProduto = itemView.findViewById(R.id.textViewQuantidadeProduto);
            }
            else if(procedimento==2)
            {
                imageViewProduto = itemView.findViewById(R.id.imageViewProduto);
                textViewNomeProduto = itemView.findViewById(R.id.textViewNomeProduto);
                textViewPreco = itemView.findViewById(R.id.textViewPreco);
                textViewMarca = itemView.findViewById(R.id.textViewMarca);
                textViewQuantidade = itemView.findViewById(R.id.textViewQuantidade);
            }
            else if(procedimento==3)
            {
                imageViewVeterinario = itemView.findViewById(R.id.imageViewFotoVet);
                textViewVetNome = itemView.findViewById(R.id.textViewVetNome);
                textViewVetEmail = itemView.findViewById(R.id.textViewVetEmail);;
                textViewVetNumero = itemView.findViewById(R.id.textViewVetNumero);;
            }
        }
    }
}

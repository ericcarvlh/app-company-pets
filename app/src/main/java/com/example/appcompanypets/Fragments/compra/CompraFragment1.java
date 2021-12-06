package com.example.appcompanypets.Fragments.compra;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcompanypets.DTO.DtoCompra;
import com.example.appcompanypets.DTO.DtoProduto;
import com.example.appcompanypets.DTO.DtoUsuario;
import com.example.appcompanypets.R;
import com.example.appcompanypets.RecyclerViewAdapter;
import com.example.appcompanypets.ui.carrinho.DaoBancoCarrinho;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class CompraFragment1 extends Fragment
{
    FragmentActivity context;
    RecyclerView recyclerViewItens;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<DtoProduto> arrayListProduto = new ArrayList<>();
    Chip chipCompraLoja, chipDeliveryCliente, chipDeliveryPresente;
    TextView textViewValorParcial, textViewQuantidade;
    Button buttonContinuar;
    DtoUsuario dtoU = new DtoUsuario();
    DtoCompra dto = new DtoCompra();
    int itensQuantidadeTotal;
    String tipoEntrega;

    public CompraFragment1()
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
        View view = inflater.inflate(R.layout.fragment_compra1, container, false);

        recyclerViewItens = view.findViewById(R.id.recyclerViewItens);

        textViewQuantidade = view.findViewById(R.id.textViewQuantidadeTotal);
        textViewValorParcial = view.findViewById(R.id.textViewValorParcial);
        buttonContinuar = view.findViewById(R.id.buttonContinuar);

        chipDeliveryCliente = view.findViewById(R.id.chipDeliveryCliente);

        chipCompraLoja = view.findViewById(R.id.chipCompraLoja);
        // informa apenas um código de entrega
        chipDeliveryPresente = view.findViewById(R.id.chipDeliveryPresente);
        // informa-se novo endereço

        if(chipDeliveryPresente.isChecked())
        {
            Toast.makeText(context, "Vai comprar de presente", Toast.LENGTH_SHORT).show();
        }
        else if(chipCompraLoja.isChecked())
        {
            Toast.makeText(context, "Vai retirar na loja", Toast.LENGTH_SHORT).show();
        }
        else if(chipDeliveryCliente.isChecked())
        {
            Toast.makeText(context, "Vai entregar no seu endereço", Toast.LENGTH_SHORT).show();
        }

        DaoBancoCarrinho dao = new DaoBancoCarrinho(context);
        DtoCompra.ItensCarrinho = dao.consultarItensCarrinho();

        for (int i = 0; i < DtoCompra.ItensCarrinho.size(); i++)
        {
            itensQuantidadeTotal += DtoCompra.ItensCarrinho.get(i).getQt_Produto();
        }

        dto.setVl_TotalCompra(dao.consultaValorTotal(DtoUsuario.cd_UsuLogin));

        textViewValorParcial.setText(String.valueOf(dto.getVl_TotalCompra()));
        textViewQuantidade.setText(itensQuantidadeTotal);

        recyclerViewAdapter = new RecyclerViewAdapter(arrayListProduto, 2, R.layout.compra_adapter);

        buttonContinuar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(chipDeliveryPresente.isChecked())
                    tipoEntrega = "deliveryPre";
                else if(chipCompraLoja.isChecked())
                    tipoEntrega = "deliveryLoj";
                else if(chipDeliveryCliente.isChecked() && dtoU.getNo_UF().equals("SP"))
                    tipoEntrega = "deliveryCli";
                else
                    // aqui pode ser exibido um dialogo p/ questionar se o cliente se encontra próximo a loja
                    Toast.makeText(context, "Lamentamos mas você mora muito longe para retirar na loja.", Toast.LENGTH_SHORT).show();

                DtoCompra.ItensCarrinho = dao.consultarItensCarrinho();
            }
        });

        return view;
    }
}
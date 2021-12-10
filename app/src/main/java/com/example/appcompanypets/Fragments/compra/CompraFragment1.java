package com.example.appcompanypets.Fragments.compra;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.example.appcompanypets.Fragments.formaPagamento.CartaoCreditoFragment;
import com.example.appcompanypets.Fragments.formaPagamento.CartaoDebitoFragment;
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
    Chip chipCompraLoja, chipDeliveryCliente, chipDeliveryPresente;
    Chip chipCartaoCredito, chipCartaoDebito;
    TextView textViewValorParcial, textViewQuantidade;
    Button buttonContinuar;
    DtoCompra dto = new DtoCompra();
    String tipoEntrega;
    String formaPagamento;
    int itensQuantidadeTotal;

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

    @SuppressLint("SetTextI18n")
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
        chipDeliveryPresente = view.findViewById(R.id.chipDeliveryPresente);

        chipCartaoCredito = view.findViewById(R.id.chipCartaoCredito);
        chipCartaoDebito = view.findViewById(R.id.chipCartaoDebito);

        DaoBancoCarrinho dao = new DaoBancoCarrinho(context);
        DtoCompra.ItensCarrinho = dao.consultarItensCarrinho();

        for (int i = 0; i < DtoCompra.ItensCarrinho.size(); i++)
        {
            itensQuantidadeTotal += DtoCompra.ItensCarrinho.get(i).getQt_Produto();
        }

        dto.setVl_TotalCompra(dao.consultaValorTotal(DtoUsuario.cd_UsuLogin));

        textViewValorParcial.setText("R$ "+(dto.getVl_TotalCompra()));

        if (itensQuantidadeTotal>1)
            textViewQuantidade.setText("("+(itensQuantidadeTotal)+ " Itens)");
        else
            textViewQuantidade.setText("("+(itensQuantidadeTotal)+ " Item)");

        recyclerViewAdapter = new RecyclerViewAdapter(DtoCompra.ItensCarrinho, 2, R.layout.compra_adapter);
        recyclerViewItens.setLayoutManager(new LinearLayoutManager(context));
        recyclerViewItens.setAdapter(recyclerViewAdapter);

        buttonContinuar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                tipoEntrega = "nenhuma";
                formaPagamento = "nenhuma";

                if(chipDeliveryPresente.isChecked())
                    tipoEntrega = "deliveryPre";
                else if(chipDeliveryCliente.isChecked())
                    tipoEntrega = "deliveryCli";
                else if(chipCompraLoja.isChecked() && DtoUsuario.uf_UsuLogin.equals("SP"))
                    tipoEntrega = "deliveryLoj";
                else if(chipCompraLoja.isSelected() && !DtoUsuario.uf_UsuLogin.equals("SP"))
                    // aqui pode ser exibido um dialogo p/ questionar se o cliente se encontra próximo a loja
                    Toast.makeText(context, "Lamentamos mas você mora muito longe para retirar na loja.", Toast.LENGTH_SHORT).show();

                if (chipCartaoCredito.isChecked())
                    formaPagamento = "Cartão de Crédito";
                else if (chipCartaoDebito.isChecked())
                    formaPagamento = "Cartão de Débito";

                if (!tipoEntrega.equals("nenhuma"))
                {
                    if(!formaPagamento.equals("nenhuma"))
                    {
                        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                        switch (formaPagamento)
                        {
                            case "Cartão de Débito":
                                transaction.replace(R.id.frameLayoutConteudoCompra, new CartaoDebitoFragment(tipoEntrega));
                                transaction.commit();
                                break;
                            case "Cartão de Crédito":
                                transaction.replace(R.id.frameLayoutConteudoCompra, new CartaoCreditoFragment(tipoEntrega));
                                transaction.commit();
                                break;
                        }
                    }
                    else
                        Toast.makeText(context, "Escolha uma forma de pagamento", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(context, "Escolha uma forma de entrega.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
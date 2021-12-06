package com.example.appcompanypets.ui.carrinho;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.appcompanypets.Activities.CompraActivity;
import com.example.appcompanypets.DTO.DtoCompra;
import com.example.appcompanypets.DTO.DtoProduto;
import com.example.appcompanypets.R;
import com.example.appcompanypets.RecyclerViewAdapter;
import com.example.appcompanypets.databinding.CarrinhoAdapterBinding;
import com.example.appcompanypets.ui.produto.ProdutoFragment;

public class CarrinhoFragment extends Fragment
{
    FragmentActivity context;
    Button buttonContinuarComprando, buttonComprar;
    RecyclerView recyclerViewCarrinho;
    RecyclerViewAdapter recyclerViewAdapter;
    ImageButton imageButtonRetira, imageButtonAcrescenta;
    EditText editTextQuantidade;
    DtoProduto dtoProduto = new DtoProduto();

    @Override
    public void onAttach(Activity activity)
    {
        context = (FragmentActivity) activity;

        super.onAttach(activity);
    }

    public CarrinhoFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_carrinho, container, false);

        buttonContinuarComprando = view.findViewById(R.id.buttonContinuarComprando);
        buttonComprar = view.findViewById(R.id.buttonComprar);

        CarrinhoAdapterBinding carrinhoLayout = CarrinhoAdapterBinding.inflate(getLayoutInflater());

        imageButtonAcrescenta = carrinhoLayout.imageButtonAcrescenta;
        imageButtonRetira = carrinhoLayout.imageButtonRetira;

        editTextQuantidade = view.findViewById(R.id.editTextQuantidade);

        recyclerViewCarrinho = view.findViewById(R.id.recyclerViewCarrinho);

        DaoBancoCarrinho dao = new DaoBancoCarrinho(context);
        DtoCompra.ItensCarrinho = dao.consultarItensCarrinho();

        recyclerViewAdapter = new RecyclerViewAdapter(DtoCompra.ItensCarrinho, 1, R.layout.carrinho_adapter);

        recyclerViewCarrinho.setLayoutManager(new LinearLayoutManager(context));
        recyclerViewCarrinho.setAdapter(recyclerViewAdapter);

        imageButtonAcrescenta.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int qntdSoma = 1 + Integer.parseInt(editTextQuantidade.getText().toString());
                editTextQuantidade.setText(qntdSoma);
                dtoProduto.setQt_Produto(qntdSoma);
                // falta enviar p/ banco fazendo o update
                // falta colocar o comparativo p/ do estoque
            }
        });

        imageButtonRetira.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int qntdSub = Integer.parseInt(editTextQuantidade.getText().toString()) - 1;
                if (qntdSub>0){
                editTextQuantidade.setText(qntdSub);
                dtoProduto.setQt_Produto(qntdSub);
                // falta enviar p/ banco fazendo o update
                } else
                    // quando for menor que 0 retiramos do carrinho e do banco.
                    Toast.makeText(context, "A quantidade requerida não pode ser menor que 0.", Toast.LENGTH_SHORT).show();
            }
        });

        buttonContinuarComprando.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, ProdutoFragment.class);
                startActivity(intent);
            }
        });

        buttonComprar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, CompraActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
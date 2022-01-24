package com.example.appcompanypets.ui.carrinho;

import android.annotation.SuppressLint;
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
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.appcompanypets.Activities.CompraActivity;
import com.example.appcompanypets.Activities.MenuLateralActivity;
import com.example.appcompanypets.DAO.DaoBancoCarrinho;
import com.example.appcompanypets.DTO.DtoCompra;
import com.example.appcompanypets.DTO.DtoUsuario;
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
    TextView textViewQuantidadeProduto, textViewValor;
    DtoCompra dto = new DtoCompra();
    CarrinhoAdapterBinding carrinhoLayout;

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

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_carrinho, container, false);

        buttonContinuarComprando = view.findViewById(R.id.buttonContinuarComprando);
        buttonComprar = view.findViewById(R.id.buttonComprar);
        recyclerViewCarrinho = view.findViewById(R.id.recyclerViewCarrinho);

        DaoBancoCarrinho dao = new DaoBancoCarrinho(context);
        DtoCompra.ItensCarrinho = dao.consultarItensCarrinho();

        carrinhoLayout = CarrinhoAdapterBinding.inflate(getLayoutInflater());

        imageButtonAcrescenta = carrinhoLayout.imageButtonAcrescenta;
        imageButtonRetira = carrinhoLayout.imageButtonRetira;

        textViewQuantidadeProduto = carrinhoLayout.textViewQuantidadeProduto;
        textViewValor = view.findViewById(R.id.textViewValor);

        dto.setVl_TotalCompra(dao.consultaValorTotal(DtoUsuario.cd_UsuLogin));

        textViewValor.setText("R$ "+(dto.getVl_TotalCompra()));

        recyclerViewAdapter = new RecyclerViewAdapter(DtoCompra.ItensCarrinho, 1, R.layout.carrinho_adapter, null);
        recyclerViewCarrinho.setLayoutManager(new LinearLayoutManager(context));
        recyclerViewCarrinho.setAdapter(recyclerViewAdapter);

        buttonContinuarComprando.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, MenuLateralActivity.class);
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
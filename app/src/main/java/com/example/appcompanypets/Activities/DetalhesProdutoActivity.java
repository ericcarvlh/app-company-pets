package com.example.appcompanypets.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcompanypets.DTO.DtoProduto;
import com.example.appcompanypets.R;
import com.example.appcompanypets.ui.carrinho.DaoBancoCarrinho;
import com.squareup.picasso.Picasso;

public class DetalhesProdutoActivity extends AppCompatActivity
{
    DtoProduto dto = new DtoProduto();
    TextView textViewNomeProduto, textViewPrecoProduto, textViewDsProduto, textViewNmMarca;
    ImageView imageViewProduto;
    Button buttonAdicionarAoCarrinho;
    DaoBancoCarrinho dao = new DaoBancoCarrinho(this);

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produto);

        Bundle bundle = getIntent().getExtras();
        dto = (DtoProduto) bundle.getSerializable("dto");

        buttonAdicionarAoCarrinho = findViewById(R.id.buttonAdiconar);

        textViewNomeProduto = findViewById(R.id.textViewNomeProduto);
        imageViewProduto = findViewById(R.id.imageViewProduto);
        textViewPrecoProduto = findViewById(R.id.textViewPrecoProduto);
        textViewDsProduto = findViewById(R.id.textViewDsProduto);
        textViewNmMarca = findViewById(R.id.textViewNmMarca);

        textViewNomeProduto.setText(dto.getNm_Produto());
        textViewPrecoProduto.setText("R$ " + dto.getVl_Produto());
        textViewDsProduto.setText(dto.getDs_Produto());
        Picasso.get().load(dto.getDs_Foto()).into(imageViewProduto);

        buttonAdicionarAoCarrinho.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dto.setQt_Produto(1);

                if(dao.verificaProdutoCarrinho(dto.getCd_Produto()))
                {
                    // há no carrinho
                    if (dto.getQt_Estoque()<=dao.produtoQuantidadeNoCarrinho(dto))
                        dao.inserirQtdEValorTtProdutoCarrinho(dto);
                }
                else
                    // não há no carrinho
                    dao.inserirProdutoCarrinho(dto);
            }
        });


    }
}
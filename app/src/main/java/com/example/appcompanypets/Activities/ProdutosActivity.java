package com.example.appcompanypets.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.appcompanypets.Api.Produto.AsyncProduto;
import com.example.appcompanypets.R;
import com.example.appcompanypets.RecyclerViewClickListener;

public class ProdutosActivity extends AppCompatActivity
{
    RecyclerView recyclerViewProduto;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        recyclerViewProduto = findViewById(R.id.recyclerViewProduto);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewProduto.setLayoutManager(layoutManager);

        AsyncProduto asyncProduto = new AsyncProduto(recyclerViewProduto, ".JPEG");
        asyncProduto.execute();

        recyclerViewProduto.addOnItemTouchListener(new RecyclerViewClickListener(ProdutosActivity.this, recyclerViewProduto,
                new RecyclerViewClickListener.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));

    }
}
package com.example.appcompanypets.Api.Produto;

import android.os.AsyncTask;
import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appcompanypets.Api.JsonHandler;
import com.example.appcompanypets.DTO.DtoProduto;
import com.example.appcompanypets.R;
import com.example.appcompanypets.RecyclerViewAdapter;
import com.example.appcompanypets.ui.produto.ProdutoFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AsyncProduto extends AsyncTask
{
    public ArrayList<DtoProduto> arrayListProduto;
    RecyclerView recyclerViewProdutos;

    public AsyncProduto(RecyclerView recyclerViewProdutos)
    {
        this.recyclerViewProdutos = recyclerViewProdutos;
    }

    @Override
    protected Object doInBackground(Object[] objects)
    {
        RecyclerViewAdapter recyclerViewAdapter = null;
        String json = JsonHandler.getJson("https://appcompanypetsapi.000webhostapp.com/consultaProduto.php");
        try
        {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("Produtos");

            arrayListProduto = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++)
            {
                DtoProduto dto = new DtoProduto();
                dto.setCd_Produto(jsonArray.getJSONObject(i).getInt("cd_Produto"));
                dto.setCd_Categoria(jsonArray.getJSONObject(i).getInt("cd_Categoria"));
                dto.setNm_Produto(jsonArray.getJSONObject(i).getString("nm_Produto"));
                dto.setDs_Produto(jsonArray.getJSONObject(i).getString("ds_Produto"));
                dto.setVl_Produto(jsonArray.getJSONObject(i).getDouble("vl_Produto"));
                dto.setNm_Marca(jsonArray.getJSONObject(i).getString("nm_Marca"));
                dto.setQt_Estoque(jsonArray.getJSONObject(i).getInt("qt_Estoque"));
                dto.setDs_Foto(jsonArray.getJSONObject(i).getString("ds_Foto"));

                arrayListProduto.add(dto);
            }

            recyclerViewAdapter = new RecyclerViewAdapter(arrayListProduto, 0, R.layout.produto_adapter);

        } catch (Exception e)
        {
            Log.d("ErroGenericoAsyProduto", e.toString());
        }

        return recyclerViewAdapter;
    }

    @Override
    protected void onPostExecute(Object adapter)
    {
        super.onPostExecute(adapter);
        recyclerViewProdutos.setAdapter((RecyclerView.Adapter) adapter);
        new ProdutoFragment(arrayListProduto);
    }
}

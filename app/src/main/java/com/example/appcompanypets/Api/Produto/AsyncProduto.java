package com.example.appcompanypets.Api.Produto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appcompanypets.Api.JsonHandler;
import com.example.appcompanypets.Dto.DtoProduto;
import com.example.appcompanypets.RecyclerViewAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AsyncProduto extends AsyncTask
{
    ArrayList<DtoProduto> arrayListProduto;
    RecyclerView recyclerViewProdutos;
    String formatoArquivo;

    public AsyncProduto(RecyclerView recyclerViewProdutos, String formatoArquivo)
    {
        this.recyclerViewProdutos = recyclerViewProdutos;
        this.formatoArquivo = formatoArquivo;
    }

    @Override
    protected Object doInBackground(Object[] objects)
    {
        RecyclerViewAdapter recyclerViewAdapter = null;
        String json = JsonHandler.getJson("");
        try
        {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("Search");

            arrayListProduto = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++)
            {
                DtoProduto dto = new DtoProduto();
                //dto.setTitulo(jsonArray.getJSONObject(i).getString(""));
                //dto.setAno(jsonArray.getJSONObject(i).getString(""));
                //set image
                URL url = new URL(jsonArray.getJSONObject(i).getString(""));
                Bitmap imagem = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                //dto.setImagem(imagem);

                arrayListProduto.add(dto);
            }

            recyclerViewAdapter = new RecyclerViewAdapter(arrayListProduto);

        } catch (JSONException | MalformedURLException e) {
            e.printStackTrace();
            Log.d("ErroJsonEx||UrlEx", e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("ErroProdutoIOEx", e.toString());
        }

        return recyclerViewAdapter;
    }

    @Override
    protected void onPostExecute(Object adapdter)
    {
        super.onPostExecute(adapdter);
        recyclerViewProdutos.setAdapter((RecyclerView.Adapter) adapdter);
    }
}

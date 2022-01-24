package com.example.appcompanypets.API.CEP;

import android.os.AsyncTask;
import android.util.Log;
import com.example.appcompanypets.API.JsonHandler;
import com.google.android.material.textfield.TextInputEditText;
import org.json.JSONException;
import org.json.JSONObject;

public class AsyncCEP extends AsyncTask {

    TextInputEditText editTextLogradouro, editTextBairro,
            editTextCidade, editTextUF;
    String cep;

    public AsyncCEP(TextInputEditText editTextLogradouro, TextInputEditText editTextBairro, TextInputEditText editTextCidade, TextInputEditText editTextUF, String cep)
    {
        this.editTextLogradouro = editTextLogradouro;
        this.editTextBairro = editTextBairro;
        this.cep = cep;
        this.editTextCidade = editTextCidade;
        this.editTextUF = editTextUF;
    }

    @Override
    protected Object doInBackground(Object[] objects)
    {
        return JsonHandler.getJson("https://viacep.com.br/ws/"+cep+"/json/");
    }

    @Override
    protected void onPostExecute(Object endereco)
    {
        super.onPostExecute(endereco);
        try {
            JSONObject jsonObject = new JSONObject((String) endereco);
            editTextLogradouro.setText(jsonObject.getString("logradouro"));
            editTextBairro.setText(jsonObject.getString("bairro"));
            editTextCidade.setText(jsonObject.getString("localidade"));
            editTextUF.setText(jsonObject.getString("uf"));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("e.printStackTrace", e.toString());
        }
    }
}

package com.example.appcompanypets.Api;

import android.os.AsyncTask;
import android.util.Log;

import com.example.appcompanypets.DTO.DtoUsuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AsyncLogin extends AsyncTask
{
    DtoUsuario dto = new DtoUsuario();

    public AsyncLogin(DtoUsuario dto)
    {
        this.dto = dto;
    }

    @Override
    protected Object doInBackground(Object[] objects)
    {
        return JsonHandler.getJson("https://appcompanypetsapi.000webhostapp.com/consultaLogin.php?ds_Email="+dto.getDs_Email()+"&ds_Senha="+dto.getDs_Senha());
    }

    @Override
    protected void onPostExecute(Object usuario)
    {
        super.onPostExecute(usuario);
        try {
            JSONArray jsonArray = new JSONArray(usuario);
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            dto.setCd_Usuario(jsonObject.getInt("cd_Usuario"));
            dto.setDs_Email(jsonObject.getString("ds_Email"));
            dto.setDs_Senha(jsonObject.getString("ds_Senha"));

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("e.printStackTrace", e.toString());
        }
    }
}

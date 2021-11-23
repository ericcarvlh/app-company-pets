package com.example.appcompanypets.Fragments.Cadastro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.appcompanypets.Api.CEP.AsyncCEP;
import com.example.appcompanypets.Dto.DtoUsuario;
import com.example.appcompanypets.Activities.LoginActivity;
import com.example.appcompanypets.Metodos;
import com.example.appcompanypets.R;
import com.example.appcompanypets.Dao.DaoUsuario;
import com.example.appcompanypets.Retrofit.ConfigRetrofit;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Retrofit;

public class CadastroFragment3 extends Fragment
{
    Metodos met = new Metodos();
    private DtoUsuario dto = new DtoUsuario();
    private FragmentActivity context;
    Button buttonFinalizar;
    TextInputEditText editTextLogradouro, editTextBairro, editTextNumero, editTextCEP,
            editTextUF, editTextCidade, editTextComplemento;
    Retrofit retrofit;
    DaoUsuario dao;

    public CadastroFragment3(DtoUsuario dto)
    {
        this.dto = dto;
    }

    public CadastroFragment3()
    {
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
        View view = inflater.inflate(R.layout.fragment_cadastro3, container, false);

        buttonFinalizar = view.findViewById(R.id.buttonFinalizar);

        editTextLogradouro = view.findViewById(R.id.editTextLogradouro_Cadastro);
        editTextBairro = view.findViewById(R.id.editTextBairro_Cadastro);
        editTextNumero = view.findViewById(R.id.editTextNumero_Cadastro);
        editTextCEP = view.findViewById(R.id.editTextCEP_Cadastro);
        editTextUF = view.findViewById(R.id.editTextUF_Cadastro);
        editTextCidade = view.findViewById(R.id.editTextCidade_Cadastro);
        editTextComplemento = view.findViewById(R.id.editTextComplemento_Cadastro);

        editTextCEP.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                dto.setCEP(editTextCEP.getText().toString());
                if(dto.getCEP().length()==8)
                {
                    AsyncCEP async = new AsyncCEP(editTextLogradouro, editTextBairro, editTextCidade, editTextUF, dto.getCEP());
                    async.execute();
                }
            }
        });

        buttonFinalizar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dto.setUFEstado(editTextUF.getText().toString());
                dto.setCidade(editTextCidade.getText().toString());
                dto.setCEP(editTextCEP.getText().toString());
                dto.setLogradouro(editTextLogradouro.getText().toString());
                dto.setBairro(editTextBairro.getText().toString());
                dto.setNumero(editTextNumero.getText().toString());
                dto.setComplemento(editTextComplemento.getText().toString());

                if(dto.getUFEstado().equals("") || dto.getUFEstado().length() != 2)
                    Toast.makeText(getActivity(), "É obrigatório informar o UF.", Toast.LENGTH_SHORT).show();
                else if(dto.getCEP().equals("") || dto.getCEP().length()<8)
                    Toast.makeText(getActivity(), "É obrigatório informar o CEP, mínimo de 8 e máximo de 8 digitos", Toast.LENGTH_SHORT).show();
                else if(dto.getLogradouro().equals(""))
                    Toast.makeText(getActivity(), "É obrigatório informar o logradouro.", Toast.LENGTH_SHORT).show();
                else if(dto.getNumero().equals(""))
                    Toast.makeText(getActivity(), "É obrigatório informar o numero, mínimo de 1 e máximo de 5 digitos", Toast.LENGTH_SHORT).show();
                else if(dto.getBairro().equals(""))
                    Toast.makeText(getActivity(), "É obrigatório informar o bairro.", Toast.LENGTH_SHORT).show();
                else if(dto.getCidade().equals(""))
                    Toast.makeText(getActivity(), "É obrigatório informar A cidade.", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(context, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        return view;
    }

    private void cadastrarUsuario()
    {
        retrofit = ConfigRetrofit.getRetrofit();

        dao = retrofit.create(DaoUsuario.class);

        Call<Boolean> call =  dao.cadastrar();

        met.retrofitProcedimento(call, "Sucesso ao cadastrar", "Erro ao cadastrar: ",
                "Erro: ", context, LoginActivity.class);
    }
}
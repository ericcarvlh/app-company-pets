package com.example.appcompanypets.ui.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.appcompanypets.Api.CEP.AsyncCEP;
import com.example.appcompanypets.DTO.DtoUsuario;
import com.example.appcompanypets.Activities.LoginActivity;
import com.example.appcompanypets.Metodos;
import com.example.appcompanypets.R;
import com.example.appcompanypets.DAO.DaoUsuario;
import com.example.appcompanypets.Retrofit.ConfigRetrofit;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CadastroFragment3 extends Fragment
{
    Metodos met = new Metodos();
    private DtoUsuario dto;
    private FragmentActivity context;
    Button buttonFinalizar;
    TextInputEditText editTextLogradouro, editTextBairro, editTextNumero, editTextCEP,
            editTextUF, editTextCidade, editTextComplemento;
    Retrofit retrofit;

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
                dto.setNo_CEP(editTextCEP.getText().toString());
                if(dto.getNo_CEP().length()==8)
                {
                    AsyncCEP async = new AsyncCEP(editTextLogradouro, editTextBairro, editTextCidade, editTextUF, dto.getNo_CEP());
                    async.execute();
                }
            }
        });

        buttonFinalizar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dto.setNo_UF(editTextUF.getText().toString());
                dto.setNm_Cidade(editTextCidade.getText().toString());
                dto.setNo_CEP(editTextCEP.getText().toString());
                dto.setNm_Logradouro(editTextLogradouro.getText().toString());
                dto.setNm_Bairro(editTextBairro.getText().toString());
                dto.setNo_Logradouro(editTextNumero.getText().toString());
                dto.setDs_Complemento(editTextComplemento.getText().toString());

                if(dto.getNo_UF().equals("") || dto.getNo_UF().length() != 2)
                    Toast.makeText(getActivity(), "É obrigatório informar o UF.", Toast.LENGTH_SHORT).show();
                else if(dto.getNo_CEP().equals("") || dto.getNo_CEP().length()<8)
                    Toast.makeText(getActivity(), "É obrigatório informar o CEP, mínimo de 8 e máximo de 8 digitos", Toast.LENGTH_SHORT).show();
                else if(dto.getNm_Logradouro().equals(""))
                    Toast.makeText(getActivity(), "É obrigatório informar o logradouro.", Toast.LENGTH_SHORT).show();
                else if(dto.getNo_Logradouro().equals(""))
                    Toast.makeText(getActivity(), "É obrigatório informar o numero, mínimo de 1 e máximo de 5 digitos", Toast.LENGTH_SHORT).show();
                else if(dto.getNm_Bairro().equals(""))
                    Toast.makeText(getActivity(), "É obrigatório informar o bairro.", Toast.LENGTH_SHORT).show();
                else if(dto.getNm_Cidade().equals(""))
                    Toast.makeText(getActivity(), "É obrigatório informar A cidade.", Toast.LENGTH_SHORT).show();
                else {
                    cadastrarUsuario(dto.getNm_Usuario(), dto.getDt_Nascimento(), dto.getDs_Senha(), dto.getSg_Sexo(), dto.getNo_CPF(),
                            dto.getDs_Email(), dto.getNo_UF(), dto.getNm_Cidade(), dto.getNm_Bairro(), dto.getNm_Logradouro(), dto.getNo_Logradouro(), dto.getNo_CEP(),
                            dto.getDs_Complemento(), dto.getNo_Telefone(), dto.getNo_Celular());
                }
            }
        });

        return view;
    }

    private void cadastrarUsuario(String nm_usuario, String dt_nascimento, String ds_senha, String sg_sexo, String no_cpf, String ds_email, String no_uf, String nm_cidade, String nm_bairro, String nm_logradouro, String no_logradouro, String no_cep, String ds_complemento, String no_telefone, String no_celular)
    {
        retrofit = ConfigRetrofit.getRetrofit();

        DaoUsuario dao = retrofit.create(DaoUsuario.class);

        Call<Boolean> call =  dao.cadastrar(nm_usuario, dt_nascimento, ds_senha, sg_sexo, no_cpf, ds_email, no_uf, nm_cidade, nm_bairro, nm_logradouro, no_logradouro, no_cep, ds_complemento, no_telefone, no_celular);

        call.enqueue(new Callback<Boolean>()
        {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response)
            {
                if(response.isSuccessful())
                {
                    Toast.makeText(context, "Sucesso ao cadastrar.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, LoginActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(context, "Erro ao cadastrar: " + response.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable throwable)
            {
                Toast.makeText(context, "Erro: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("CadastroCallBooljsom", throwable.toString());
            }
        });    }
}
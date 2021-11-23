package com.example.appcompanypets.Fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.appcompanypets.Dto.DtoUsuario;
import com.example.appcompanypets.Fragments.Cadastro.CadastroFragment3;
import com.example.appcompanypets.R;
import com.google.android.material.textfield.TextInputEditText;

public class CadastroFragment2 extends Fragment
{
    private DtoUsuario dto = new DtoUsuario();
    private FragmentActivity context;
    Button buttonAvancar;
    TextInputEditText editTextEmail, editTextSenha;

    public CadastroFragment2(DtoUsuario dto)
    {
        this.dto = dto;
    }

    public CadastroFragment2()
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
        View view = inflater.inflate(R.layout.fragment_cadastro2, container, false);

        buttonAvancar = view.findViewById(R.id.buttonAvancar);

        editTextEmail = view.findViewById(R.id.editTextEmail_Cadastro);
        editTextSenha = view.findViewById(R.id.editTextSenha_Cadastro);

        buttonAvancar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dto.setEmail(editTextEmail.getText().toString());
                dto.setSenha(editTextSenha.getText().toString());

                if(dto.getEmail().equals("") || dto.getEmail().length()<6)
                    Toast.makeText(getActivity(), "É obrigatório informar o email, com no máximo 60 caracteres", Toast.LENGTH_SHORT).show();
                else if(dto.getSenha().equals("") || dto.getSenha().length()<10)
                    Toast.makeText(getActivity(), "É obrigatório informar a senha, mínimo de 10 e maxímo de 20 caracteres.", Toast.LENGTH_SHORT).show();
                else{
                    try {
                        CadastroFragment3 fragment = new CadastroFragment3(dto);
                        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frameLayoutConteudoCadastro, fragment);
                        transaction.commit();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        Log.d("ExNoCadastro2", e.toString());
                    }
                }
            }
        });

        return view;
    }
}
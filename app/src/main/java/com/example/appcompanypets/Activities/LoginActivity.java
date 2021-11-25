package com.example.appcompanypets.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcompanypets.Dao.DaoUsuario;
import com.example.appcompanypets.Dto.DtoUsuario;
import com.example.appcompanypets.Metodos;
import com.example.appcompanypets.R;
import com.example.appcompanypets.Retrofit.ConfigRetrofit;

import retrofit2.Call;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity
{
    Metodos met = new Metodos();
    TextView textViewCadastro;
    Button buttonEntrar;
    EditText editTextEmail, editTextSenha;
    Retrofit retrofit;
    DaoUsuario dao;
    DtoUsuario dto = new DtoUsuario();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewCadastro = findViewById(R.id.textViewCadastro);

        editTextEmail = findViewById(R.id.editTextEmail_Login);
        editTextSenha = findViewById(R.id.editTextSenha_Login);

        buttonEntrar = findViewById(R.id.buttonEntrar);

        buttonEntrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dto.setDs_Email(editTextEmail.getText().toString());
                dto.setDs_Senha(editTextSenha.getText().toString());

                if(dto.getDs_Email().equals("") || dto.getDs_Email().length()<6)
                    Toast.makeText(LoginActivity.this, "É obrigatório informar o email, com no máximo 60 caracteres", Toast.LENGTH_SHORT).show();
                else if(dto.getDs_Senha().equals("") || dto.getDs_Senha().length()<10)
                    Toast.makeText(LoginActivity.this, "É obrigatório informar a senha, mínimo de 10 e maxímo de 20 caracteres.", Toast.LENGTH_SHORT).show();
                else {
                    loginUsuario();
                }
            }
        });

        textViewCadastro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginUsuario()
    {
        retrofit = ConfigRetrofit.getRetrofit();

        dao = retrofit.create(DaoUsuario.class);

        Call<DtoUsuario> call =  dao.Login(dto.getDs_Email(), dto.getDs_Senha());

        met.retrofitProcedimentoLogin(call, "Sucesso ao logar", "Erro ao logar: ",
                "Erro: ", LoginActivity.this, MenuActivity.class);
    }

}
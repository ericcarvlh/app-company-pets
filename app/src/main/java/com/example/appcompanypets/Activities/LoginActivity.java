package com.example.appcompanypets.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcompanypets.DAO.DaoLogin;
import com.example.appcompanypets.DTO.DtoUsuario;
import com.example.appcompanypets.R;
import com.example.appcompanypets.Retrofit.ConfigRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity
{
    TextView textViewCadastro;
    Button buttonEntrar;
    EditText editTextEmail, editTextSenha;
    DtoUsuario dto = new DtoUsuario();
    ArrayList<DtoUsuario> arrayList = new ArrayList<DtoUsuario>();
    Retrofit retrofit;

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
                    consultaLogin(dto.getDs_Email(), dto.getDs_Senha());
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

    private void consultaLogin(String email, String senha)
    {
        retrofit = ConfigRetrofit.getRetrofit();
        DaoLogin dao = retrofit.create(DaoLogin.class);
        Call<ArrayList<DtoUsuario>> call = dao.loginUsuario(email, senha);
        call.enqueue(new Callback<ArrayList<DtoUsuario>>()
        {
            @Override
            public void onResponse(Call<ArrayList<DtoUsuario>> call, Response<ArrayList<DtoUsuario>> response)
            {
                arrayList = response.body();
                if(arrayList.size()!=0)
                {
                    Intent intent = new Intent(LoginActivity.this, MenuLateralActivity.class);
                    intent.putExtra("cd_Usuario", arrayList.get(0).getCd_Usuario());
                    intent.putExtra("nm_Usuario", arrayList.get(0).getNm_Usuario());
                    intent.putExtra("ds_Email", arrayList.get(0).getDs_Email());
                    intent.putExtra("ds_Tipo", arrayList.get(0).getDs_Tipo());
                    DtoUsuario.uf_UsuLogin = arrayList.get(0).getNo_UF();
                    DtoUsuario.cd_UsuLogin = arrayList.get(0).getCd_Usuario();
                    startActivity(intent);
                }
                else
                    Toast.makeText(LoginActivity.this, "Usuário ou senha incorretos.", Toast.LENGTH_SHORT).show();
                // aqui podemos contabilizar as tentativas feitas, sendo assim p
                // reduzir as chances de ataque por força bruta
            }

            @Override
            public void onFailure(Call<ArrayList<DtoUsuario>> call, Throwable throwable)
            {
                Toast.makeText(LoginActivity.this, "Erro: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("LoginCallBooljsom", throwable.toString());
            }
        });
    }

}
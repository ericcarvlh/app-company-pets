package com.example.appcompanypets.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcompanypets.DAO.DaoUsuario;
import com.example.appcompanypets.DTO.DtoUsuario;
import com.example.appcompanypets.R;
import com.example.appcompanypets.Retrofit.ConfigRetrofit;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appcompanypets.databinding.ActivityMenuLateralBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MenuLateralActivity extends AppCompatActivity
{
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuLateralBinding binding;
    DtoUsuario dto = new DtoUsuario();
    ArrayList<DtoUsuario> arrayList = new ArrayList<DtoUsuario>();
    TextView textViewNomeUsuario, textViewEmailUsuario;
    ImageView imageViewCliente;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // configura a tool bar
        binding = ActivityMenuLateralBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMenuLateral.toolbar);

        Bundle puxar = getIntent().getExtras();
        if (puxar!=null){
            dto.setCd_Usuario(puxar.getInt("cd_Usuario"));
            dto.setNm_Usuario(puxar.getString("nm_Usuario"));
            dto.setDs_Email(puxar.getString("ds_Email"));
            dto.setDs_Tipo(puxar.getString("ds_Tipo"));
        }

        DrawerLayout drawer = binding.drawerLayout;
        // area de navegação
        NavigationView navigationView = binding.navView;

        // pegando o header view para setar usuario e email
        View headerView = navigationView.getHeaderView(0);
        textViewEmailUsuario = headerView.findViewById(R.id.textViewEmailUsuario);
        textViewNomeUsuario = headerView.findViewById(R.id.textViewNomeUsuario);
        imageViewCliente = headerView.findViewById(R.id.imageViewCliente);

        consultaDadosUsuario(DtoUsuario.cd_UsuLogin);

        // define as configurações do navigationdrawer
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_inicio, R.id.nav_produto, R.id.nav_mapa_da_loja, R.id.nav_carrinho, R.id.nav_sair).setDrawerLayout(drawer).build();

        // Configura a área que vai abrir os fragments
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_lateral);

        // exibe a parte de navegação para chamar o drawerlayout
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        // Responsavel por configurar a navegação, permitindo as transições
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_lateral);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    private void consultaDadosUsuario(int cd_Usuario)
    {
        retrofit = ConfigRetrofit.getRetrofit();
        DaoUsuario dao = retrofit.create(DaoUsuario.class);
        Call<ArrayList<DtoUsuario>> call = dao.dadosUsuario(cd_Usuario);
        call.enqueue(new Callback<ArrayList<DtoUsuario>>()
        {
            @Override
            public void onResponse(Call<ArrayList<DtoUsuario>> call, Response<ArrayList<DtoUsuario>> response)
            {
                arrayList = response.body();
                if(arrayList.size()!=0) {
                    dto.setNm_Usuario(arrayList.get(0).getNm_Usuario());
                    dto.setDs_Email(arrayList.get(0).getDs_Email());
                    dto.setSg_Sexo(arrayList.get(0).getSg_Sexo());
                    textViewNomeUsuario.setText(dto.getNm_Usuario());
                    textViewEmailUsuario.setText(dto.getDs_Email());
                    if (dto.getSg_Sexo().equals("M"))
                        imageViewCliente.setImageResource(R.drawable.usu1);
                    else
                        imageViewCliente.setImageResource(R.drawable.usu2);

                }else
                    Toast.makeText(MenuLateralActivity.this, "Erro ao executar a consulta.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ArrayList<DtoUsuario>> call, Throwable throwable) {

            }
        });
    }
}
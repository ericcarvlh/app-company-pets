package com.example.appcompanypets.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.appcompanypets.DTO.DtoUsuario;
import com.example.appcompanypets.R;
import com.example.appcompanypets.ui.menu.MenuFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appcompanypets.databinding.ActivityMenuLateralBinding;

public class MenuLateralActivity extends AppCompatActivity
{
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuLateralBinding binding;
    DtoUsuario dto = new DtoUsuario();
    TextView textViewNomeUsuario, textViewEmailUsuario;

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
        if (dto!=null){
        textViewNomeUsuario.setText(dto.getNm_Usuario());
        textViewEmailUsuario.setText(dto.getDs_Email());
        }

        // define as configurações do navigationdrawer
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_inicio, R.id.nav_produto, R.id.nav_mapa_da_loja, R.id.nav_carrinho).setDrawerLayout(drawer).build();

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
}
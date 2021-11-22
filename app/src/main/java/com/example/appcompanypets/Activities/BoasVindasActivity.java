package com.example.appcompanypets.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appcompanypets.R;
import com.example.appcompanypets.Slides.SlideAdapter;

public class BoasVindasActivity extends AppCompatActivity
{
    ViewPager viewPager;
    Button buttonLogin, buttonCadastro;
    SlideAdapter slideAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boas_vindas);

        int imagens[] = {
                R.drawable.imgboasvindas1,
                R.drawable.imgboasvindas2,
                R.drawable.imgboasvindas3,
                R.drawable.imgboasvindas4,
                R.drawable.imgboasvindas5
        };

        int descricao[] = {
                R.string.descricaoBoasvindas1,
                R.string.descricaoBoasvindas2,
                R.string.descricaoBoasvindas3,
                R.string.descricaoBoasvindas4,
                R.string.descricaoBoasvindas5
        };

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonCadastro = findViewById(R.id.buttonCadastro);
        viewPager = findViewById(R.id.viewPagerBoasVindas);
        slideAdapter = new SlideAdapter(this, imagens, descricao, R.layout.slide_adapter);

        buttonLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent login = new Intent(BoasVindasActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });

        buttonCadastro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent cadastro = new Intent(BoasVindasActivity.this, CadastroActivity.class);
                startActivity(cadastro);
            }
        });
        viewPager.setAdapter(slideAdapter);
        viewPager.addOnPageChangeListener(viewListener);
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener()
    {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position)
        {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
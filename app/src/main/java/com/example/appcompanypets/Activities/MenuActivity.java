package com.example.appcompanypets.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.appcompanypets.Dto.DtoUsuario;
import com.example.appcompanypets.R;
import com.example.appcompanypets.Slides.SlideAdapter;

public class MenuActivity extends AppCompatActivity
{
    ViewPager viewPager;
    SlideAdapter slideAdapter;
    DtoUsuario dto = new DtoUsuario();

    public MenuActivity(DtoUsuario dto)
    {
        this.dto = dto;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

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

        viewPager = findViewById(R.id.viewPagerMenu);
        slideAdapter = new SlideAdapter(this, imagens, descricao, R.layout.slide_adapter);

        viewPager.setAdapter(slideAdapter);
        viewPager.addOnPageChangeListener(viewListener);

        Toast.makeText(MenuActivity.this, "O seu código é: " +dto.getCd_Usuario(), Toast.LENGTH_SHORT).show();
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
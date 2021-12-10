package com.example.appcompanypets.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appcompanypets.R;

public class ErroActivity extends AppCompatActivity
{
    Button buttonVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erro);

        buttonVoltar = findViewById(R.id.buttonVoltar);

        buttonVoltar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ErroActivity.this, MenuLateralActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
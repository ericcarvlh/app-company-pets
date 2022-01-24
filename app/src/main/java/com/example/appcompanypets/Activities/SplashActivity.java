package com.example.appcompanypets.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.appcompanypets.R;

public class SplashActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable()
        {
            @Override public void run()
            {
                Intent intent = new Intent(SplashActivity.this, BoasVindasActivity.class);
                startActivity(intent);
                finish();
            }}, 3000);
    }
}
package com.example.androidnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {
int time=3000;
GifImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        gifImageView=findViewById(R.id.splash_gif);
        gifImageView.setGifImageResource(R.drawable.iphone_spinner);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(SplashActivity.this,FragmentShowActivity.class);
                startActivity(i);
                finish();
            }
        },time);

    }
}
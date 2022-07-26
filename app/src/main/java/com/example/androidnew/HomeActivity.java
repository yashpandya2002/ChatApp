package com.example.androidnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent i=getIntent();
        String email=i.getStringExtra("EMAIL");
        String gender=i.getStringExtra("GENDER");
        txt=findViewById(R.id.text_view);
        txt.setText("Email is + "+email + "and gender is "+gender);
    }
}
package com.example.androidnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RawDataDisplay extends AppCompatActivity {
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raw_data_display);
        textView=findViewById(R.id.tvData_gridviewdisplay);
        Intent i=getIntent();

        String text=i.getStringExtra("data");
        textView.setText(text);
    }
}
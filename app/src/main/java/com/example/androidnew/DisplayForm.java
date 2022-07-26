package com.example.androidnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DisplayForm extends AppCompatActivity {
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form_display);
        textView=findViewById(R.id.display_text);
        Intent i = getIntent();
        String name=i.getStringExtra("FULL_NAME");
        String age=(i.getStringExtra("AGE"));
        String height=(i.getStringExtra("HEIGHT"));
        String c_weight=(i.getStringExtra("CURRENT_WEIGHT"));
        String g_weight=(i.getStringExtra("GOAL_WEIGHT"));
        String phone=(i.getStringExtra("PHONE"));
        String address=i.getStringExtra("ADDRESS");
        String gender=i.getStringExtra("GENDER");
        String date=i.getStringExtra("DATE");


        textView.setText("Name :\t"+name+ "\n" +
                "Age :\t" +age+"\nHeight :\t"+height+"\nGender :\t"
                + gender+"\nCurrent Weight :\t"+c_weight+"\nGoal Weight :\t"+g_weight+"\nSelected Date:\t"+date+"\nPhone :\t"+phone+"\nAddress :\t"+address);
    }
}
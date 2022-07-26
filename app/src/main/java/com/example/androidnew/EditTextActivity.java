package com.example.androidnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EditTextActivity extends AppCompatActivity {
EditText name;
ImageView img;
Button btn;
RadioGroup gender_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        name=findViewById(R.id.edt_name);
        img=findViewById(R.id.fimage);
        btn=findViewById(R.id.btn_enter);
        gender_btn=findViewById(R.id.gender);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setImageResource(R.drawable.download);
                int radio_id = gender_btn.getCheckedRadioButtonId();
                RadioButton radioButton=findViewById(radio_id);
                String gender=radioButton.getText().toString().trim();
                String str=name.getText().toString().trim();
                if(str.length()==0){
                    Toast.makeText(EditTextActivity.this, "Please enter proper name", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditTextActivity.this, "Name is " + str, Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent(EditTextActivity.this,HomeActivity.class);
                i.putExtra("EMAIL",str);
                i.putExtra("GENDER",gender);
                startActivity(i);
            }
        });
    }
}
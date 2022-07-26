package com.example.androidnew;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class RegisterationForm extends AppCompatActivity {
EditText fname;
EditText c_weight,g_weight,height,age,phone,address;
RadioGroup gender_group;
CheckBox check;
Button btn;
TextView tvDate;
ImageButton date_btn;
int date,month,year,month1,year1,date1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration_form);
        fname=findViewById(R.id.full_name);
        c_weight=findViewById(R.id.weight);
        g_weight=findViewById(R.id.goal_weight);
        age=findViewById(R.id.age);
        height=findViewById(R.id.height);
        tvDate=findViewById(R.id.tv_date);
        phone=findViewById(R.id.phone);
        address=findViewById(R.id.address);
        btn=findViewById(R.id.submit);
        check=findViewById(R.id.checkbox);
        gender_group=findViewById(R.id.gender_group);
        date_btn=findViewById(R.id.date);
        Calendar calendar=Calendar.getInstance();
        date=calendar.get(Calendar.DAY_OF_MONTH);
        month=calendar.get(Calendar.MONTH);
        year=calendar.get(Calendar.YEAR);
        date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(RegisterationForm.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        tvDate.setText(i2+"/"+i1+"/"+i);
                    }
                } ,year,month,date);
                datePickerDialog.show();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check.isChecked()) {
                    String name = fname.getText().toString().trim();
                    String cw = c_weight.getText().toString();
                    String gw = g_weight.getText().toString();
                    String h = height.getText().toString();
                    String a = age.getText().toString();
                    String ph = phone.getText().toString();
                    String add = address.getText().toString().trim();
                    String date  = tvDate.getText().toString();
                    int choice=gender_group.getCheckedRadioButtonId();
                    RadioButton radioButton=findViewById(choice);
                    String gender = radioButton.getText().toString();
                    Intent i = new Intent(RegisterationForm.this, DisplayForm.class);
                    i.putExtra("FULL_NAME", name);
                    i.putExtra("AGE",a);
                    i.putExtra("HEIGHT", h);
                    i.putExtra("CURRENT_WEIGHT",cw);
                    i.putExtra("GOAL_WEIGHT",gw);
                    i.putExtra("PHONE",ph);
                    i.putExtra("ADDRESS", add);
                    i.putExtra("GENDER", gender);
                    i.putExtra("DATE",date);
                    startActivity(i);
                }
                else{
                    Toast.makeText(RegisterationForm.this, "Select the checkbox to submit the form", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
package com.example.androidnew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SpinnerActivity extends AppCompatActivity {
String arr[]={"Select your language","C","C++","Java","HTML"};
Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        spinner=findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arr){

/*

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView tvData=(TextView) super.getView(position, convertView, parent);
                if(position==0)
                {
                    tvData.setTextColor(Color.GRAY);
                }
                else
                {
                    tvData.setTextColor(Color.BLUE);
                }
                return tvData;
            }
*/

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView tvData=(TextView) super.getDropDownView(position, convertView, parent);
                if(position==0)
                {
                    tvData.setTextColor(Color.GRAY);
                }
                else
                {
                    tvData.setTextColor(Color.MAGENTA);
                }
                return tvData;
            }


        };
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i!=0) {
                    Toast.makeText(SpinnerActivity.this, "Selected course is " + adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

                    ((TextView)view).setTextColor(Color.BLUE);
                }
                else
                {
                    ((TextView)view).setTextColor(Color.GRAY);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
package com.example.androidnew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SimpleListActivity extends AppCompatActivity {
String str[]={"Java","C","C++","Python","Android"};
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list);
        listView=findViewById(R.id.list_view);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(SimpleListActivity.this, android.R.layout.simple_list_item_1,str);

        listView.setAdapter(arrayAdapter);


    }
}
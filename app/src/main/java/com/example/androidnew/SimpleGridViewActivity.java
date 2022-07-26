package com.example.androidnew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class SimpleGridViewActivity extends AppCompatActivity {
GridView gridView;
String arr[]={"data 1","data 2","data 3","data 4","data 5","data 6"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_grid_view);
        gridView=findViewById(R.id.grid_view);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(SimpleGridViewActivity.this, R.layout.raw_gridview,R.id.grid_tvData,arr){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return super.getView(position, convertView, parent);
            }

        };
        gridView.setAdapter(arrayAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text=adapterView.getItemAtPosition(i).toString();
                Log.e("selected item",text);

                Intent intent = new Intent(SimpleGridViewActivity.this,RawDataDisplay.class);

                intent.putExtra("data", text);
                startActivity(intent);

            }
        });

    }
}
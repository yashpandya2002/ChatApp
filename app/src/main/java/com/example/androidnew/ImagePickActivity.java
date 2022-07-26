package com.example.androidnew;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.net.URI;

public class ImagePickActivity extends AppCompatActivity {
    ImageView imgDP;
    Uri uri1;
    Bitmap bitmap1;
    Button btn_gallery,btn_Camera,btn_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pick);
        imgDP=findViewById(R.id.imgDP);
        btn_gallery=findViewById(R.id.btn_gallery);
        btn_next=findViewById(R.id.btn_nextpage);
        btn_Camera=findViewById(R.id.btn_camera);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ImagePickActivity.this, ImageShowActivity.class);
                i.putExtra("BITMAP", bitmap1);
                i.putExtra("URI", uri1);
                startActivity(i);
            }

        });
        btn_Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent();
                i.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,12);
            }
        });
        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i,11);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==11 && resultCode==RESULT_OK)
        {
            Uri uri = data.getData();
            imgDP.setImageURI(uri);
            uri1=uri;
        }
        if(requestCode==12 && resultCode==RESULT_OK)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgDP.setImageBitmap(bitmap);
            bitmap1=bitmap;
        }
    }
}
package com.example.androidnew;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageShowActivity extends AppCompatActivity {
ImageView img;
TextView txt_set;
TextView edt_width,edt_length;
Button btn_set,width_up,width_down,length_up,length_down;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);
        img=findViewById(R.id.imgshow);
        btn_set=findViewById(R.id.btn_set);
        txt_set=findViewById(R.id.txt_set);
        width_down=findViewById(R.id.width_down);
        width_up=findViewById(R.id.width_up);
//        length_down=findViewById(R.id.length_down);
//        length_up=findViewById(R.id.length_up);
        Intent i=getIntent();
        Bitmap bitmap=(Bitmap) i.getParcelableExtra("BITMAP");
        Uri uri=(Uri) i.getParcelableExtra("URI");
        if(bitmap==null)
        {
            img.setImageURI(uri);
            txt_set.setText("OG Width"+img.getLayoutParams().width +"  OG Height"+img.getLayoutParams().height);

        }
        else
        {
            img.setImageBitmap(bitmap);
            txt_set.setText("OG Width"+img.getLayoutParams().width +"  OG Height"+img.getLayoutParams().height);
        }
//        txt_set.setText("OG Width"+img.getLayoutParams().width +"  OG Height"+img.getLayoutParams().height);
        edt_length=findViewById(R.id.pic_length);
        edt_width=findViewById(R.id.pic_width);
        width_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int width=Integer.parseInt(edt_width.getText().toString().trim());
                int width1=width+20;
                   edt_width.setText(String.valueOf(width1));
                int length=Integer.parseInt(edt_length.getText().toString().trim());
                int length1=length+20;
                edt_length.setText(String.valueOf(length1));
                //  Log.e("Data","Width"+(width-1));
//                int length=img.getLayoutParams().height;
                img.getLayoutParams().width = width1;
                img.getLayoutParams().height=length1;
                img.requestLayout();

                txt_set.setText("Height:"+length1+"\tWidth:"+(width1));
            }
        });
        width_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int width=Integer.parseInt(edt_width.getText().toString().trim());
                int width1=width-20;
                edt_width.setText(String.valueOf(width1));
                int length=Integer.parseInt(edt_length.getText().toString().trim());
                int length1=length-20;
                edt_length.setText(String.valueOf(length1));
                //  Log.e("Data","Width"+(width-1));
//                int length=img.getLayoutParams().height;
                img.getLayoutParams().width = width1;
                img.getLayoutParams().height=length1;
                img.requestLayout();

                txt_set.setText("Height:"+length1+"\tWidth:"+(width1));
            }
        });
//        length_up.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int width=img.getLayoutParams().width;
//
//                //   edt_width.setText(width-1);
//                //  Log.e("Data","Width"+(width-1));
//                int length=img.getLayoutParams().height;
//                img.getLayoutParams().height= (length+1);
//                img.requestLayout();
//
//                txt_set.setText("Height:"+(length+1)+"\tWidth:"+(width));
//            }
//        });
//        length_down.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int width=img.getLayoutParams().width;
//
//                //   edt_width.setText(width-1);
//                //  Log.e("Data","Width"+(width-1));
//                int length=img.getLayoutParams().height;
//                img.getLayoutParams().height = (length-1);
//                img.requestLayout();
//
//                txt_set.setText("Height:"+(length-1)+"\tWidth:"+(width));
//            }
//        });
        btn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int length=Integer.parseInt(edt_length.getText().toString().trim());
                int width=Integer.parseInt(edt_width.getText().toString().trim());
//
                ViewGroup.LayoutParams layoutParams=img.getLayoutParams();
                layoutParams.height=length;
                layoutParams.width=width;
                img.setLayoutParams(layoutParams);

                txt_set.setText("Height:"+length+"\tWidth:"+width);

            }
        });

//        TextWatcher textWatcher = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                int length=Integer.parseInt(edt_length.getText().toString().trim());
//                img.getLayoutParams().height=length;
//                int width=Integer.parseInt(edt_width.getText().toString().trim());
//                img.getLayoutParams().width=width;
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        };


    }
}
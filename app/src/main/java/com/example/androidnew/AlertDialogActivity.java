package com.example.androidnew;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AlertDialogActivity extends AppCompatActivity {
Button btn,btn_customdialog;
boolean doubletap=false;
TextView back_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        btn=findViewById(R.id.btn_alert);
        back_text=findViewById(R.id.back_text);
        btn_customdialog=findViewById(R.id.btn_customalert);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(AlertDialogActivity.this);
                builder.setTitle("AlertActivity");
                builder.setMessage("You surely want to exit ?");
                builder.setIcon(R.drawable.ic_android_black_24dp);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(AlertDialogActivity.this,"You have clicked yes", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(AlertDialogActivity.this, "You have clicked no", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNeutralButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
        });
        btn_customdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater=getLayoutInflater();
                View myView=layoutInflater.inflate(R.layout.custom_alert_dialog,null);

                AlertDialog.Builder builder=new AlertDialog.Builder(AlertDialogActivity.this);
                AlertDialog alertDialog=builder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.setView(myView);
                alertDialog.show();
                EditText email_box;
                email_box = myView.findViewById(R.id.custom_email);
                Button btn_submit=myView.findViewById(R.id.btn_submit);
                btn_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String email=email_box.getText().toString().trim();
                        Toast.makeText(AlertDialogActivity.this, "Email"+email, Toast.LENGTH_SHORT).show();
                        if(alertDialog.isShowing())
                        {
                            alertDialog.dismiss();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
//        AlertDialog.Builder builder=new AlertDialog.Builder(AlertDialogActivity.this);
//        builder.setTitle("AlertActivity");
//        builder.setMessage("You surely want to exit ?");
//        builder.setIcon(R.drawable.ic_android_black_24dp);
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
////                    super.onBackPressed();
//                finish();
//            }
//        });
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(AlertDialogActivity.this, "You have clicked no", Toast.LENGTH_SHORT).show();
//            }
//        });
//        builder.setNeutralButton("Done", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        });
//        builder.show();
        if(doubletap){
            super.onBackPressed();
            return;
        }
        doubletap=true;
        back_text.setText("You sure you want to exit, tap twice");
        Toast.makeText(AlertDialogActivity.this, "You sure you want to exit, tap twice", Toast.LENGTH_SHORT).show();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                doubletap=false;
            }
        },2000);


    }
}
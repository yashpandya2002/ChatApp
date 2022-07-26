package com.example.androidnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginForm extends AppCompatActivity {
//EditText edtEmail,edtPassword;
//TextView signup;
//Button btnLogin;
//ProgressDialog progressDialog;
//FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
//        edtEmail=findViewById(R.id.login_email);
//        edtPassword=findViewById(R.id.login_password);
//        signup=findViewById(R.id.login_signup);
//        btnLogin=findViewById(R.id.login_btn);
//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(LoginForm.this,SignUp.class);
//                startActivity(i);
//            }
//        });
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LoginUser();
//            }
//        });
    }

//    private void LoginUser() {
//        String email=edtEmail.getText().toString().trim();
//        String password=edtPassword.getText().toString().trim();
//
//        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//        progressDialog=new ProgressDialog(this);
//        mAuth= FirebaseAuth.getInstance();
//
//        if(!(email.matches(emailPattern)))
//        {
//            edtEmail.setError("Email pattern should match");
//        }
//        else if(password.isEmpty() || password.length()<6)
//        {
//            edtPassword.setError("Password length must be atleast 6 characters");
//        }
//        else
//        {
//            progressDialog.setMessage("Please wait while Logging In...");
//            progressDialog.setTitle("Logging In" );
//            progressDialog.setCanceledOnTouchOutside(false);
//            progressDialog.show();
//            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if(task.isSuccessful())
//                    {
//                        progressDialog.dismiss();
//                        Intent i=new Intent(LoginForm.this,LoginPage2.class);
//                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(i);
//                        Toast.makeText(LoginForm.this, "User login successful", Toast.LENGTH_SHORT).show();
//                    }
//                    else {
//                        progressDialog.dismiss();
//                        Toast.makeText(LoginForm.this, "SignUp failed", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//
//        }
//    }
}
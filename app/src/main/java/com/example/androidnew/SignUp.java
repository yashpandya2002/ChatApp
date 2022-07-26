package com.example.androidnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class SignUp extends AppCompatActivity {
    EditText edtEmail,edtPassword,edtConfirmPassword,edtUsername;
    TextView txt_change,login;
    Button btnSignUp;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    boolean isSigningUp=true;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edtEmail=findViewById(R.id.signup_email);
        edtPassword=findViewById(R.id.signup_password);
        edtConfirmPassword=findViewById(R.id.signup_confirmpassword);
        edtUsername=findViewById(R.id.signup_username);
        login=findViewById(R.id.signup_login);
        btnSignUp=findViewById(R.id.signup_btn);
        txt_change=findViewById(R.id.txt_head);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
//        firebaseDatabase = FirebaseDatabase.getInstance("https://androidnew-826cf-default-rtdb.firebaseio.com/");
//        databaseReference = firebaseDatabase.getReference("user");
//        if(mUser!=null)
//        {
//            Intent i= new Intent(this,LoginPage2.class);
//        }
        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
        {

            startActivity(new Intent(SignUp.this,LoginPage2.class));
            finish();
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSigningUp)
                {
                    login.setText("Create new Account ?");
                    edtConfirmPassword.setVisibility(View.GONE);
                    txt_change.setText("Login");
                    btnSignUp.setText("Login");
                    isSigningUp=false;
                }
                else
                {
                    login.setText("Already have an Account ?");
                    edtConfirmPassword.setVisibility(View.VISIBLE);
                    txt_change.setText("Sign Up");
                    btnSignUp.setText("Sign Up");
                    isSigningUp=true;
                }

            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSigningUp) {
                    AuthenticateAddUser();

                }
                else
                {
                    loginUser();
                }
            }
        });
    }

    private void loginUser() {
        String email=edtEmail.getText().toString().trim();
        String password=edtPassword.getText().toString().trim();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        progressDialog=new ProgressDialog(this);
        if(!(email.matches(emailPattern)))
        {
            edtEmail.setError("Email pattern should match");
        }
        else if(password.isEmpty() || password.length()<6)
        {
            edtPassword.setError("Password length must be atleast 6 characters");
        }
        else
        {
            progressDialog.setMessage("Please wait while Logging In...");
            progressDialog.setTitle("Logging In" );
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {


                        progressDialog.dismiss();
                        SharedPreferences sharedPreferences=getSharedPreferences("myapp",MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putString("sender",edtUsername.getText().toString());
                        myEdit.putString("sender_id",FirebaseAuth.getInstance().getCurrentUser().getUid().toString());
                        myEdit.commit();
                        Intent i=new Intent(SignUp.this,LoginPage2.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        Toast.makeText(SignUp.this, "User login successful", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(SignUp.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    private void AuthenticateAddUser() {

        String email=edtEmail.getText().toString().trim();
        String password=edtPassword.getText().toString().trim();
        String confirmpassword=edtConfirmPassword.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        progressDialog=new ProgressDialog(this);
        if(!(email.matches(emailPattern)))
        {
            edtEmail.setError("Email pattern should match");
        }
        else if(password.isEmpty() || password.length()<6)
        {
            edtPassword.setError("Password length must be atleast 6 characters");
        }
        else if(!(password.equals(confirmpassword)))
        {
            edtConfirmPassword.setError("Both the passwords should match");
        }
        else
        {
            progressDialog.setMessage("Please wait while Signing Up...");
            progressDialog.setTitle("Signing Up" );
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        FirebaseDatabase.getInstance().getReference("user/"+FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(new User(edtUsername.getText().toString(),
                                edtEmail.getText().toString(),
                                "",0,FirebaseAuth.getInstance().getCurrentUser().getUid().toString(), "","",0));
                        progressDialog.dismiss();
                        SharedPreferences sharedPreferences=getSharedPreferences("myapp",MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putString("sender",edtUsername.getText().toString());
                        myEdit.commit();
                        Intent i=new Intent(SignUp.this,LoginPage2.class);
//
////                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
//                        loginUser();
                        Toast.makeText(SignUp.this, "User successfully created", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(SignUp.this, "SignUp failed", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }
}
package com.example.androidnew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Profile_ChatApp extends AppCompatActivity {
TextView txt_email;
ImageView profile_image;
Button logout,upload_image;
FirebaseAuth mAuth;
FirebaseUser mUser;
Uri uri,uri1;
String path;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_chat_app);
        txt_email=findViewById(R.id.profile_email);
        logout=findViewById(R.id.logout_btn);
        upload_image=findViewById(R.id.profile_pic_upload);
        profile_image=findViewById(R.id.profile_pic);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        txt_email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail().toString());


//        File file = null;
//        try {
//            file = File.createTempFile("temp","jpg");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        File finalFile = file;
//        FirebaseStorage.getInstance().getReference().child("images/"+FirebaseAuth.getInstance().getCurrentUser().getUid())
//                .getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                Toast.makeText(Profile_ChatApp.this, "Image Retreived", Toast.LENGTH_SHORT).show();
//                Bitmap bitmap= BitmapFactory.decodeFile(finalFile.getAbsolutePath());
//                profile_image.setImageBitmap(bitmap);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//            }
//        });
        FirebaseDatabase.getInstance().getReference("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    User user=dataSnapshot.getValue(User.class);
                    if(user.getEmail().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail()) && !user.getProfilepicture().equals(null))
                    {
                        String path=user.getProfilepicture();
                        Glide.with(Profile_ChatApp.this).load(path).into(profile_image);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        path=FirebaseStorage.getInstance().getReference("images/"+FirebaseAuth.getInstance().getCurrentUser().getUid()).getDownloadUrl().toString();
//        profile_image.setImageURI(uri1);
//                String path = FirebaseDatabase.getInstance("https://androidnew-826cf-default-rtdb.firebaseio.com/").getReference("user/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/profilepicture").get().toString();
//        String path=Fireb
//        Toast.makeText(this, "Path for image"+path, Toast.LENGTH_SHORT).show();
//        txt_email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail().toString()+"::::"+path);
//        Glide.with(Profile_ChatApp.this).load(path).into(profile_image);
//        profile_image.setImageURI((Uri)(
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(Profile_ChatApp.this,SignUp.class));
                finish();
            }
        });
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i,1);
            }
        });
        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog=new ProgressDialog(Profile_ChatApp.this);
                progressDialog.setTitle("Uploading...");
                progressDialog.show();
                FirebaseStorage.getInstance().getReference("images/"+FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful())
                        {
                            task.getResult().getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if(task.isSuccessful())
                                    {
                                        path=task.getResult().toString();
                                        updateimageurl(task.getResult().toString());

                                    }
                                }
                            });
                            Toast.makeText(Profile_ChatApp.this, "Profile picture Uploaded!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {

                            Toast.makeText(Profile_ChatApp.this, "Profile Picture not Uploaded", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progress= 100.0 * snapshot.getBytesTransferred()/snapshot.getTotalByteCount();
                            progressDialog.setMessage("Uploaded " + (int)progress +"%");
                    }
                });
            }
        });

    }

    private void updateimageurl(String toString) {
        FirebaseDatabase.getInstance().getReference("user/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/profilepicture").setValue(toString);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK)
        {
            uri=data.getData();
            profile_image.setImageURI(uri);
        }
    }
}
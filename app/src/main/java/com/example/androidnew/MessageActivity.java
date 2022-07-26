package com.example.androidnew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public class MessageActivity extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
ImageView imageView;
TextView textView;
EditText editText;
ImageView btn_send,btn_send_photo;
String username_sender;
MessageAdapter messageAdapter;
String username_receiver,link_pic_receiver;
ListView listView;
Runnable refresh;
Uri uri;
String path;
Handler handler = new Handler();
//ArrayList<Message> list=new ArrayList<Message>();
String str[]={};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        imageView = findViewById(R.id.message_receiver_pic);
        textView = findViewById(R.id.message_receiver_name);
        editText = findViewById(R.id.message_editbox);
        btn_send = findViewById(R.id.message_send_btn);
        listView = findViewById(R.id.message_list_view);
        btn_send_photo = findViewById(R.id.send_photo);
        SharedPreferences sharedPreferences=getSharedPreferences("myapp", Context.MODE_PRIVATE);
//        swipeRefreshLayout=findViewById(R.id.swipe_refresh);
        Intent i = getIntent();
        username_receiver = i.getStringExtra("receiver_name").toString();
        link_pic_receiver = i.getStringExtra("receiver_pic").toString();
        textView.setText(username_receiver);
//        loadmessages();
        refresh = new Runnable() {
            public void run() {
                // Do something
                handler.postDelayed(refresh, 500);
                listView.setAdapter(null);
                loadmessages();
            }
        };
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                listView.setAdapter(null);
//           loadmessages();
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });
        btn_send_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, 12);
            }
        });
        Glide.with(MessageActivity.this).load(link_pic_receiver).into(imageView);

        FirebaseDatabase.getInstance().getReference("user/" + FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                username_sender = snapshot.getValue(User.class).getUsername();
                listView.setAdapter(null);
//                loadmessages();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        FirebaseDatabase.getInstance().getReference("messages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loadmessages();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = editText.getText().toString().trim();
                if (message.length() == 0) {
//                    Toast.makeText(MessageActivity.this, "Empty Message sent form "+username_sender+" to "+username_receiver, Toast.LENGTH_SHORT).show();
                    loadmessages();
                } else {
                    Calendar calendar = Calendar.getInstance();
                    long millisecond = System.currentTimeMillis();
//                    String msc=String.valueOf(millisecond);

//                    FirebaseDatabase.getInstance().getReference("user").addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            for(DataSnapshot dataSnapshot:snapshot.getChildren())
//                            {
//
//                                User user=dataSnapshot.getValue(User.class);
//                                if(user.getUsername().equals(username_receiver))
//                                {

//                                }
//
//
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
                    FirebaseDatabase.getInstance().getReference("messages/" + millisecond + "/").setValue(new Message(username_sender, username_receiver, message, millisecond, "false",""));
                    FirebaseDatabase.getInstance().getReference("user/"+sharedPreferences.getString("receiver_id","").toString()+"/time").setValue(millisecond);
                    FirebaseDatabase.getInstance().getReference("user/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/time").setValue(millisecond);
                    FirebaseDatabase.getInstance().getReference("user/"+sharedPreferences.getString("receiver_id","").toString()+"/getLastMessageFrom").setValue(username_sender);
                    FirebaseDatabase.getInstance().getReference("user/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/lastMessageTo").setValue(username_receiver);


//                    Toast.makeText(MessageActivity.this, "Message sent form "+username_sender+" to "+username_receiver, Toast.LENGTH_SHORT).show();
                    editText.setText("");
                    listView.setAdapter(null);
                    loadmessages();
                }
//                loadmessages();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==12 && resultCode==RESULT_OK)
        {
            uri=data.getData();
            long millisecond=System.currentTimeMillis();
            FirebaseStorage.getInstance().getReference("messages/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/"+millisecond).putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
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
//                                        setimagepathtodatabase(path);
                                        FirebaseDatabase.getInstance().getReference("messages/"+millisecond+"/")
                                                .setValue(new Message(username_sender,username_receiver,"",millisecond,"true",path));
                                    }
                            }
                        });

                    }
                }
            });
        }
    }

//    private void setimagepathtodatabase(String path) {
//        editText.setText(path);
//    }

    public void loadmessages() {
        ArrayList<Message> list=new ArrayList<Message>();
        messageAdapter=new MessageAdapter(MessageActivity.this,list);
        messageAdapter.notifyDataSetChanged();
        FirebaseDatabase.getInstance().getReference("messages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    Message message=dataSnapshot.getValue(Message.class);
                    if(message.getSender().equals(username_sender) && message.getReceiver().equals(username_receiver))
                    {
                        list.add(message);
                        messageAdapter.notifyDataSetChanged();
                    }
                    if(message.getReceiver().equals(username_sender) && message.getSender().equals(username_receiver))
                    {
                        list.add(message);
                        messageAdapter.notifyDataSetChanged();
                    }

                }

                listView.setAdapter(new ArrayAdapter<String>(MessageActivity.this, android.R.layout.simple_list_item_1,str));
//                messageAdapter=new MessageAdapter(MessageActivity.this,list);
                listView.setDivider(null);
                listView.setAdapter(messageAdapter);
                listView.setScrollY(ScrollView.FOCUS_DOWN);
//                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//                    @Override
//                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
////                        LayoutInflater layoutInflater=getLayoutInflater();
////                        View myView=layoutInflater.inflate(R.layout.activity_message_delete,null);
////                        AlertDialog.Builder builder=new AlertDialog.Builder(MessageActivity.this);
////                        AlertDialog alertDialog=builder.create();
////                        alertDialog.setView(myView);
////                        alertDialog.show();
////                        Log.e("Item selected:",adapterView.getItemAtPosition(i);
//                        return true;
//                    }
//                });
//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        LayoutInflater layoutInflater = getLayoutInflater();
//                        View myView = layoutInflater.inflate(R.layout.activity_message_delete, null);
//                        AlertDialog.Builder builder = new AlertDialog.Builder(MessageActivity.this);
//                        AlertDialog alertDialog = builder.create();
//                        alertDialog.setView(myView);
//                        alertDialog.show();
//                        Log.e("Item selected:", adapterView.getItemAtPosition(i).toString());
//                    }});
//                int height=listView.getHeight();
//                int length=list.size()-1;
//                listView.getS
//                listView.smoothScrollToPosition(length);
//                listView.smoothScrollToPositionFromTop(length,height,1);

//                listView.setFocusable(true);
//                Toast.makeText(MessageActivity.this, "Size of list is:"+list.size(), Toast.LENGTH_SHORT).show();
                Log.e("list size", String.valueOf(list.size()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
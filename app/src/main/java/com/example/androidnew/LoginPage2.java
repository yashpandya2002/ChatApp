package com.example.androidnew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class LoginPage2 extends AppCompatActivity {
//SwipeRefreshLayout swipeRefreshLayout;
    ListView listView;
ArrayList<Message> messageArrayList=new ArrayList<Message>();
    ArrayList<String> newmessageArrayList=new ArrayList<String>();
    Handler handler = new Handler();
    Runnable refresh;
    MyUserAdapter myUserAdapter;
    String receiver_sharedpreference;
private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page2);
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser mUser=mAuth.getCurrentUser();
        listView=findViewById(R.id.listview);
        SharedPreferences sharedPreferences=getSharedPreferences("myapp", Context.MODE_PRIVATE);
        FirebaseDatabase.getInstance().getReference("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot:snapshot.getChildren())
//                {
//                    FirebaseDatabase.getInstance().getReference("user/"+dataSnapshot.getKey().toString()+"/id").setValue(dataSnapshot.getKey().toString());
//                    FirebaseDatabase.getInstance().getReference("user/"+dataSnapshot.getKey().toString()+"/time").setValue(0);
//                    FirebaseDatabase.getInstance().getReference("user/"+dataSnapshot.getKey().toString()+"/lastMessageTo").setValue("");
//                    FirebaseDatabase.getInstance().getReference("user/"+dataSnapshot.getKey().toString()+"/getLastMessageFrom").setValue("");
//                    FirebaseDatabase.getInstance().getReference("user/"+dataSnapshot.getKey().toString()+"/rank").setValue(0);
//                }
                getAllUsers();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        getAllUsers();
        FirebaseDatabase.getInstance().getReference
                ("user/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/id")
                .setValue(FirebaseAuth.getInstance().getCurrentUser().getUid().toString());
        FirebaseDatabase.getInstance().getReference("messages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    Message message=dataSnapshot.getValue(Message.class);
                    if(message.getSender().equals(sharedPreferences.getString("sender","").toString()) || message.getReceiver().equals(sharedPreferences.getString("sender","").toString()))
                    {
                        Log.e("message added",message.getSender()+" to "+message.getReceiver());
                        messageArrayList.add(message);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Collections.reverse(messageArrayList);
        Log.e("reverse message list", String.valueOf(messageArrayList.size()));

        for(Message message:messageArrayList)
        {
            if(message.getReceiver().equals(sharedPreferences.getString("sender","")))
            {
                if(!newmessageArrayList.contains(message.getSender()))
                {
                    newmessageArrayList.add(message.getSender());
                }
            }
            else
            {
                if(!newmessageArrayList.contains(message.getReceiver()))
                {
                    newmessageArrayList.add(message.getReceiver());
                }
            }
        }
        Log.e("list for",sharedPreferences.getString("sender","").toString()+newmessageArrayList.size());
//        swipeRefreshLayout=findViewById(R.id.swiperefresh);
//        refresh = new Runnable() {
//            public void run() {
//                // Do something
//                handler.postDelayed(refresh, 5000);
//                getAllUsers();
//            }
//        };
//        handler.post(refresh);
//        list.clear();
//        ArrayList<String> list=new ArrayList<String>();

//        btn_logout=findViewById(R.id.logout_btn);
//        if(mUser!=null)
//        {
//            String email=mUser.getEmail().toString();
//            text_Email.setText(email);
//        }
//        else
//        {
//            startActivity(new Intent(LoginPage2.this,SignUp.class));
//            finish();
//        }

//        btn_logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mAuth.signOut();
//                startActivity(new Intent(LoginPage2.this,SignUp.class));
//                finish();
//            }
//        });
//        String str[]={};
//        ArrayAdapter myArrayAdapter=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,str);
//        listView.setAdapter(myArrayAdapter);
//        getAllUsers();
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//                getAllUsers();
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });


    }

    private void getAllUsers() {
//        list.clear();
        ArrayList<User> list=new ArrayList<User>();
        FirebaseDatabase.getInstance().getReference("user").addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {



                    try {
                        User user = dataSnapshot.getValue(User.class);
                        if (user.getEmail().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())) {
//                    Toast.makeText(LoginPage2.this, "User"+user.getEmail()+user.getProfilepicture(), Toast.LENGTH_SHORT).show();

                        } else {
                            list.add(user);
                        }
                    }
                    catch (NullPointerException e)
                    {
                        e.printStackTrace();
                    }
//                    MyUserAdapter.setNotifyOnChange(true);
                }
                for (int j=0;j<messageArrayList.size();j++)
                {

                }
                list.sort((o1, o2)
                        -> o1.getUsername().compareTo(
                        o2.getUsername()));
//                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(LoginPage2.this, android.R.layout.simple_list_item_1,list);
                myUserAdapter=new MyUserAdapter(list,LoginPage2.this);
                listView.setAdapter(myUserAdapter);
//                Toast.makeText(LoginPage2.this, "Arrray adapter set", Toast.LENGTH_SHORT).show();
//                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.profile_menu_item)
        {
            startActivity(new Intent(this, Profile_ChatApp.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
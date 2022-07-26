package com.example.androidnew;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MessageAdapter extends BaseAdapter {
    Context context;
    ArrayList<Message> list;

    public MessageAdapter() {
    }

    public MessageAdapter(Context context, ArrayList<Message> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Message getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        SharedPreferences sharedPreferences=context.getSharedPreferences("myapp",Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("sender","");
        long yourmilliseconds=list.get(i).getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date resultdate = new Date(yourmilliseconds);


//        final String[] username_sender = new String[1];
//        FirebaseDatabase.getInstance().getReference("user/"+ FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                username_sender[0] =snapshot.getValue(User.class).getUsername();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        if(list.get(i).getReceiver().equals(user))
        {
            if(list.get(i).getIsPhoto().equals("true"))
            {
                view=LayoutInflater.from(context).inflate(R.layout.imageviewsingle_left,null);
                ImageView imageView=view.findViewById(R.id.image_left);
                TextView imageViewtime = view.findViewById(R.id.image_time_left);
                String time = String.valueOf(sdf.format(resultdate));
                String path=list.get(i).getPhotopath();
                Glide.with(context).load(path).into(imageView);
                imageViewtime.setText(time);
            }
            else {
                view = LayoutInflater.from(context).inflate(R.layout.activity_single_message_base_adapter_left_side, null);
                TextView textView = view.findViewById(R.id.single_msg_left);
                TextView textViewtime = view.findViewById(R.id.time_left);
                String time = String.valueOf(sdf.format(resultdate));
                String msg = list.get(i).getContent();
                textView.setText(msg);
                textViewtime.setText(time);
            }
        }
        else
        {
            if(list.get(i).getIsPhoto().equals("true"))
            {
                view=LayoutInflater.from(context).inflate(R.layout.imageviewsingle_right,null);
                ImageView imageView=view.findViewById(R.id.image_right);
                TextView imageViewtime = view.findViewById(R.id.image_time_right);
                String time = String.valueOf(sdf.format(resultdate));
                String path=list.get(i).getPhotopath();
                Glide.with(context).load(path).into(imageView);
                imageViewtime.setText(time);
            }
            else {
                view = LayoutInflater.from(context).inflate(R.layout.activity_single_message_base_adapter, null);
                TextView textView = view.findViewById(R.id.single_msg);
                TextView textViewtime = view.findViewById(R.id.time_right);
                String time = String.valueOf(sdf.format(resultdate));
                String msg = list.get(i).getContent();
                textView.setText(msg);
                textViewtime.setText(time);
            }
        }
//        view.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                Log.e("Item clicked",);
//                return true;
//            }
//        }
//        );
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                View myView=LayoutInflater.from(context).inflate(R.layout.activity_message_delete,null);

                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                AlertDialog alertDialog=builder.create();
                alertDialog.setView(myView);
                alertDialog.show();
                Button button=myView.findViewById(R.id.activity_delete);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String timestamp=String.valueOf(list.get(i).getTime());
                        FirebaseDatabase.getInstance().getReference("messages/"+timestamp).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context, "Message deleted successfully!", Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();

                            }
                        });

                    }
                });

                return true;
            }
        });
//        else
//        {
//            textView.setTextColor(Color.WHITE);
//            textView.setGravity(Gravity.RIGHT);
//            textView.setBackgroundColor(Color.BLUE);
//            String msg=list.get(i).getContent();
//            textView.setText(msg);
//        }




        return view;
    }

}

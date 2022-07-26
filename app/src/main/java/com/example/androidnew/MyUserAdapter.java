package com.example.androidnew;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;

import com.bumptech.glide.Glide;
import com.example.androidnew.R;
import com.example.androidnew.User;

import java.util.ArrayList;
import java.util.List;

public class MyUserAdapter extends BaseAdapter {

    List<User> arrayList;
    Context context;

    public MyUserAdapter(ArrayList<User> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public MyUserAdapter() {

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public User getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view=LayoutInflater.from(context).inflate(R.layout.activity_chatapp_list_view,null);
        TextView username=view.findViewById(R.id.profile_list_username);
        ImageView imageView=view.findViewById(R.id.profile_list_pic);
        username.setText(arrayList.get(i).getUsername());
//        Uri uri=Uri.parse(arrayList.get(i).getProfilepicture().toString());
//        imageView.setImageURI(uri);

        String link=arrayList.get(i).getProfilepicture();
        Glide.with(context).load(link).into(imageView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,MessageActivity.class);
                intent.putExtra("receiver_name",arrayList.get(i).getUsername());
                intent.putExtra("receiver_pic",link);
                SharedPreferences sharedPreferences=context.getSharedPreferences("myapp",MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("receiver",arrayList.get(i).getUsername());
                myEdit.putString("receiver_id",arrayList.get(i).getId());
                myEdit.commit();
                context.startActivity(intent);
            }
        });
        return view;
    }
}

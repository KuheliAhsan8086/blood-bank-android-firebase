package com.kuheliahsan.bloodbank.Activities;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.kuheliahsan.bloodbank.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<User> {

    private Activity context;
    private List<User> userList;

    public CustomAdapter(Activity context,   List<User> userList) {
        super(context, R.layout.sample_layout, userList);
        this.context = context;
        this.userList=userList;

    }

    @NonNull
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view= layoutInflater.inflate(R.layout.sample_layout,null,true);
        User User=userList.get(position);


        TextView t1=view.findViewById(R.id.textName);
        TextView t2=view.findViewById(R.id.textArea);
        TextView t3=view.findViewById(R.id.textMobile);
        TextView t4=view.findViewById(R.id.textBlood_group);
        TextView t5=view.findViewById(R.id.textEmail);


        return view;
    }
}

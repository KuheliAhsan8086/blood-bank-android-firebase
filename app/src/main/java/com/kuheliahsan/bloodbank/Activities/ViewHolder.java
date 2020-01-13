package com.kuheliahsan.bloodbank.Activities;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kuheliahsan.bloodbank.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public ViewHolder(View itemView) {
        super(itemView);
        mView=itemView;
    }


    public void setDetails(Context ctx, String name, String area, String mobile, String blood_group, String email)
    {
        TextView mname=mView.findViewById(R.id.DonorName);
        TextView marea=mView.findViewById(R.id.DonorArea);
        TextView mmobile=mView.findViewById(R.id.DonorMobile);
        TextView mblood_group=mView.findViewById(R.id.DonorBlood_group);
        TextView memail=mView.findViewById(R.id.DonorEmail);

        //set data to views

        mname.setText(name);
        marea.setText(area);
        mmobile.setText(mobile);
        mblood_group.setText(blood_group);
        memail.setText(email);

    }
}

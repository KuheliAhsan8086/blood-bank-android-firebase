package com.kuheliahsan.bloodbank.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.kuheliahsan.bloodbank.R;

public class BloodBank_list extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank_list);
        listView=findViewById(R.id.listid);
        String[] bloodBankList=getResources().getStringArray(R.array.blood_bank);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(BloodBank_list.this,R.layout.blood_bank_sample,R.id.text_list_view,bloodBankList);
        listView.setAdapter(adapter);
    }
}

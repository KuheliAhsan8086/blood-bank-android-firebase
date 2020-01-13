package com.kuheliahsan.bloodbank.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.kuheliahsan.bloodbank.R;

public class Fact_list extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact_list);
        listView=findViewById(R.id.listid);
        String[] factsList=getResources().getStringArray(R.array.facts_list);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(Fact_list.this,R.layout.facts_list_sample,R.id.text_list_view,factsList);

        listView.setAdapter(adapter);
    }
}

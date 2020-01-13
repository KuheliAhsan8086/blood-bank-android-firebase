


package com.kuheliahsan.bloodbank.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.kuheliahsan.bloodbank.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    FirebaseAuth mAuth;
    DrawerLayout dl1;
    NavigationView nv1;
    Toolbar toolbar1;
    ActionBarDrawerToggle toggle;
    private Button searchDoner,blood_bank,facts,profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        searchDoner=findViewById(R.id.search_donor);
        blood_bank=findViewById(R.id.blood_bank);
        profile=findViewById(R.id.profile);
        facts=findViewById(R.id.facts);

        mAuth=FirebaseAuth.getInstance();
        searchDoner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DonorNewActivity.class));
            }
        });
        blood_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Home.this,BloodBank_list.class);
                startActivity(i);

            }
        });

        facts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Home.this,Fact_list.class);
                startActivity(i);

            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Home.this,ProfileActivity.class);
                startActivity(i);

            }
        });
        dl1=findViewById(R.id.drawlayout);
        toolbar1=findViewById(R.id.toolbar);
        nv1=findViewById(R.id.naviview);
        nv1.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this,dl1,toolbar1,R.string.drawerOpen,R.string.drawerClose);
        dl1.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayShowHomeEnabled(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.logout)
        {
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if(id== R.id.about)
        {
            Intent intent = new Intent(getApplicationContext(),Ourself.class);
            startActivity(intent);
        }
        return true;
    }
}

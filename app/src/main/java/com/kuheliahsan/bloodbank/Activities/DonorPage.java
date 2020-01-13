package com.kuheliahsan.bloodbank.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.kuheliahsan.bloodbank.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DonorPage extends AppCompatActivity {
   DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
   RecyclerView mrecyclerView;
   FirebaseDatabase firebaseDatabase;
   FirebaseAuth auth=FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_page);


        mrecyclerView=findViewById(R.id.recyclerViewID);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

//        toolbar=findViewById(R.id.toolBarId);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        firebaseDatabase= FirebaseDatabase.getInstance();

        FirebaseRecyclerAdapter<User, ViewHolder> firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<User, ViewHolder>(
                        User.class,
                        R.layout.cardview,
                        ViewHolder.class,
                        databaseReference.child("User2")
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, User user, int i) {
                        viewHolder.setDetails(getApplicationContext(), user.getName(), user.getArea(), user.getMobile(), user.getBlood_group(), user.getEmail());
                    }
                };
        mrecyclerView.setAdapter(firebaseRecyclerAdapter);


    }


    //search data
    public void firebaseSearch(String searchText){
        Query firebaseSearchQuery= databaseReference.orderByChild("area").startAt(searchText).endAt(searchText +"\uf8ff");
        FirebaseRecyclerAdapter<User,ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<User, ViewHolder>(
                        User.class,
                        R.layout.cardview,
                        ViewHolder.class,
                        firebaseSearchQuery
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, User user, int i) {
                        viewHolder.setDetails(getApplicationContext(),user.getName(),user.getArea(),user.getMobile(),user.getBlood_group(),user.getEmail());

                    }
                };
        //set adapter
        mrecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    //load data into recycler view onStart


    @Override
    protected void onStart() {
        super.onStart();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem item= menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //filter as you type
                firebaseSearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        if(id==R.id.action_settings)
        {
            //TODO
            return true;
        }
        return super.onOptionsItemSelected(item);
   }
}


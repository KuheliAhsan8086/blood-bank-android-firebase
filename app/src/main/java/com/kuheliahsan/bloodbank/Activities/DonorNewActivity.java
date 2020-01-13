package com.kuheliahsan.bloodbank.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.Query;
import com.kuheliahsan.bloodbank.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DonorNewActivity extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView mrecyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_new);

        mrecyclerView=findViewById(R.id.recyclerView2);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        toolbar=findViewById(R.id.toolBarId);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        firebaseDatabase= FirebaseDatabase.getInstance();
        readAllData();
    }

    private void firebaseSearch(String searchText){
        if (!searchText.isEmpty()){

            String newSearchText=searchText.toLowerCase();

            Query firebaseSearchQuery=databaseReference.child("User").child("User").orderByChild("blood_group").startAt(newSearchText).endAt(newSearchText + "\uf8ff");
            FirebaseRecyclerAdapter<User, ViewHolder> firebaseRecyclerAdapter=
                    new FirebaseRecyclerAdapter<User, ViewHolder>(
                            User.class,
                            R.layout.cardview,
                            ViewHolder.class,
                            firebaseSearchQuery
                    ) {
                        @Override
                        protected void populateViewHolder(ViewHolder viewHolder, User user, int i) {
                            viewHolder.setDetails(getApplicationContext(), user.getName(), user.getArea(), user.getMobile(), user.getBlood_group(), user.getEmail());
                        }

                        @Override
                        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                            ViewHolder viewHolder=super.onCreateViewHolder(parent, viewType);

                            return viewHolder;
                        }

                    };
            mrecyclerView.setAdapter(firebaseRecyclerAdapter);
        } else {
            readAllData();
        }

    }

    private void readAllData() {
        FirebaseRecyclerAdapter<User, ViewHolder> firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<User, ViewHolder>(
                        User.class,
                        R.layout.cardview,
                        ViewHolder.class,
                        databaseReference.child("User").child("User")
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, User user, int i) {
                        viewHolder.setDetails(getApplicationContext(), user.getName(), user.getArea(), user.getMobile(), user.getBlood_group(), user.getEmail());
                    }
                };
        mrecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem item=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                firebaseSearch(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //int id=item.getItemId();

        return super.onOptionsItemSelected(item);
    }


}

package com.kuheliahsan.bloodbank.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kuheliahsan.bloodbank.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    private TextView profileName,profileArea,profileMobile,profileBloodGroup,profileEmail;
    private Button profileBtton;
    private FirebaseAuth auth=FirebaseAuth.getInstance();
    private DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileName=findViewById(R.id.profile_name);
        profileArea=findViewById(R.id.profile_area);
        profileMobile=findViewById(R.id.profile_mobile);
        profileBloodGroup=findViewById(R.id.profile_bloodGroup);
        profileEmail=findViewById(R.id.profile_email);


        databaseReference.child("User").child("User").child(auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user=dataSnapshot.getValue(User.class);

                profileName.setText("Name :"+user.getName());
                profileArea.setText("Area :"+user.getArea());
                profileMobile.setText("Mobile :"+user.getMobile());
                profileBloodGroup.setText("Blood Group :"+user.getBlood_group());
                profileEmail.setText("Email :"+user.getEmail());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this,databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}

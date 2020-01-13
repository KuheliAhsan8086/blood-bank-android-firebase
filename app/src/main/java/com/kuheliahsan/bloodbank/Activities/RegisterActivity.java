package com.kuheliahsan.bloodbank.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.kuheliahsan.bloodbank.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameEt,areaEt,bloodGroupEt,mobileEt,passwordEt,emailEt;
    private Button submitButton;
    private ProgressBar progressBar;
    private TextView login;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        this.setTitle("Registration");



        login=findViewById(R.id.logid);
        progressBar=findViewById(R.id.progressber);
        nameEt=findViewById(R.id.name);
        areaEt=findViewById(R.id.area);
        bloodGroupEt=findViewById(R.id.blood_group);
        mobileEt=findViewById(R.id.number);
        passwordEt=findViewById(R.id.password);
        emailEt=findViewById(R.id.email);
        submitButton=findViewById(R.id.submit_button);

        databaseReference=FirebaseDatabase.getInstance().getReference("User");

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRegistrer();


                //showMessage(name+"\n"+area+"\n"+blood_group+"\n"+password+"\n"+mobile);



            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }

    private void userRegistrer() {
        final String name,area,blood_group,mobile,password,email;
        name= nameEt.getText().toString().trim();
        area= areaEt.getText().toString().trim();
        blood_group= bloodGroupEt.getText().toString().trim();
        mobile =mobileEt.getText().toString().trim();
        email=emailEt.getText().toString().trim();
        password= passwordEt.getText().toString().trim();


        if(name.isEmpty())
        {
            nameEt.setError("Enter your name");
            nameEt.requestFocus();
            return;
        }
        if(area.isEmpty())
        {
            areaEt.setError("Enter your area");
            areaEt.requestFocus();
            return;
        }
        if(blood_group.isEmpty())
        {
            bloodGroupEt.setError("Enter blood group");
            bloodGroupEt.requestFocus();
            return;
        }
        if(mobile.isEmpty())
        {
            mobileEt.setError("Enter mobile number");
            mobileEt.requestFocus();
            return;
        }
        if(mobile.length()<11)
        {
            passwordEt.setError("enter valid mobile number");
            passwordEt.requestFocus();
            return;
        }



        //checking the validity of the email
        if(email.isEmpty())
        {
            emailEt.setError("Enter an email address");
            emailEt.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailEt.setError("Enter a valid email address");
            emailEt.requestFocus();
            return;
        }

        //checking the validity of the password
        if(password.isEmpty())
        {
            passwordEt.setError("Enter a password");
            passwordEt.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            passwordEt.setError("Minimum length of a password should be 6");
            passwordEt.requestFocus();
            return;
        }
        else
        {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressBar.setVisibility(View.GONE);

                    if (task.isSuccessful()) {

                        User information = new User(name, area, blood_group, mobile, email);

                        databaseReference.child("User").child(mAuth.getUid()).setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getApplicationContext(), "Registration is successfull", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RegisterActivity.this,MainActivity.class);
                                startActivity(i);
                                finish();

                            }
                        });


                    }
                    else {
                        if(task.getException() instanceof FirebaseAuthUserCollisionException)
                        {
                            Toast.makeText(RegisterActivity.this, "user is already registered", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {

                            Toast.makeText(getApplicationContext(),"Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }

                        //Toast.makeText(getApplicationContext(),"Registration is not successfull",Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }
}
